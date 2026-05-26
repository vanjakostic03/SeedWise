package com.ftn.sbnz.model.events;

import java.io.Serializable;
import java.util.Date;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;
import org.kie.api.definition.type.Expires;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Expires("24h")
public class PestDetectionEvent implements Serializable {
    private Date timestamp;
    private Long storageId;
    private boolean pest;

    public PestDetectionEvent() {}

    public PestDetectionEvent(Date timestamp, Long storageId, boolean pest) {
        this.timestamp = timestamp;
        this.storageId = storageId;
        this.pest = pest;
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

    public boolean isPest() {
        return pest;
    }

    public void setPest(boolean pest) {
        this.pest = pest;
    }
}
