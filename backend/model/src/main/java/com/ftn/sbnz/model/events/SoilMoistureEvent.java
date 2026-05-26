package com.ftn.sbnz.model.events;

import java.io.Serializable;
import java.util.Date;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;
import org.kie.api.definition.type.Expires;


@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Expires("96h")
public class SoilMoistureEvent implements Serializable {
    private Date timestamp;
    private Long soilId;
    private double moisture;

    public SoilMoistureEvent() {}

    public SoilMoistureEvent(Date timestamp, Long soilId, double moisture) {
        this.timestamp = timestamp;
        this.soilId = soilId;
        this.moisture = moisture;
    }

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

    public double getMoisture() {
        return moisture;
    }

    public void setMoisture(double moisture) {
        this.moisture = moisture;
    }
}
