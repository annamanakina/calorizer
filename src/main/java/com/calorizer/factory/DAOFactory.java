package com.calorizer.factory;

import com.calorizer.db.*;

public abstract class DAOFactory {

    public static final int PRODUCT_DAO = 1;
    public static final int PRODUCT_CATEGORY_DAO = 2;
    public static final int NOTE_DAO = 3;
    public static final int PERSON_DAO = 3;
    public static final int USER_ACCOUNT_DAO = 3;

    public abstract ProductDAO getProductDAO();

    public abstract ProductCategoryDAO getProductCategoryDAO();

    public abstract NoteDAO getNoteDAO();

    //TODO
    //public abstract PersonDAO getPersonDAO();
    //public abstract UserAccountDAO getUserAccountDAO();


    public static AbstractDAO getDAOInstance(int whichFactory) {
        switch (whichFactory) {
            case PRODUCT_DAO:
                return new ProductDAO();
            case PRODUCT_CATEGORY_DAO:
                return new ProductCategoryDAO();
            case NOTE_DAO:
                return new NoteDAO();
            default:
                return null;
        }
    }
}
