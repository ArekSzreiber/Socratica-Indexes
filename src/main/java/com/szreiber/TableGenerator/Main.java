package com.szreiber.TableGenerator;

import java.sql.*;


public class Main {
    private final String url = System.getenv("DB_URL");
    private final String user = System.getenv("DB_USERNAME");
    private final String password = System.getenv("DB_PASSWORD");


    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }


    public void insertPersons() {
        String SQL = "INSERT INTO person(first_name,last_name, birthday) "
                + "VALUES(?,?,?)";
        try (
                Connection conn = connect();
                PreparedStatement statement = conn.prepareStatement(SQL);) {
            Person person;
            int range = 100_000_000;
            for (int i = 1; i <= range; i++) {
                person = new Person();
                statement.setString(1, person.getFirstName());
                statement.setString(2, person.getLastName());
                statement.setDate(3, java.sql.Date.valueOf(person.getBirthday()));

                statement.addBatch();
                if (i % 10_000 == 0 || i == range) {
                    statement.executeBatch();
                    System.out.print('\r');
                    System.out.print(i);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static void main(String[] args) throws SQLException {
        Main main = new Main();
        main.insertPersons();
    }
}
