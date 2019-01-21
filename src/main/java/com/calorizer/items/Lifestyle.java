package com.calorizer.items;

import com.calorizer.calculation.ActivityMultiplier;

import java.util.Objects;

public class Lifestyle {

    private ActivityMultiplier activityMultiplier;
    private double bodyMassIndex;
    private double dailyCalorieIntakes;
    private double basalMetabolicRate;

    public Lifestyle(ActivityMultiplier activityMultiplier, double bodyMassIndex, double dailyCalorieIntakes, double basalMetabolicRate) {
        this.activityMultiplier = activityMultiplier;
        this.bodyMassIndex = bodyMassIndex;
        this.dailyCalorieIntakes = dailyCalorieIntakes;
        this.basalMetabolicRate = basalMetabolicRate;
    }

    public ActivityMultiplier getActivityMultiplier() {
        return activityMultiplier;
    }

    public void setActivityMultiplier(ActivityMultiplier activityMultiplier) {
        this.activityMultiplier = activityMultiplier;
    }

    public double getBodyMassIndex() {
        return bodyMassIndex;
    }

    public void setBodyMassIndex(double bodyMassIndex) {
        this.bodyMassIndex = bodyMassIndex;
    }

    public double getDailyCalorieIntakes() {
        return dailyCalorieIntakes;
    }

    public void setDailyCalorieIntakes(double dailyCalorieIntakes) {
        this.dailyCalorieIntakes = dailyCalorieIntakes;
    }

    public double getBasalMetabolicRate() {
        return basalMetabolicRate;
    }

    public void setBasalMetabolicRate(double basalMetabolicRate) {
        this.basalMetabolicRate = basalMetabolicRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lifestyle lifestyle = (Lifestyle) o;
        return Double.compare(lifestyle.bodyMassIndex, bodyMassIndex) == 0 &&
                Double.compare(lifestyle.dailyCalorieIntakes, dailyCalorieIntakes) == 0 &&
                Double.compare(lifestyle.basalMetabolicRate, basalMetabolicRate) == 0 &&
                activityMultiplier == lifestyle.activityMultiplier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityMultiplier, bodyMassIndex, dailyCalorieIntakes, basalMetabolicRate);
    }

    @Override
    public String toString() {
        return "Lifestyle{" +
                "activityMultiplier=" + activityMultiplier +
                ", bodyMassIndex=" + bodyMassIndex +
                ", dailyCalorieIntakes=" + dailyCalorieIntakes +
                ", basalMetabolicRate=" + basalMetabolicRate +
                '}';
    }
}
