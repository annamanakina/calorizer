package com.calorizer.db;

import com.calorizer.items.UserAccount;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static com.calorizer.utils.Constant.*;

public class UserAccountDAO extends AbstractDAO<Integer, UserAccount>{

    public UserAccount findUser(String username, String password) {
        String query = EXTRA_ALL_FROM + TABLE_USER_ACCOUNT + EXTRA_WHERE + USER_NAME + " = '" +username+ "' and " + USER_PASSWORD + " = '" +password +"';";
        System.out.println("findUser " + query);
        ConnectionPool pool = null;
        Connection cn = null;
        Statement st = null;
        UserAccount userAccount = null;
        try {
            pool = ConnectionPool.getInstance();
            cn = DataBaseHelper.getConnection();
            st = cn.createStatement();

            ResultSet resultSet =
                    st.executeQuery(query);
            while (resultSet.next()) {
                userAccount = new UserAccount(resultSet.getInt(ID), resultSet.getString(USER_NAME), resultSet.getString(USER_PASSWORD), resultSet.getString(USER_PERSON_ID));
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            pool.close(st);
            pool.close(cn);
        }
        return userAccount;
    }

    public UserAccount findUser(String username) {
        String query = EXTRA_ALL_FROM + TABLE_USER_ACCOUNT + EXTRA_WHERE + USER_NAME + " = '" +username +"';";
        System.out.println("findUser " + query);
        ConnectionPool pool = null;
        Connection cn = null;
        Statement st = null;
        UserAccount userAccount = null;
        try {
            pool = ConnectionPool.getInstance();
            cn = DataBaseHelper.getConnection();
            st = cn.createStatement();

            ResultSet resultSet =
                    st.executeQuery(query);
            while (resultSet.next()) {
                userAccount = new UserAccount(resultSet.getInt(ID), resultSet.getString(USER_NAME));
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            pool.close(st);
            pool.close(cn);
        }
        return userAccount;
    }


    @Override
    public List<UserAccount> getAll() {
        return null;
    }

    @Override
    public UserAccount getById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(UserAccount object) {
        return false;
    }

    @Override
    public boolean create(UserAccount userAccount) {
        ConnectionPool pool = null;
        boolean isCreated = false;
        Connection cn = null;
        Statement st = null;
        try {
            pool = ConnectionPool.getInstance();
            cn = pool.getConnection();
            st = cn.createStatement();

            String str = "insert into calorizerdb.user_account ("+USER_NAME + ", "
                    + USER_PASSWORD+ ", " +USER_PERSON_ID  +") VALUES ('"
                    + userAccount.getUserName() + "', '"
                    + userAccount.getPassword() + "', "
                    + userAccount.getPersonId() +");";
            System.out.println("UserAccount: "+str);

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
    public UserAccount update(UserAccount object) {
        return null;
    }
}
