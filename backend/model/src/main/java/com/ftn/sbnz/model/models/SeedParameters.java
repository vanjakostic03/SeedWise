package com.ftn.sbnz.model.models;


public class SeedParameters {
    private Long seriesId;

    private double moisture;                //vlaznost
    private double purity;                  //cistoca
    private double thousandGrainMass;       //masa hiljadu zrna
    private double germination;             //klijavost
    private double germinationEnergy;       //energija klijavosti
    private double fusarium;                //zdravstveno stanje - fusarium
    private boolean biologicalImpurities;   //bioloske primese
    private int ageYears;                   //starost

    public SeedParameters(){}
    public SeedParameters(Long seriesId, double moisture, double purity, double thousandGrainMass, double germination, double germinationEnergy, double fusarium, boolean biologicalImpurities, int ageYears) {
        this.seriesId = seriesId;
        this.moisture = moisture;
        this.purity = purity;
        this.thousandGrainMass = thousandGrainMass;
        this.germination = germination;
        this.germinationEnergy = germinationEnergy;
        this.fusarium = fusarium;
        this.biologicalImpurities = biologicalImpurities;
        this.ageYears = ageYears;
    }

    public Long getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Long seriesId) {
        this.seriesId = seriesId;
    }

    public double getMoisture() {
        return moisture;
    }

    public void setMoisture(double moisture) {
        this.moisture = moisture;
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
}
