package com.calorizer.items;

import com.calorizer.calculation.ActivityMultiplier;

import java.util.Objects;

public class Person extends Entity{

    private Sex sex;


    private int age;
    private int weight;  //TODO maybe double?
    private int height;
    private ActivityMultiplier activityMultiplier;

    public Person(int id) {
        super(id);
    }

    public Person(int id, Sex sex) {
        super(id);
        this.sex = sex;
    }

    public Person(int id, Sex sex, int age) {
        super(id);
        this.sex = sex;
        this.age = age;
    }

    public Person(int id, Sex sex, int age, int weight) {
        super(id);
        this.sex = sex;
        this.age = age;
        this.weight = weight;
    }

    public Person(int id, Sex sex, int age, int weight, int height) {
        super(id);
        this.sex = sex;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public Person(int id, Sex sex, int age, int weight, int height, ActivityMultiplier activityMultiplier) {
        super(id);
        this.sex = sex;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.activityMultiplier = activityMultiplier;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ActivityMultiplier getActivityMultiplier() {
        return activityMultiplier;
    }

    public void setActivityMultiplier(ActivityMultiplier activityMultiplier) {
        this.activityMultiplier = activityMultiplier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sex, age, weight, height, activityMultiplier);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                weight == person.weight &&
                height == person.height &&
                sex == person.sex &&
                Objects.equals(activityMultiplier, person.activityMultiplier);
    }

    @Override
    public String toString() {
        return "Person{" +
                "sex=" + sex +
                ", age=" + age +
                ", weight=" + weight +
                ", height=" + height +
                ", activityMultiplier=" + activityMultiplier +
                '}';
    }
}
