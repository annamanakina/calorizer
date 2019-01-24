package com.calorizer.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionPool {

    private ConnectionPool() {
    }

    private static ConnectionPool sInstance = null;

    public static ConnectionPool getInstance(){
        if (sInstance==null)
            sInstance = new ConnectionPool();
        return sInstance;
    }

    public Connection getConnection(){
        Context context;
        Connection c = null;
        try {
            context = (Context) (new InitialContext().lookup("java:comp/env"));
            DataSource ds = (DataSource) context.lookup("jdbc/calorizerdb");
            c = ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    public void close(Statement st){
        try{
            if (st != null){
                st.close();
            }
        } catch (SQLException e){

        }
    }

    public void close(Connection connection){
        try{
            if (connection != null){
                connection.close();
            }
        } catch (SQLException e){

        }
    }

}
