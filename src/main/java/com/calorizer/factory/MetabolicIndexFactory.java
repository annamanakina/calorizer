package com.calorizer.factory;

import com.calorizer.calculation.Formulas;
import com.calorizer.items.Person;

public class MetabolicIndexFactory {

    public static final int BMI = 1;
    public static final int DAILY_CALORIE_INTAKE = 2;
    public static final int BMR = 3;

    public static double calculate(int id, Person person) {
        double index = 0.0;

        switch (id) {
            case BMI:
                index = Formulas.calculateBodyMassIndex(person);
                break;
            case DAILY_CALORIE_INTAKE:
                index = Formulas.calculateDailyCaloriesByHarrisBenedictEquation(person);
                break;
            case BMR:
                index = Formulas.calculateBMRHarrisBenedictEquation(person);
                break;
        }
        return index;
    }
}
