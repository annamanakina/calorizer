package com.calorizer.items;

import java.io.Serializable;
import java.util.Objects;

public class ProductCategory extends Entity implements Serializable {
    private int id;
    private String title;

    public ProductCategory() {
    }

    public ProductCategory(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategory that = (ProductCategory) o;
        return id == that.id &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
