package com.calorizer.db;


import com.calorizer.products.Berries;

public class ProductFactory {
    public static final String BERRIES = "berries";
    public static final int BERRIES_ID = 15;
    public static final int EGGS = 16;
    public static final String DAYRIES = "dayries";
    public static final String MUSHROOMS = "mushrooms";


    public static IProductCreator createProduct(int id) {
        IProductCreator products = null;

        switch (id){
            case BERRIES_ID:
                products = new Berries();
                break;
           /*case EGGS:
                products = new Eggs();
                break;
             /*case DAYRIES:
                products = new Dairies();
            case MUSHROOMS:
                products = new Mushrooms();*/
        }

        return products;
    }
}
