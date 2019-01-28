package com.calorizer.items;

import java.util.Objects;

public class Person extends Entity{

    private String firstName;
    private String lastName;
    private Sex sex;
    private int age;
    private int weight;
    private int height;
    private MetabolicRate metabolicRate;

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

    public Person( Sex sex, int age, int weight, int height) {
        this.sex = sex;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public Person( int id, Sex sex, int age, int weight, int height) {
        super(id);
        this.sex = sex;
        this.age = age;
        this.weight = weight;
        this.height = height;
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

    public boolean setAge(int age) {
        if (age < 0)
            this.age = 0;
        else
            this.age = age;
        return age >= 0;
    }

    public int getWeight() {
        return weight;
    }

    public boolean setWeight(int weight) {
        if (weight < 0)
            this.weight = 0;
        else
            this.weight = weight;
        return weight >= 0;
    }

    public int getHeight() {
        return height;
    }

    public boolean setHeight(int height) {
        if (height < 0)
            this.height = 0;
        else
            this.height = height;
        return height >= 0;
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

    public MetabolicRate getMetabolicRate() {
        return metabolicRate;
    }

    public void setMetabolicRate(MetabolicRate metabolicRate) {

       this.metabolicRate = metabolicRate;
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
                Objects.equals(metabolicRate, person.metabolicRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, sex, age, weight, height, metabolicRate);
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
                ", metabolicRate=" + metabolicRate +
                '}';
    }
}
