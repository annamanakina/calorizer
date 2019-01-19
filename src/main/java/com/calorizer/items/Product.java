package com.calorizer.items;

public class Product extends Entity{

    private int id;
    private String title;
    private double proteins;
    private double fats;
    private double carbohydrates;
    private double calories;
    private double productCategoryId;
    //todo ProductCategory object?


    public Product() {
    }

    public Product(String title, double proteins, double fats, double carbohydrates, double calories, double productCategoryId) {
        this.title = title;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.calories = calories;
        this.productCategoryId = productCategoryId;
    }

    //TODO расписать equals hashcode to string


    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(double productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates +
                ", calories=" + calories +
                ", productCategoryId=" + productCategoryId +
                '}';
    }
}
