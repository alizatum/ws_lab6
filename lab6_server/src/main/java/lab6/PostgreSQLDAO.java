package lab6;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgreSQLDAO {
    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from persons");

            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int age = rs.getInt("age");
                int phone = rs.getInt("phone");
                String gender = rs.getString("gender");

                Person person = new Person(name, surname, age, phone, gender);
                persons.add(person);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return persons;
    }

    public List<Person> getPersons(String name, String surname, Integer age, Integer phone, String gender ) {
        List<Person> persons = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select * from persons p " +
                            "where (" + getQuoted(name) + " is NULL or " + getQuoted(name) + " =p.name) AND " +
                            "(" + getQuoted(surname) + " is NULL or " + getQuoted(surname) + " =p.surname) AND " +
                            "(" + age + " is NULL or " + age + " =p.age) AND " +
                            "(" + phone + " is NULL or " + phone + " =p.phone) AND " +
                            "(" + getQuoted(gender) + " is NULL or " + getQuoted(gender) + " =p.gender)");

            while (rs.next()) {
                String resName = rs.getString("name");
                String resSurname = rs.getString("surname");
                Integer resAge = rs.getInt("age");
                Integer resPhone = rs.getInt("phone");
                String resGender = rs.getString("gender");

                Person person = new Person(resName, resSurname, resAge, resPhone, resGender);
                persons.add(person);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return persons;
    }

    public Integer addPerson(String name, String surname, Integer age, Integer phone, String gender ) {
        Integer resId =null;
        List<Person> persons = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "insert into persons(name, surname, age, phone, gender) values ("+ getQuoted(name) +","+ getQuoted(surname) +","+ age +","+ phone +","+ getQuoted(gender) +") RETURNING id");
            while (rs.next()) {
                resId = rs.getInt("id");
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resId ;
    }

    public String deletePerson(Integer id ) {
        String status ="Error";
        try (Connection connection = ConnectionUtil.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "delete from persons where id=" + id +" RETURNING id");
            while (rs.next()) {
                Integer resId = rs.getInt("id");
                if (id == resId) { status="Success";}
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status ;
    }

    public String updatePerson(Integer id, String name, String surname, Integer age, Integer phone, String gender ) {
        String status ="Error";
        try (Connection connection = ConnectionUtil.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "UPDATE persons SET name = " + getQuoted(name) + ", surname = " + getQuoted(surname) +
                            ", age = " + age + ", phone = " + phone + ", gender = " + getQuoted(gender) +
                            " WHERE id = " + id + " RETURNING id");
            while (rs.next()) {
                Integer resId = rs.getInt("id");
                if (id == resId) { status="Success";}
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status ;
    }


    private String getQuoted(String o) {
        return o == null ? o : ("'" + o + "'");
    }
}