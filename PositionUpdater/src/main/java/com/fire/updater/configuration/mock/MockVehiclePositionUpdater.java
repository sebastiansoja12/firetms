package com.fire.updater.configuration.mock;

import com.fire.updater.domain.model.Vehicle;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Configuration
@Slf4j
@Getter
@ConditionalOnProperty(name="service.mock", havingValue="true")
public class MockVehiclePositionUpdater {
    private final List<Vehicle> vehicles = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Create some vehicles with initial coordinates
        vehicles.add(new Vehicle("SR1234", true, 45.843995, 9.038434, Instant.now()));
        vehicles.add(new Vehicle("SO1234", true, 51.0468548, 19.9348336, Instant.now()));
        vehicles.add(new Vehicle("SK1234", true, 52.0468548, 19.9348336, Instant.now()));
        vehicles.add(new Vehicle("SY1234", true, 53.0468548, 19.9348336, Instant.now()));
        vehicles.add(new Vehicle("SRB1234", true, 54.0468548, 19.9348336, Instant.now()));

        // Start a timer that updates the coordinates of each vehicle every 5 minutes
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("Updating coordinates");
                vehicles.forEach(
                        vehicle -> {
                            double latitude = vehicle.getLatitude() + Math.random() * 0.01 - 0.005;
                            double longitude = vehicle.getLongitude() + Math.random() * 0.01 - 0.005;
                            vehicle.setLatitude(latitude);
                            vehicle.setLongitude(longitude);
                            vehicle.setTimeStamp(Instant.now());
                        }
                );
            }
        }, 0, 60000);
    }
}
