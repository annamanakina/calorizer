package com.calorizer.items;

import com.calorizer.calculation.ActivityMultiplier;

import java.util.Objects;

public class MetabolicRate {
    private ActivityMultiplier multiplier;
    private double bodyMassIndex;
    private double basalMetabolicRate;
    private double dailyCaloriesIntakes;

    public MetabolicRate() {
    }

    public MetabolicRate(ActivityMultiplier multiplier, double bodyMassIndex, double basalMetabolicRate) {
        this.multiplier = multiplier;
        this.bodyMassIndex = bodyMassIndex;
        this.basalMetabolicRate = basalMetabolicRate;
    }

    public ActivityMultiplier getActivityMultiplier() {
        return multiplier;
    }

    public void setLifestyle(ActivityMultiplier multiplier) {
        this.multiplier = multiplier;
    }

    public double getBodyMassIndex() {
        return bodyMassIndex;
    }

    public void setBodyMassIndex(double bodyMassIndex) {
        this.bodyMassIndex = bodyMassIndex;
    }

    public double getBasalMetabolicRate() {
        return basalMetabolicRate;
    }

    public void setBasalMetabolicRate(double basalMetabolicRate) {
        this.basalMetabolicRate = basalMetabolicRate;
    }

    public double getDailyCaloriesIntakes() {
        return dailyCaloriesIntakes;
    }

    public void setDailyCaloriesIntakes(double dailyCaloriesIntakes) {
        this.dailyCaloriesIntakes = dailyCaloriesIntakes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetabolicRate that = (MetabolicRate) o;
        return Objects.equals(multiplier, that.multiplier) &&
                Objects.equals(bodyMassIndex, that.bodyMassIndex) &&
                Objects.equals(basalMetabolicRate, that.basalMetabolicRate) &&
                Objects.equals(dailyCaloriesIntakes, that.dailyCaloriesIntakes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(multiplier, bodyMassIndex, basalMetabolicRate, dailyCaloriesIntakes);
    }

    @Override
    public String toString() {
        return "MetabolicRate{" +
                "multiplier=" + multiplier +
                ", bodyMassIndex=" + bodyMassIndex +
                ", basalMetabolicRate=" + basalMetabolicRate +
                ", dailyCaloriesIntakes=" + dailyCaloriesIntakes +
                '}';
    }

}
