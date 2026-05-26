package com.ftn.sbnz.model.events;

import java.io.Serializable;
import java.util.Date;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;
import org.kie.api.definition.type.Expires;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Expires("96h")
public class SoilTemperatureEvent implements Serializable {
    private Date timestamp;
    private Long soilId;
    private double temperature;

    public SoilTemperatureEvent(Date timestamp, Long soilId, double temperature) {
        this.timestamp = timestamp;
        this.soilId = soilId;
        this.temperature = temperature;
    }
    public SoilTemperatureEvent() {}

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Long getSoilId() {
        return soilId;
    }

    public void setSoilId(Long soilId) {
        this.soilId = soilId;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
