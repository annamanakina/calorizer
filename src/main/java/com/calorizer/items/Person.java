package com.calorizer.items;

import java.util.Objects;

public class Person extends Entity{

    private String firstName;
    private String lastName;
    private Sex sex;
    private int age;
    private double weight;
    private int height;
    private Lifestyle lifestyle;

    public Person() {
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

    public Person(String firstName, String lastName, Sex sex, int age, double weight, int height, Lifestyle lifestyle) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.lifestyle = lifestyle;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Double.compare(person.weight, weight) == 0 &&
                height == person.height &&
                firstName.equals(person.firstName) &&
                lastName.equals(person.lastName) &&
                sex == person.sex &&
                Objects.equals(lifestyle, person.lifestyle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, sex, age, weight, height, lifestyle);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", weight=" + weight +
                ", height=" + height +
                ", lifestyle=" + lifestyle +
                '}';
    }
}
