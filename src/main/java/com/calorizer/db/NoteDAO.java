package com.calorizer.db;

import com.calorizer.items.Note;
import com.calorizer.items.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.calorizer.utils.Constant.*;

public class NoteDAO extends AbstractDAO<Integer, Note> {


    @Override
    public List<Note> getAll() {
        return query(queryGetAllFromNotes());
    }

    public static String queryGetAllFromNotes() {
        //SELECT * FROM calorizerdb.daily_products_notes, calorizerdb.products where daily_products_notes.product_id = products.id;
        return EXTRA_ALL_FROM + TABLE_NOTES +", " + TABLE_PRODUCTS
                +  EXTRA_WHERE + "daily_products_notes.product_id = products.id;";
    }

    public static String queryGetAllFromNotesByDate(String date) {
        return EXTRA_ALL_FROM + TABLE_NOTES +", " + TABLE_PRODUCTS
                +  EXTRA_WHERE + "daily_products_notes.product_id = products.id and daily_products_notes.date like '"+date+"%';";
    }

    private List<Note> query(String query) {
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
                notes.add(note);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            pool.close(st);
            pool.close(cn);
        }
        return notes;
    }

    @Override
    public Note getById(Integer id) {
        String query = EXTRA_ALL_FROM + TABLE_NOTES +", " + TABLE_PRODUCTS +
                  EXTRA_WHERE + "daily_products_notes.product_id = products.id and daily_products_notes.id = " + id +";";
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
                note.setDate(resultSet.getTimestamp(PRODUCT_DATE).toString());

            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            pool.close(st);
            pool.close(cn);
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
        return isCreated;
    }

    @Override
    Note update(Note object) {
        return null;
    }

    public List<Note> getByDate(String date){
        return query(queryGetAllFromNotesByDate(date));
    }
}
