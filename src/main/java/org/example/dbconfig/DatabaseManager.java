package org.example.dbconfig;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseManager {

    Connection getConnection() throws SQLException;
    void releaseConnection(Connection connection) throws SQLException;
    void disconnect();


}
