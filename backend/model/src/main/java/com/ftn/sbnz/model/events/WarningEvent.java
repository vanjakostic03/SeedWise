package com.ftn.sbnz.model.events;

public class WarningEvent {

    private String type;
    private String message;
    private Long entityId;

    public WarningEvent(){}

    public WarningEvent(String type, String message, Long entityId) {
        this.type = type;
        this.message = message;
        this.entityId = entityId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }
}
