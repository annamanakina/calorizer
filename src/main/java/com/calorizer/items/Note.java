package com.calorizer.items;

import java.util.Objects;

public class Note extends Entity {

    private int id;
    private Product product;
    private int weight;
    private String date;
    private double proteinsPerWeight;
    private double fatsPerWeight;
    private double carbohydratesPerWeight;
    private double caloriesPerWeight;


    public Note() {
    }

    public Note(Product product, int weight, String date) {
        this.product = product;
        this.weight = weight;
        this.date = date;
        this.proteinsPerWeight = product.getProteins()*weight*0.01;
        this.fatsPerWeight = product.getFats()*weight*0.01;
        this.carbohydratesPerWeight = product.getCarbohydrates()*weight*0.01;
        this.caloriesPerWeight = product.getCalories()*weight*0.01;
    }

    public Note(int id, Product product, int weight, String date) {
        this.id = id;
        this.product = product;
        this.weight = weight;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id == note.id &&
                weight == note.weight &&
                product.equals(note.product) &&
                date.equals(note.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, weight, date);
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", product=" + product +
                ", weight=" + weight +
                ", date='" + date + '\'' +
                '}';
    }

    public double getProteinsPerWeight() {
        return proteinsPerWeight;
    }

    public double getFatsPerWeight() {
        return fatsPerWeight;
    }

    public double getCarbohydratesPerWeight() {
        return carbohydratesPerWeight;
    }

    public double getCaloriesPerWeight() {
        return caloriesPerWeight;
    }
}
