package com.calorizer.calculation;

import com.calorizer.items.Person;

public class Formulas {

     public static double calculateDailyCaloriesByHarrisBenedictEquation(Person person){
        return person.getMetabolicRate().getBasalMetabolicRate() * person.getMetabolicRate().getActivityMultiplier().getFactor();
    }

    /*public static double calculateDailyCaloriesByMifflinJeorEquation(Person person){
        return calculateBMRMifflinStJeorEquation(person)*person.getActivityMultiplier().getFactor();
    }*/

    public static double calculateBodyMassIndex(Person person) {
        // return person.getWeight().divide(new BigDecimal(Math.pow((person.getHeight() / 100.0), 2)), 2, BigDecimal.ROUND_HALF_UP);
        return person.getWeight() / (Math.pow((person.getHeight() / 100.0), 2));
    }

    public static double calculateBMRHarrisBenedictEquation(Person person) {
        double callories = 0.0;
        switch (person.getSex()) {
            case MALE:
                callories = 88.362 + (13.397 * person.getWeight()) + (4.799 * person.getHeight()) - (5.677 * person.getAge());
                return callories;

            case FEMALE:
                callories = 447.593 + (9.247 * person.getWeight()) + (3.098 * person.getHeight()) - (4.330 * person.getAge());
                return callories;
        }
        return callories;
    }
}
