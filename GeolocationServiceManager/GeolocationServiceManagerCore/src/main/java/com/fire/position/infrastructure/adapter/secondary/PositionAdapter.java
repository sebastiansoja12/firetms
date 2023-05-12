package com.fire.position.infrastructure.adapter.secondary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fire.geocoding.GeocodingService;
import com.fire.geocoding.dto.CoordinatesDto;
import com.fire.geocoding.dto.CountryResponseDto;
import com.fire.position.domain.model.Coordinate;
import com.fire.position.domain.model.Position;
import com.fire.position.domain.model.Truck;
import com.fire.position.domain.port.secondary.PositionRepository;
import com.fire.position.domain.port.secondary.PositionServicePort;
import com.fire.position.domain.vo.PositionTransfer;
import com.fire.report.LogEventPublisher;
import com.fire.report.dto.BorderCrossingDto;
import com.fire.report.dto.EventDto;
import com.fire.report.dto.TruckPositionMessageDto;
import com.fire.report.event.TruckPositionDetermineEvent;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@AllArgsConstructor
public class PositionAdapter implements PositionServicePort {

    private final LogEventPublisher logEventPublisher;

    // to wyciagnac do nowej konfiguracji
    private final static String POSITION_URL = "http://localhost:8081/vehicles/coordinates/";

    private final GeocodingService geocodeService;

    private final PositionRepository positionRepository;

    // TODO tutaj to trzeba przerobić, te serwisy juz sa tutaj wykorzystywane. Trzeba zrobić tak, że jak pozycja 0 jest
    // np. z polski a następna już nie to wtedy powinien być stworzony ten EVentDto i trzeba pobrać mu te kraje ktore były
    // tutaj mozliwe ze trzeba bedzie przekazac z bazy wczesniej pobrane wartosci, chcoiaz tutaj tez mozna sie odwolac do bazy idk
    // ale jakos tak i wtedy bedzie mozna ten raport wygenerowac i nawet go zapisac wiec zostanie kwestia ustawienia postgres i
    // tych propertiesow zeby sie podmieniło

    // to jeszcze inaczej trzeba, mozna zrobic tak ze jak przychodzi ta informacja o pozycji to by sprawdzamy, czy poprzedni rekord w bazie
    // ma ten sam kod kraju, a jak nie to DOPIERO wtedy tworzymy ten event ktory trafia do raportgeneratora
    @Override
    public void determineVehiclePosition(Truck truck, List<Position> positions) {
        // event
        final Position position = getPosition(truck);

        positions.forEach(
                pos -> {
                    if (!position.getCountry().equals(pos.getCountry())) {
                        final EventDto event = EventDto.builder()
                                .eventTimeStamp(Instant.now())
                                .countryIn(position.getCountry())
                                .countryOut(pos.getCountry())
                                .vehicleReg(truck.getPlate())
                                .build();

                        final TruckPositionMessageDto message = TruckPositionMessageDto.builder()
                                .raportTimeStamp(Instant.now())
                                .events(List.of(event))
                                .build();

                        sendEvent(buildEvent(message));
                    }
                }
        );
    }

    @Override
    public Position determineVehiclePositions(Truck truck) {
        return getPosition(truck);
    }

    @Override
    public void createBorderCrossingEvent(Position position, Position newPosition) {
        final List<EventDto> events = new ArrayList<>();

        final EventDto event = EventDto.builder()
                .eventTimeStamp(Instant.now())
                .countryIn(newPosition.getCountry())
                .countryOut(position.getCountry())
                .vehicleReg(position.getVehicleReg())
                .build();

        events.add(event);

        final TruckPositionMessageDto message = TruckPositionMessageDto.builder()
                .raportTimeStamp(Instant.now())
                .events(events)
                .build();

        sendEvent(buildEvent(message));
    }

    private Position getPosition(Truck truck) {
        final RestTemplate restTemplate = new RestTemplate();
        final String json = restTemplate.getForObject(POSITION_URL + truck.getPlate(), String.class);
        final ObjectMapper objectMapper = new ObjectMapper();
        PositionTransfer positionTransfer = new PositionTransfer();
        try {
            positionTransfer = objectMapper.readValue(json, PositionTransfer.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        final CountryResponseDto countryResponse = geocodeService.determineCountry(coordinates(positionTransfer));

        return Position.builder()
                .coordinate(Coordinate.builder()
                        .latitude(positionTransfer.getLatitude())
                        .longitude(positionTransfer.getLongitude())
                        .build())
                .vehicleReg(truck.getPlate())
                .timestamp(Instant.now().toString())
                .country(countryResponse.getCountry())
                .build();
    }

    private CoordinatesDto coordinates(PositionTransfer positionTransfer) {
        return new CoordinatesDto(positionTransfer.getLongitude(), positionTransfer.getLatitude());
    }

    private TruckPositionDetermineEvent buildEvent(TruckPositionMessageDto message) {
        return TruckPositionDetermineEvent.builder()
                .truckPositionMessage(message)
                .build();
    }

    public void sendEvent(TruckPositionDetermineEvent event) {
        logEventPublisher.send(event);
    }
}
