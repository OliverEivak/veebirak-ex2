package ee.ttu.olivereivak.veebirakex2.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ee.ttu.olivereivak.veebirakex2.model.Star;

public class StarDAO {

    private static final Logger log = LogManager.getLogger(StarDAO.class);

    private String driver;
    private String url;
    private String user;
    private String password;

    public StarDAO() {
        loadProperties();
        registerConnector();
    }

    public Star[] findAll() {
        Connection connection = getConnection();
        if (connection == null) {
            return null;
        }

        List<Star> starList = new ArrayList<>();

        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Star");

            while (resultSet.next()) {
                starList.add(getStarFromResultSet(resultSet));
            }

            resultSet.close();
        } catch (SQLException e) {
            log.error("SQL Error", e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        Star[] stars = new Star[starList.size()];
        for (int i = 0; i < starList.size(); i++) {
            stars[i] = starList.get(i);
        }

        return stars;
    }

    public Star findById(Long id) {
        Connection connection = getConnection();
        if (connection == null) {
            return null;
        }

        Star star = null;

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM Star s WHERE s.id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                star = getStarFromResultSet(resultSet);
            }

            resultSet.close();
        } catch (SQLException e) {
            log.error("SQL Error", e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return star;
    }

    public Star update(Star star) {
        Connection connection = getConnection();
        if (connection == null) {
            return null;
        }

        if (star.getId() == null)
            return null;

        PreparedStatement statement = null;

        try {
            connection.setAutoCommit(false);

            statement = connection.prepareStatement("UPDATE Star s SET s.commonName = ?, s.distanceInLightYears = ?, "
                    + "s.description = ? WHERE s.id = ? LIMIT 1");
            statement.setString(1, star.getCommonName());
            statement.setBigDecimal(2, star.getDistanceInLightYears());
            statement.setString(3, star.getDescription());
            statement.setLong(4, star.getId());

            int rowsModified = statement.executeUpdate();
            log.debug(String.format("Update modified %s rows", rowsModified));

            connection.commit();
        } catch (SQLException e) {
            log.error("SQL Error", e);
            rollback(connection);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return findById(star.getId());
    }

    private Star getStarFromResultSet(ResultSet resultSet) throws SQLException {
        Star star = new Star();

        star.setId(resultSet.getLong("id"));
        star.setCommonName(resultSet.getString("commonName"));
        star.setDistanceInLightYears(resultSet.getBigDecimal("distanceInLightYears"));
        star.setDescription(resultSet.getString("description"));
        return star;
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
        Properties prop = new Properties();

        try {
            prop.load(StarDAO.class.getClassLoader().getResourceAsStream("DBConnection.properties"));
        } catch (IOException e) {
            log.error("Failed to load properties", e);
        }

        driver = prop.getProperty("driver");
        url = prop.getProperty("url");
        user = prop.getProperty("user");
        password = prop.getProperty("password");
    }

}
