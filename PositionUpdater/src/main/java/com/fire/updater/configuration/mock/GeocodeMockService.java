package com.fire.updater.configuration.mock;

import com.fire.geocoding.dto.CoordinatesDto;
import com.fire.geocoding.dto.CountryResponseDto;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@ConditionalOnProperty(name="service.mock", havingValue="true")
@Service
public class GeocodeMockService {

    public CountryResponseDto determineCountry(CoordinatesDto coordinate) {

        if ((coordinate.getLatitude() >= 49.002046 && coordinate.getLatitude() <= 54.851536)
        && coordinate.getLongitude() >= 14.123997 && coordinate.getLongitude() <= 24.145303) {
            return new CountryResponseDto("POL");
        }
        if ((coordinate.getLatitude() >= 47.270111 && coordinate.getLatitude() <= 55.058203)
                && coordinate.getLongitude() >= 5.866342 && coordinate.getLongitude() <= 15.041931) {
            return new CountryResponseDto("DEU");
        }
        if ((coordinate.getLatitude() >= 41.333685 && coordinate.getLatitude() <= 51.124213)
                && coordinate.getLongitude() >= -5.266392 && coordinate.getLongitude() <= 9.662667) {
            return new CountryResponseDto("FRA");
        }

        return new CountryResponseDto("MOCK");
    }

}
