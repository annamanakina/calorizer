package com.calorizer.db;

import com.calorizer.items.Product;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.calorizer.utils.Constant.*;

public class ProductDAO extends AbstractDAO<Integer, Product> {
    //public static final String SQL_SELECT_ALL_PRODUCTS = EXTRA_SELECT + EXTRA_SPACE;
    //public static final String SQL_SELECT_PRODUCTS_BY_ID = "SELECT * FROM calorizerdb.products where calorizerdb.products.id = ";
    //public static final String SQL_SELECT_ALL_PRODUCT_CATEGORY = "SELECT * FROM calorizerdb.product_category";


    private String queryAllFrom(String tableName) {
        return EXTRA_ALL_FROM + tableName + EXTRA_SEMI_COLON;
    }

    private String queryWhereBy(String tableName, String title) {
        return EXTRA_ALL_FROM + tableName + " where products.title like '"+title+"%';";
    }

    private String queryGetProductByTitle(String tableName, String title) {
        String str = EXTRA_ALL_FROM + tableName + " where products.title = '"+title+"';";
        System.out.println(str);
        return str;
                //EXTRA_ALL_FROM + tableName + " where products.title = "+title+";";
       /* return EXTRA_ALL_FROM + tableName + EXTRA_WHERE + tableName + DOT + title +
                EXTRA_LIKE + EXTRA_QUOTE + title + EXTRA_PERCENT + EXTRA_QUOTE + EXTRA_SEMI_COLON;*/
    }


    private String queryWhereBy(String tableName, int id) {
        return EXTRA_ALL_FROM + tableName + " where products.id = "+id +";";
        //EXTRA_ALL_FROM + tableName + EXTRA_WHERE + tableName + DOT + EXTRA_EQUAL + id + EXTRA_SEMI_COLON;
    }

    //getQueryGetProductByTitle(...)
    private String queryByProductCategoryId(String tableName, int id) {
        return EXTRA_ALL_FROM + tableName + " where products.product_category_id = "+id +";";
        //EXTRA_ALL_FROM + tableName + EXTRA_WHERE + tableName + DOT + EXTRA_EQUAL + id + EXTRA_SEMI_COLON;
    }

    private List<Product> query(String query){
        List<Product> products = new ArrayList<>();
        ConnectionPool pool= null;
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
                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            if (pool != null) {
                pool.close(st);
                pool.close(cn);
            }
        }

        return products;
    }


    private Product queryGetProduct(String query){
        ConnectionPool pool= null;
        Connection cn = null;
        Statement st = null;
        Product product = null;
        try {
            pool = ConnectionPool.getInstance();
            cn = pool.getConnection();
            st = cn.createStatement();

            ResultSet resultSet =
                    st.executeQuery(query);
            while (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt(ID));
                product.setTitle(resultSet.getString(TITLE));
                product.setProteins(resultSet.getDouble(PROTEINS));
                product.setFats(resultSet.getDouble(FATS));
                product.setCarbohydrates(resultSet.getDouble(CARBOHYDRATES));
                product.setCalories(resultSet.getDouble(CALORIES));
                product.setProductCategoryId(resultSet.getInt(PRODUCT_CATEGORY_ID));

           }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            if (pool != null) {
                pool.close(st);
                pool.close(cn);
            }
        }

        return product;
    }



    @Override
    public List<Product> getAll() {
        System.out.println("ProductDAO get all");
        return query(queryAllFrom(TABLE_PRODUCTS));
    }

    @Override
    public Product getById(Integer id) {
// queryGetProduct(queryGetProductById(TABLE_PRODUCTS, String.valueOf(id)));
        return null;
    }

    public Product getByTitle(String title) {

        return queryGetProduct(queryGetProductByTitle(TABLE_PRODUCTS, title));
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(Product object) {
        return false;
    }

    @Override
    public boolean create(Product product) {
        boolean isCreated = false;
        ConnectionPool pool = null;
        Connection cn = null;
        Statement st = null;
        try {
            pool = ConnectionPool.getInstance();
            cn = pool.getConnection();
            st = cn.createStatement();

            st.executeUpdate("insert into calorizerdb.products (title, proteins, fats, carbohydrates, calories, product_category_id) VALUES ('"
                      + product.getTitle() + "', "
                      + product.getProteins() + ", "
                      + product.getFats() + ", "
                      + product.getCarbohydrates()+ ", "
                      + product.getCalories() + ", "
                      + product.getProductCategoryId() + ");" );
            isCreated = true;
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            if (pool != null) {
                pool.close(st);
                pool.close(cn);
            }
        }
        return isCreated;
    }

    @Override
    public Product update(Product object) {
        return null;
    }

    /*public List<Product> getByTitle(String title) {
        return query(queryWhereBy(TABLE_PRODUCTS, title));
    }*/

    public List<Product> getByProductCategoryId(int id) {
        return query(queryByProductCategoryId(TABLE_PRODUCTS, id));
    }

}
