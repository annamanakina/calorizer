package com.calorizer.db;

import com.calorizer.items.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


//*https://devcolibri.com/%D0%BA%D0%B0%D0%BA-%D1%81%D0%B2%D1%8F%D0%B7%D0%B0%D1%82%D1%8C-entity-%D0%B2-jpa//


public abstract class AbstractDAO<K, T extends Entity> {
    //todo create class, which creates object Candy
    abstract List<T> getAll();
    abstract T getById(K id);
    abstract boolean delete (K id);
    abstract boolean delete (T object);
    abstract boolean create (T object);
    abstract T update(T object);

    //K это ключ в таблице
    //класс также может содержать в себе методы ]по откр/закрытию соединения с базой данных

    public void close(Statement st){
        try{
            if (st != null){
                st.close();
            }
        } catch (SQLException e){
            //лог о невозможности закрытия Statement
        }
    }

    public void close(Connection connection){
        try{
            if (connection != null){
                connection.close();
            }
        } catch (SQLException e){
            //генерация исключения, т.к. нарушается работа пула
        }
    }

}
