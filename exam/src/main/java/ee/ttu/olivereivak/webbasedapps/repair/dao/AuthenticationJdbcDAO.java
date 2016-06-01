package ee.ttu.olivereivak.webbasedapps.repair.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.gwizard.hibernate.DatabaseConfig;

import com.google.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import ee.ttu.olivereivak.webbasedapps.repair.entity.Authentication;

/**
 * This DAO class uses JDBC to call a stored procedure
 * (to fulfill exam requirements for the system).
 */

@Slf4j
public class AuthenticationJdbcDAO {

    private DatabaseConfig databaseConfig;

    private String driver;
    private String url;
    private String user;
    private String password;

    @Inject
    public AuthenticationJdbcDAO(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;

        loadProperties();
        registerConnector();
    }

    public void remove(Authentication authentication) {
        Connection connection = getConnection();
        if (connection == null) {
            return;
        }

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("SELECT logout(?)");
            statement.setString(1, authentication.getToken());
            ResultSet resultSet = statement.executeQuery();
            resultSet.close();
            log.debug("Deleting Authentication with token = " + authentication.getToken());
        } catch (SQLException e) {
            log.error("SQL Error", e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    private void registerConnector() {
        try {
            Class.forName(driver).newInstance();
        } catch (Exception e) {
            log.error("Error registering mysql connector", e);
        }
    }

    private Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            log.error("Error getting connection", e);
        }

        return connection;
    }

    private void rollback(Connection connection) {
        if (connection != null) {
            log.info("Transaction is being rolled back");
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error("Failed to roll back", ex);
            }
        }
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("Could not close connection", e);
        }
    }

    private void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                log.error("Could not close statement", e);
            }
        }
    }

    private void loadProperties() {
        driver = databaseConfig.getDriverClass();
        url = databaseConfig.getUrl();
        user = databaseConfig.getUser();
        password = databaseConfig.getPassword();
    }

}
