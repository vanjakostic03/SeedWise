package com.ftn.sbnz.model.models;


public class SoilParameters {
    private Long parcelId;

    private double soilTemperature;
    private double moisture;            //vlaznost zemljista
    private boolean insectsPresent;
    private boolean plowed;             //zaorano
    private boolean fertilized;           //djubrenje
    private double ph;
    private double airTemperature;

    public SoilParameters(){}
    public SoilParameters(Long parcelId, double soilTemperature, double moisture, boolean insectsPresent, boolean isPlowed, boolean isFertilized, double ph, double airTemperature) {
        this.parcelId = parcelId;
        this.soilTemperature = soilTemperature;
        this.moisture = moisture;
        this.insectsPresent = insectsPresent;
        this.plowed = isPlowed;
        this.fertilized = isFertilized;
        this.ph = ph;
        this.airTemperature = airTemperature;
    }

    public Long getParcelId() {
        return parcelId;
    }

    public void setParcelId(Long parcelId) {
        this.parcelId = parcelId;
    }

    public double getSoilTemperature() {
        return soilTemperature;
    }

    public void setSoilTemperature(double soilTemperature) {
        this.soilTemperature = soilTemperature;
    }

    public double getMoisture() {
        return moisture;
    }

    public void setMoisture(double moisture) {
        this.moisture = moisture;
    }

    public boolean isInsectsPresent() {
        return insectsPresent;
    }

    public void setInsectsPresent(boolean insectsPresent) {
        this.insectsPresent = insectsPresent;
    }

    public boolean isPlowed() {
        return plowed;
    }

    public void setPlowed(boolean plowed) {
        plowed = plowed;
    }

    public boolean isFertilized() {
        return fertilized;
    }

    public void setFertilized(boolean fertilized) {
        fertilized = fertilized;
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }

    public double getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(double airTemperature) {
        this.airTemperature = airTemperature;
    }
}
