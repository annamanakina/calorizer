package com.calorizer.db;


import com.calorizer.items.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DataBaseHelper {

    // JDBC driver name and com.calorizer.access.database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/calorizerdb?useUnicode=true" +
            "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false" +
            "&serverTimezone=UTC&characterEncoding=UTF-8";
    //можно еще этот атрибут добавитьесли база данных не создана вообще
    //тогда она создается
    //create=true

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "F$3Kdn*6Q";

    //Подключение к БД Mysql:
    //Class.forName("com.mysql.jdbc.Driver");
    //Connection conn = DriverManager.getConnection("jdbc:mysql://hostname:port/dbname","username", "password");
    //conn.close();

    public static ArrayList<Product>  getBerries(){
        Connection conn = null;
        Statement stmt = null;
        ArrayList<Product> list = new ArrayList<>();

        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to com.calorizer.access.database...");
            conn = DriverManager.getConnection(DB_URL,USER, PASS); //

            //STEP 4: Execute a query
            //System.out.println("Creating com.calorizer.access.database...");
            stmt = conn.createStatement();

            //String sql = "CREATE DATABASE STUDENTS";
            ResultSet resultSet = stmt.executeQuery("select product_category.category, title, proteins, fats, carbohydrates, \n" +
                    "calories from calorizerdb.product_category, calorizerdb.type_berries where \n" +
                    "product_category.id = type_berries.product_category_id;");
            System.out.println("Database executeQuery successfully...");

            while(resultSet.next()){
                Product product = new Product();
                product.setTitle(resultSet.getString(2));
                product.setProteins((int)resultSet.getDouble(3));
                product.setFats((int)resultSet.getDouble(4));
                product.setCarbohydrates((int)resultSet.getDouble(5));
                product.setCalories((int)resultSet.getDouble(6));
                list.add(product);

                /*System.out.println("--------------------");
                //System.out.printf("%s");
                System.out.println("1: "+resultSet.getString(1)+ "\t"
                        +"2: "+resultSet.getString(2) + "\t"
                        +"3: "+resultSet.getString(3)+ "\t"
                        +"4: "+resultSet.getString(4)+ "\t"
                        +"5: "+resultSet.getString(5)+ "\t"
                        +"6: "+resultSet.getString(6));*/
            }
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return list;
    }


    //todo use this or lower one
    //соединение с БД.
    /*private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }*/


    public static Connection getConnection() {
        Connection conn = null;
        //STEP 2: Register JDBC driver
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } /*finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }*/
        return conn;

    }

    //создавать в БД таблицу

    /*private static void createDbUserTable() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;

        String createTableSQL = "CREATE TABLE DBUSER("
                + "USER_ID NUMBER(5) NOT NULL, "
                + "USERNAME VARCHAR(20) NOT NULL, "
                + "CREATED_BY VARCHAR(20) NOT NULL, "
                + "CREATED_DATE DATE NOT NULL, " + "PRIMARY KEY (USER_ID) "
                + ")";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            // выполнить SQL запрос
            statement.execute(createTableSQL);
            System.out.println("Table \"dbuser\" is created!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }*/


    public Properties setDBProperties(){
        Properties properties = new Properties();
        properties.put("user", USER);
        properties.put("password", PASS);
        //TODO где посмотреть этот атрибут
        properties.put("characterEncoding", "UTF-8");
        //properties.put("useUnicode", "true");
        return properties;
    }
}
