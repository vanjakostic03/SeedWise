package com.ftn.sbnz.model.events;

import java.io.Serializable;
import java.util.Date;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;
import org.kie.api.definition.type.Expires;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Expires("72h")
public class StorageHumidityEvent implements Serializable {
    private Date timestamp;
    private Long storageId;
    private double humidity;

    public StorageHumidityEvent(Date timestamp, Long storageId, double humidity) {
        this.timestamp = timestamp;
        this.storageId = storageId;
        this.humidity = humidity;
    }

    public StorageHumidityEvent() {}


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

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
}
