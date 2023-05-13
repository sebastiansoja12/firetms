package com.fire.telemetry;

import com.fire.properties.GeoProperties;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "telemetry")
public class TelemetryProperties extends GeoProperties {

    private static final String URL = "telemetry.url";

    private static final String STAGE = "telemetry.stage";

    private String url;

    private String stage;


    public void setUrl(String url) {
        this.url = url;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

}
