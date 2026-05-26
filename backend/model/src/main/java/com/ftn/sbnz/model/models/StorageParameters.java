package com.ftn.sbnz.model.models;



public class StorageParameters {

    private Long storageId;

    private double temperature;
    private double humidity;
    private boolean pestsPresent;

//    private boolean safe = true;
//    private String warningMessage = "";

    public StorageParameters(){}
    public StorageParameters(Long storageId, double temperature, double humidity, boolean pestsPresent) {
        this.storageId = storageId;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pestsPresent = pestsPresent;
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

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public boolean isPestsPresent() {
        return pestsPresent;
    }

    public void setPestsPresent(boolean pestsPresent) {
        this.pestsPresent = pestsPresent;
    }
}
