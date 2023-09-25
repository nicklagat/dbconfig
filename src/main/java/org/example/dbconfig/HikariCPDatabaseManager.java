package org.example.dbconfig;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class HikariCPDatabaseManager implements DatabaseManager {

    private static final HikariDataSource dataSource = new HikariDataSource();


    // configuring the connection pool

    static {

        if (ConfigurationReader.readDatabaseConfig() != null) {
            dataSource.setDriverClassName(Objects.requireNonNull(ConfigurationReader.readDatabaseConfig()).getDriverClassName());
            dataSource.setUsername(Objects.requireNonNull(ConfigurationReader.readDatabaseConfig()).getUsername());
            dataSource.setPassword(Objects.requireNonNull(ConfigurationReader.readDatabaseConfig()).getPassword());
            dataSource.setJdbcUrl(Objects.requireNonNull(ConfigurationReader.readDatabaseConfig()).getUrl());


            dataSource.setMaximumPoolSize(10);
            dataSource.setMaxLifetime(5);
            dataSource.setInitializationFailTimeout(5);


        } else {
            throw new RuntimeException("Failed to read the database configurations ");
        }

    }


    // private constructor to prevent instantiation
    // this ensures that only one object is created at a tine

    HikariCPDatabaseManager() {
    }

    ;


    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void releaseConnection(Connection connection) throws SQLException {

        if (connection != null) {
            connection.close();
        }

    }

    @Override
    public void disconnect() {
        dataSource.close();
    }
}
