package com.calorizer.calculation;

public enum ActivityMultiplier {
     /*Activity Multiplier:
Sedentary = BMR x 1.2 (little or no exercise, desk job)
Lightly active = BMR x 1.375 (light exercise/ sports 1-3 days/week)
Moderately active = BMR x 1.55 (moderate exercise/ sports 6-7 days/week)
Very active = BMR x 1.725 (hard exercise every day, or exercising 2 xs/day)
Extra active = BMR x 1.9 (hard exercise 2 or more times per day, or training for marathon, or triathlon, etc. */

    /*Sedentary.  Little to no regular exercise.
(factor 1.2)

Mild activity level: Intensive exercise for at least 20 minutes 1 to 3 times per week.
This may include such things as bicycling, jogging, basketball, swimming, skating, etc.
If you do not exercise regularly, but you maintain a busy life style that requires you to walk frequently for long periods,
you meet the requirements of this level.
(factor 1.375)

Moderate activity level: Intensive exercise for at least 30 to 60 minutes 3 to 4 times per week.
Any of the activities listed above will qualify.    (factor 1.55)

Heavy or (Labor-intensive) activity level: Intensive exercise for 60 minutes or greater 5 to 7 days per week
(see sample activities above).  Labor-intensive occupations also qualify for this level.
Labor-intensive occupations include construction work (brick laying, carpentry, general labor, etc.).
Also farming, landscape worker or similar occupations.     (factor 1.7)

Extreme level: Exceedingly active and/or very demanding activities:  Examples include:
(1) athlete with an almost unstoppable training schedule with multiple training sessions throughout the day
 (2) very demanding job, such as shoveling coal or working long hours on an assembly line.
 Generally, this level of activity is very difficult to achieve.  (factor 1.9)*/

    SEDENTARY(1.2, "Little to no regular exercise"),
    MILD(1.375, "Intensive exercise for at least 20 minutes 1 to 3 times per week.\n" +
            "This may include such things as bicycling, jogging, basketball, swimming, skating, etc.\n" +
            "If you do not exercise regularly, but you maintain a busy life style that requires you to walk frequently for long periods,\n" +
            "you meet the requirements of this level."),
    MODERATE(1.55, "Intensive exercise for at least 30 to 60 minutes 3 to 4 times per week.\n" +
            "Any of the activities listed above will qualify."),
    HEAVY(1.7, "Intensive exercise for 60 minutes or greater 5 to 7 days per week\n" +
            "(see sample activities above). Labor-intensive occupations also qualify for this level.\n" +
            "Labor-intensive occupations include construction work (brick laying, carpentry, general labor, etc.).\n" +
            "Also farming, landscape worker or similar occupations."),
    EXTREME(1.9, "Exceedingly active and/or very demanding activities:  Examples include:\n" +
            "(1) athlete with an almost unstoppable training schedule with multiple training sessions throughout the day\n" +
            " (2) very demanding job, such as shoveling coal or working long hours on an assembly line.\n" +
            " Generally, this level of activity is very difficult to achieve.");

    private double factor;
    private String description;

    ActivityMultiplier(double factor, String  description){
        this.factor = factor;
        this.description = description;
    }

    public double getFactor() {
        return factor;
    }

    public String getDescription() {
        return description;
    }

    public static ActivityMultiplier getElementBy(String title){
        for (ActivityMultiplier element : values()) {
            if (title.equals(element.name())) {
                return element;
            }
        }

        throw new IllegalArgumentException("No such element for " + title);
    }
}
