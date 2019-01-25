package com.calorizer.items;

public enum Sex {
    MALE, FEMALE;

    public static Sex getValueBy(String title){
        for (Sex element : values()) {
            if (title.equals(element.name())) {
                return element;
            }
        }
        throw new IllegalArgumentException("No such element for " + title);
    }
}
