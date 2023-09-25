package org.example.dbconfig;

import java.sql.*;

public class ConnectionTest {

    public static void main(String[] args) {

        HikariCPDatabaseManager databaseManager = new HikariCPDatabaseManager();
        try (Connection connection = databaseManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT  * FROM ev_models LIMIT 5");


            while (resultSet.next()) {
                String evName = resultSet.getString("electric_car_name");
                String evManufacturer = resultSet.getString("electric_car_manufacturer");
                System.out.println(evName + "  " + evManufacturer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
