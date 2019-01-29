package com.calorizer.db;


import com.calorizer.items.MetabolicRate;
import com.calorizer.items.Person;
import com.calorizer.items.Sex;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.calorizer.utils.Constant.*;

public class PersonDAO extends AbstractDAO<Integer, Person> {

    @Override
    public List<Person> getAll() {
        return null;
                //query(queryAllFrom(TABLE_PERSON));
    }

    @Override
    public Person getById(Integer id) {
        String query = EXTRA_ALL_FROM + TABLE_PERSON +EXTRA_WHERE + "person.id = " + id +";";

        ConnectionPool pool = null;
        Connection cn = null;
        Statement st = null;
        Person person = null;
        try {
            pool = ConnectionPool.getInstance();
            cn = DataBaseHelper.getConnection();
            st = cn.createStatement();

            ResultSet resultSet =
                    st.executeQuery(query);
            while (resultSet.next()) {
                person = new Person();
                person.setId(resultSet.getInt(ID));
                person.setFirstName(resultSet.getString(PERSON_FIRST_NAME));
                person.setLastName(resultSet.getString(PERSON_LAST_NAME));
                person.setAge(resultSet.getInt(PERSON_AGE));
                person.setHeight(resultSet.getInt(PERSON_HEIGHT));
                person.setWeight(resultSet.getInt(PERSON_WEIGHT));
                person.setSex(Sex.getValueBy(resultSet.getString(PERSON_SEX)));
                MetabolicRate mr = new MetabolicRate();
                mr.setBodyMassIndex(resultSet.getDouble(PERSON_BMI));
                mr.setDailyCaloriesIntakes(resultSet.getDouble(PERSON_DAILY_CALORIE_INTAKE));
                mr.setBasalMetabolicRate(resultSet.getDouble(PERSON_BMR));
                person.setMetabolicRate(mr);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            if (pool != null) {
                pool.close(st);
                pool.close(cn);
            }
        }

        return person;
    }

    @Override
    boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(Person object) {
        return false;
    }

    @Override
    public boolean create(Person person) {
        ConnectionPool pool = null;
        boolean isCreated = false;
        Connection cn = null;
        Statement st = null;
        try {
            pool = ConnectionPool.getInstance();
            cn = pool.getConnection();
            st = cn.createStatement();

            String str = "insert into calorizerdb.person ("+ PERSON_AGE + ", "
            + PERSON_HEIGHT+ ", " +PERSON_WEIGHT+ ", " + PERSON_SEX +", "
            + PERSON_BMI+ ", " + PERSON_DAILY_CALORIE_INTAKE + ", " + PERSON_BMR+" ) VALUES ("
                    + person.getAge() + ", "
                    + person.getHeight() +", "
                    + person.getWeight() +", '"
                    + person.getSex() + "', "
                    + person.getMetabolicRate().getBodyMassIndex() + ", "
                    + person.getMetabolicRate().getDailyCaloriesIntakes() + ", "
                    + person.getMetabolicRate().getBasalMetabolicRate()+ ");";
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

    public String createPerson(Person person) {
        ConnectionPool pool = null;
        Connection cn = null;

        Statement st = null;
        ResultSet resultSet;
        String rowId =null;
        try {
            pool = ConnectionPool.getInstance();
            cn = pool.getConnection();
            st = cn.createStatement();

            String str = "insert into calorizerdb.person ("+ PERSON_FIRST_NAME + ", "
                    + PERSON_LAST_NAME+ " ) VALUES ('"
                    + person.getFirstName() + "', '"
                    + person.getLastName() + "');";
            //pool.close(st);
            st.executeUpdate(str);

           // Statement st1 = cn.createStatement();
            resultSet = st.executeQuery("select LAST_INSERT_ID() as last_id from calorizerdb.person;");

            while (resultSet.next()){
                rowId = resultSet.getString("last_id");
            }
        } catch (SQLException e) {
            System.err.println("PersonDAO create: SQL exception (request or table failed): " + e);
        } finally {
            pool.close(st);
            pool.close(cn);
        }

        return rowId;
    }

    @Override
    public Person update(Person object) {
        return null;
    }

    private List<Person> query(String query){
        List<Person> persons = new ArrayList<>();
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
                Person person = new Person();
                person.setId(resultSet.getInt(ID));
                person.setFirstName(resultSet.getString(PERSON_FIRST_NAME));
                person.setLastName(resultSet.getString(PERSON_LAST_NAME));
                person.setAge(resultSet.getInt(PERSON_AGE));
                person.setHeight(resultSet.getInt(PERSON_HEIGHT));
               /* person.setWeight(resultSet.getDouble(PERSON_WEIGHT));
                person.setSex(resultSet.getString(PERSON_SEX).equals(Sex.MALE.name()) ? Sex.MALE : Sex.FEMALE);
                MetabolicRate mr = new MetabolicRate();
                person.setMetabolicRate()
                persons.add(person);*/
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            if (pool != null) {
                pool.close(st);
                pool.close(cn);
            }
        }

        return persons;
    }
}
