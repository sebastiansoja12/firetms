package com.fire.telemetry;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "telemetry")
public class TelemetryProperties {


    private String url;

    private String stage;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}
