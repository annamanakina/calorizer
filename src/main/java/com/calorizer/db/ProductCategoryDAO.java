package com.calorizer.db;

import com.calorizer.items.ProductCategory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.calorizer.utils.Constant.*;

public class ProductCategoryDAO extends AbstractDAO<Integer, ProductCategory> {
    public static final String SQL_SELECT_ALL_PRODUCT_CATEGORIES = "SELECT * FROM calorizerdb.product_category";

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> productCategories = new ArrayList<>();
        ConnectionPool pool = null;
        Connection cn = null;
        Statement st = null;
        try {
            pool = ConnectionPool.getInstance();
            cn =  pool.getConnection();//ConnectionPool.getConnection();
            st = cn.createStatement();
            ResultSet resultSet =
                    st.executeQuery(SQL_SELECT_ALL_PRODUCT_CATEGORIES);
            while (resultSet.next()) {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setId(resultSet.getInt("id"));
                productCategory.setTitle(resultSet.getString("title"));
                productCategories.add(productCategory);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            pool.close(st);
            pool.close(cn);
            // код возвращения экземпляра Connection в пул //todo????
        }
        return productCategories;


    }

    @Override
    ProductCategory getById(Integer id) {
        return null;
    }

    @Override
    boolean delete(Integer id) {
        return false;
    }

    @Override
    boolean delete(ProductCategory productCategory) {
        return false;
    }

    @Override
    boolean create(ProductCategory productCategory) {
       /* boolean isCreated = false;
        Connection cn = null;
        Statement st = null;
        try {
            cn = DataBaseHelper.getConnection();
            st = cn.createStatement();
        st.executeUpdate("insert into " + TABLE_PRODUCT_CATEGORY + " (id, title) VALUES ("
                    + productCategory.getTitle() + ");");

            isCreated = true;
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
            close(cn);
        }
        return isCreated;*/
       return false;
    }

    public ProductCategory createCategory(String title) {
        ConnectionPool pool = null;
        Connection cn = null;
        Statement st = null;
        ProductCategory category = null;
        try {
            pool = ConnectionPool.getInstance();
            cn =  pool.getConnection();
            st = cn.createStatement();
            int resultValue = st.executeUpdate("insert into " + TABLE_PRODUCT_CATEGORY + " (title) VALUES ('"
                            + title + "') ON DUPLICATE KEY UPDATE product_category.title = '" + title + "';");
            if (resultValue != 0) {
                ResultSet resultSet = st.executeQuery(queryGetCategoryByTitle(TABLE_PRODUCT_CATEGORY, title));
                while (resultSet.next()) {
                    category = new ProductCategory();
                    category.setId(resultSet.getInt(ID));
                    category.setTitle(resultSet.getString(TITLE));
                }
            }

        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            pool.close(st);
            pool.close(cn);
        }
        return category;
    }

    @Override
    ProductCategory update(ProductCategory object) {
        return null;
    }

    public ProductCategory getByTitle(String title) {

        return queryGetCategory(queryGetCategoryByTitle(TABLE_PRODUCT_CATEGORY, title));

    }

    private ProductCategory queryGetCategory(String query) {
        ConnectionPool pool = null;
        Connection cn = null;
        Statement st = null;
        ProductCategory category = null;
        try {
            pool = ConnectionPool.getInstance();
            cn =  pool.getConnection();
            st = cn.createStatement();

            ResultSet resultSet =
                    st.executeQuery(query);
            while (resultSet.next()) {
                category = new ProductCategory();
                category.setId(resultSet.getInt(ID));
                category.setTitle(resultSet.getString(TITLE));
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            pool.close(st);
            pool.close(cn);

        }
        return category;
    }

    private String queryGetCategoryByTitle(String tableName, String title) {
        String str = EXTRA_ALL_FROM + tableName + " where product_category.title = '"+title+"';";
        System.out.println(str);
        return str;
    }


    /*// собственный метод DAO
public Abonent findAbonentByLastName(String name) {
Abonent abonent = new Abonent();
Connection cn = null;
PreparedStatement st = null;
try {
cn = ConnectionPool.getConnection();
st =
cn.prepareStatement(SQL_SELECT_ABONENT_BY_LASTNAME);
st.setString(1, name);
ResultSet resultSet =st.executeQuery();
resultSet.next();
abonent.setId(resultSet.getInt("idphonebook"));
abonent.setPhone(resultSet.getInt("phone"));
abonent.setLastName(name);
} catch (SQLException e) {
System.err.println("SQL exception (request or table failed): " + e);
} finally {
close(st);
// код возвращения экземпляра Connection в пул
}
return abonent;
}*/
}
