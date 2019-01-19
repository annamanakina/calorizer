package com.calorizer.calculation;

import com.calorizer.items.Person;

import java.util.List;

public class Formulas {

    public static double calculateCalories(List<Note> notes){
        double calories = 0.0;
        if (notes != null && notes.size() > 0) {
            for (Note note : notes) {
                calories += note.getProduct().getCalories();
            }
        }
       return calories;
    }

    public static double calculateProteins(List<Note> notes){
        double proteins = 0.0;
        if (notes != null && notes.size() > 0) {
            for (Note note : notes) {
                proteins += note.getProduct().getProteins();
            }
        }
        return proteins;
    }

    public static double calculateFats(List<Note> notes){
        double fats = 0.0;
        if (notes != null && notes.size() > 0) {
            for (Note note : notes) {
                fats += note.getProduct().getFats();
            }
        }
        return fats;
    }

    public static double calculateCarbohydrates(List<Note> notes){
        double carbohydrates = 0.0;
        if (notes != null && notes.size() > 0) {
            for (Note note : notes) {
                carbohydrates += note.getProduct().getCarbohydrates();
            }
        }
        return carbohydrates;
    }


    public static double calculateTotalWeight(List<Note> notes){
        double weight = 0.0;
        if (notes != null && notes.size() > 0) {
            for (Note note : notes) {
                weight += note.getWeight();
            }
        }
        return weight;
    }

    //TODO
    public static double calculateDailyCaloriesByHarrisBenedictEquation(Person person){
        return calculateBMRHarrisBenedictEquation(person)*person.getActivityMultiplier().getFactor();
    }

    public static double calculateDailyCaloriesByMifflinJeorEquation(Person person){
        return calculateBMRMifflinStJeorEquation(person)*person.getActivityMultiplier().getFactor();
    }

    //TODO !Nutritional Needs
    /*public void calculateDailyCalories(){

    }*/



    /*как рассчитать индекс массы тела, существует простой ответ: необходимо вес (в килограммах)
    разделить на возведенный в квадрат рост (в метрах), то есть ИМТ = вес (кг): (рост (м))2.
    Например, входящие данные таковы: вес = 85 кг, рост = 165 см. Следовательно, ИМТ = 85:(1,65×1,65) = 31,2.*/
    //TODO !body mass index
    public static double calculateBodyMassIndex(Person person){
        return person.getWeight() / Math.pow((person.getHeight()/100.0), 2);
    }



    /*Equation to Calculate Your BMR
The Harris-Benedict Equation is often used to estimate basal metabolic rate.
Men: BMR = 88.362 + (13.397 x weight in kg) + (4.799 x height in cm) - (5.677 x age in years)
Women: BMR = 447.593 + (9.247 x weight in kg) + (3.098 x height in cm) - (4.330 x age in years)*/

    //TODO Equation to Calculate Your BMR
    public static double calculateBMRHarrisBenedictEquation(Person person){
        double callories = 0.0;

        switch (person.getSex()){
            case MALE:
                callories = 88.362 + (13.397 * person.getWeight()) + (4.799 * person.getHeight()) - (5.677 * person.getAge());
                return callories;

            case FEMALE:
                callories = 447.593 + (9.247 * person.getWeight()) + (3.098 * person.getHeight()) - (4.330 * person.getAge());
                return callories;
        }
        return callories;
    }



     /*Mifflin-St Jeor Equation:
For men:
BMR = 10W + 6.25H - 5A + 5
For women:
BMR = 10W + 6.25H - 5A - 161*/
     /*where:
W is body weight in kg
H is body height in cm
A is age */

    public static double calculateBMRMifflinStJeorEquation(Person person){
        double callories = 10 * person.getWeight() + 6.25 * person.getHeight() - 5 * person.getAge();

        switch (person.getSex()){
            case MALE:
                callories += 5;
                return callories;

            case FEMALE:
                callories -= 161;
                return callories;
        }
        return callories;
    }

}
