package com.calorizer.db;

import com.calorizer.calculation.Note;
import com.calorizer.items.Product;
import com.calorizer.utils.Constant;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import static com.calorizer.utils.Constant.*;

public class NoteDAO extends AbstractDAO<Integer, Note> {


    @Override
    public List<Note> getAll() {

        /*SELECT daily_products_notes.id, products.title, daily_products_notes.weight, products.proteins*daily_products_notes.weight/100 as p_proteins,
 products.fats*daily_products_notes.weight/100 as p_fats, products.carbohydrates*daily_products_notes.weight/100 as p_carbohydrates,
products.calories*daily_products_notes.weight/100 as p_calories FROM calorizerdb.daily_products_notes, calorizerdb.products where daily_products_notes.product_id = products.id;*/

       // System.out.println("NoteDAO get all");
        return query(queryGetAllFromNotes());
    }

    public static String queryGetAllFromNotes() {
        //SELECT * FROM calorizerdb.daily_products_notes, calorizerdb.products where daily_products_notes.product_id = products.id;
        return EXTRA_ALL_FROM + TABLE_NOTES +", " + TABLE_PRODUCTS
                +  EXTRA_WHERE + "daily_products_notes.product_id = products.id;";
    }

    public static String queryGetAllFromNotesByDate(String date) {
       // DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //String currentDate = formatter.format(new java.util.Date());
        //String queryLikeDate = currentDate.substring(0,10);
        //SELECT * FROM calorizerdb.daily_products_notes, calorizerdb.products where daily_products_notes.product_id = products.id;
        return EXTRA_ALL_FROM + TABLE_NOTES +", " + TABLE_PRODUCTS
                +  EXTRA_WHERE + "daily_products_notes.product_id = products.id and daily_products_notes.date like '"+date+"%';";
    }

    private List<Note> query(String query) {
       // System.out.println("query: " + query);
        List<Note> notes = new ArrayList<>();
        ConnectionPool pool = null;
        Connection cn = null;
        Statement st = null;
        try {
            pool = ConnectionPool.getInstance();
            cn = pool.getConnection();
            st = cn.createStatement();

            ResultSet resultSet =
                    st.executeQuery(query);
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt(ID));
                product.setTitle(resultSet.getString(TITLE));
                product.setProteins(resultSet.getDouble(PROTEINS));
                product.setFats(resultSet.getDouble(FATS));
                product.setCarbohydrates(resultSet.getDouble(CARBOHYDRATES));
                product.setCalories(resultSet.getDouble(CALORIES));
                product.setProductCategoryId(resultSet.getInt(PRODUCT_CATEGORY_ID));

                Note note = new Note(product, resultSet.getInt(PRODUCT_WEIGHT), resultSet.getTimestamp(PRODUCT_DATE).toString());
                note.setId(resultSet.getInt(ID));

               // note.setProduct(product);
               // note.setWeight(resultSet.getInt(PRODUCT_WEIGHT));
                //note.setDate(resultSet.getTimestamp(PRODUCT_DATE).toString()); //todo maybe convert  to another format


                notes.add(note);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            pool.close(st);
            pool.close(cn);
            // код возвращения экземпляра Connection в пул //todo????
        }
        return notes;
    }



    @Override
    public Note getById(Integer id) {
        String query = EXTRA_ALL_FROM + TABLE_NOTES +", " + TABLE_PRODUCTS +
                  EXTRA_WHERE + "daily_products_notes.product_id = products.id and daily_products_notes.id = " + id +";";
        //System.out.println("Note getById " + query);
        ConnectionPool pool = null;
        Connection cn = null;
        Statement st = null;
        Note note = null;
        try {
            pool = ConnectionPool.getInstance();
            cn = DataBaseHelper.getConnection();
            st = cn.createStatement();

            ResultSet resultSet =
                    st.executeQuery(query);
            while (resultSet.next()) {
                note = new Note();
                note.setId(resultSet.getInt(ID));
                Product product = new Product();
                product.setId(resultSet.getInt(ID));
                product.setTitle(resultSet.getString(TITLE));
                product.setProteins(resultSet.getDouble(PROTEINS));
                product.setFats(resultSet.getDouble(FATS));
                product.setCarbohydrates(resultSet.getDouble(CARBOHYDRATES));
                product.setCalories(resultSet.getDouble(CALORIES));
                product.setProductCategoryId(resultSet.getInt(PRODUCT_CATEGORY_ID));

                note.setProduct(product);
                note.setWeight(resultSet.getInt(PRODUCT_WEIGHT));
                note.setDate(resultSet.getTimestamp(PRODUCT_DATE).toString()); //todo maybe convert  to another format

            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            pool.close(st);
            pool.close(cn);
            // код возвращения экземпляра Connection в пул //todo????
        }

        return note;
    }

    @Override
    public boolean delete(Integer id) {
        ConnectionPool pool = null;
        Connection cn = null;
        Statement st = null;

        try {
            pool = ConnectionPool.getInstance();
            cn = pool.getConnection();
            st = cn.createStatement();
        String query = "DELETE FROM " +TABLE_NOTES + EXTRA_WHERE + TABLE_NOTES+".id = " + id +";";
        return st.execute(query);

        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            pool.close(st);
            pool.close(cn);
            // код возвращения экземпляра Connection в пул //todo????
        }
        return false;
    }

    @Override
    boolean delete(Note object) {
        return false;
    }

    @Override
    public boolean create(Note note) {
        ConnectionPool pool = null;
        boolean isCreated = false;
        Connection cn = null;
        Statement st = null;
        try {
            pool = ConnectionPool.getInstance();
            cn = pool.getConnection();
            st = cn.createStatement();

            String str = "insert into calorizerdb.daily_products_notes VALUES ("
                    + note.getId()+ ", "
                    + note.getProduct().getId() + ", '"
                    + note.getDate() + "', "
                    + note.getWeight() +", "
                    + note.getProteinsPerWeight() +", "
                    + note.getFatsPerWeight() + ", "
                    + note.getCarbohydratesPerWeight() + ", "
                    + note.getCaloriesPerWeight()
                    + ");";
            System.out.println(str);

            st.executeUpdate(str);
            isCreated = true;

        } catch (SQLException e) {
            System.err.println("NoteDAO create: SQL exception (request or table failed): " + e);
        } finally {
            pool.close(st);
            pool.close(cn);
        }

        //System.out.println(isCreated);
        return isCreated;
    }

    @Override
    Note update(Note object) {
        return null;
    }

    public List<Note> getByDate(String date){
        System.out.println("NoteDAO getByDate date: " + date);
        return query(queryGetAllFromNotesByDate(date));
    }
}
