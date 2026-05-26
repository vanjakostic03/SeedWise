package com.ftn.sbnz.model.events;


import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;
import org.kie.api.definition.type.Expires;

import java.io.Serializable;
import java.util.Date;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Expires("24h")
public class StorageTemperatureEvent implements Serializable {

    private Date timestamp;
    private Long storageId;
    private double temperature;

    public StorageTemperatureEvent(){}

    public StorageTemperatureEvent(Date timestamp, Long storageId, double temperature) {
        this.timestamp = timestamp;
        this.storageId = storageId;
        this.temperature = temperature;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}


