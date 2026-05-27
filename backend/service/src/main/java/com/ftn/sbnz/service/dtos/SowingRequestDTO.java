package com.ftn.sbnz.service.dtos;



public class SowingRequestDTO {
    private Long seriesId;
    private double seedMoisture;
    private double purity;
    private double thousandGrainMass;
    private double germination;
    private double germinationEnergy;
    private double fusarium;
    private boolean biologicalImpurities;
    private int ageYears;

    private Long parcelId;
    private double soilTemperature;
    private double soilMoisture;
    private boolean insectsPresent;
    private boolean plowed;
    private boolean fertilized;
    private double ph;
    private double airTemperature;

    private Long storageId;
    private double storageTemperature;
    private double storageHumidity;
    private boolean pestsPresent;



    public  SowingRequestDTO(){

    }
    public SowingRequestDTO(Long seriesId, double seedMoisture, double purity, double thousandGrainMass, double germination, double germinationEnergy, double fusarium, boolean biologicalImpurities, int ageYears, Long parcelId, double soilTemperature, double soilMoisture, boolean insectsPresent, boolean plowed, boolean fertilized, double ph, double airTemperature, Long storageId, double storageTemperature, double storageHumidity, boolean pestsPresent) {
        this.seriesId = seriesId;
        this.seedMoisture = seedMoisture;
        this.purity = purity;
        this.thousandGrainMass = thousandGrainMass;
        this.germination = germination;
        this.germinationEnergy = germinationEnergy;
        this.fusarium = fusarium;
        this.biologicalImpurities = biologicalImpurities;
        this.ageYears = ageYears;
        this.parcelId = parcelId;
        this.soilTemperature = soilTemperature;
        this.soilMoisture = soilMoisture;
        this.insectsPresent = insectsPresent;
        this.plowed = plowed;
        this.fertilized = fertilized;
        this.ph = ph;
        this.airTemperature = airTemperature;
        this.storageId = storageId;
        this.storageTemperature = storageTemperature;
        this.storageHumidity = storageHumidity;
        this.pestsPresent = pestsPresent;
    }

    public Long getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Long seriesId) {
        this.seriesId = seriesId;
    }

    public double getSeedMoisture() {
        return seedMoisture;
    }

    public void setSeedMoisture(double seedMoisture) {
        this.seedMoisture = seedMoisture;
    }

    public double getPurity() {
        return purity;
    }

    public void setPurity(double purity) {
        this.purity = purity;
    }

    public double getThousandGrainMass() {
        return thousandGrainMass;
    }

    public void setThousandGrainMass(double thousandGrainMass) {
        this.thousandGrainMass = thousandGrainMass;
    }

    public double getGermination() {
        return germination;
    }

    public void setGermination(double germination) {
        this.germination = germination;
    }

    public double getGerminationEnergy() {
        return germinationEnergy;
    }

    public void setGerminationEnergy(double germinationEnergy) {
        this.germinationEnergy = germinationEnergy;
    }

    public double getFusarium() {
        return fusarium;
    }

    public void setFusarium(double fusarium) {
        this.fusarium = fusarium;
    }

    public boolean isBiologicalImpurities() {
        return biologicalImpurities;
    }

    public void setBiologicalImpurities(boolean biologicalImpurities) {
        this.biologicalImpurities = biologicalImpurities;
    }

    public int getAgeYears() {
        return ageYears;
    }

    public void setAgeYears(int ageYears) {
        this.ageYears = ageYears;
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

    public double getSoilMoisture() {
        return soilMoisture;
    }

    public void setSoilMoisture(double soilMoisture) {
        this.soilMoisture = soilMoisture;
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
        this.plowed = plowed;
    }

    public boolean isFertilized() {
        return fertilized;
    }

    public void setFertilized(boolean fertilized) {
        this.fertilized = fertilized;
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

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public double getStorageTemperature() {
        return storageTemperature;
    }

    public void setStorageTemperature(double storageTemperature) {
        this.storageTemperature = storageTemperature;
    }

    public double getStorageHumidity() {
        return storageHumidity;
    }

    public void setStorageHumidity(double storageHumidity) {
        this.storageHumidity = storageHumidity;
    }

    public boolean isPestsPresent() {
        return pestsPresent;
    }

    public void setPestsPresent(boolean pestsPresent) {
        this.pestsPresent = pestsPresent;
    }
}
