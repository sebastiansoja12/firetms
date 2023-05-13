package com.fire.telemetry;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

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
