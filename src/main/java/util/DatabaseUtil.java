package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class DatabaseUtil {
    // Singleton instance of DatabaseUtil
    private static volatile DatabaseUtil instance;

    // Database connection object
    private Connection connection;

    // Logger instance for logging events
    private static final Logger logger = LogManager.getLogger(DatabaseUtil.class);

    // Database connection parameters
    private final String url;
    private final String username;
    private final String password;

    // Private constructor to prevent instantiation from outside
    private DatabaseUtil() {
        try {
            Properties properties = new Properties();
            // Load the database configuration from the properties file
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("db.properties");
            if (inputStream != null) {
                properties.load(inputStream);
                // Build the database URL from properties
                String host = properties.getProperty("db.host");
                String port = properties.getProperty("db.port");
                String dbName = properties.getProperty("db.name");
                url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
                username = properties.getProperty("db.username");
                password = properties.getProperty("db.password");
            } else {
                // Log error and throw exception if properties file is not found
                logger.error("db.properties file not found");
                throw new RuntimeException("db.properties file not found");
            }

            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the database connection
            this.connection = DriverManager.getConnection(url, username, password);
            logger.info("Database connection established");
        } catch (ClassNotFoundException | SQLException | IOException e) {
            // Log and throw exception if an error occurs while establishing the connection
            logger.error("Error connecting to database", e);
            throw new RuntimeException("Error connecting to database", e);
        }
    }

    // Method to retrieve the singleton instance of DatabaseUtil
    public static DatabaseUtil getInstance() {
        if (instance == null) {
            synchronized (DatabaseUtil.class) {
                if (instance == null) {
                    instance = new DatabaseUtil();// Initialize the instance if it's null
                }
            }
        }
        return instance;
    }

    // Method to retrieve the database connection
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // Re-establish the connection if it was closed or null
                connection = DriverManager.getConnection(url, username, password);
                logger.info("Database connection established");
            }
        } catch (SQLException e) {
            // Log and throw exception if an error occurs while re-establishing the connection
            logger.error("Error re-establishing the database connection", e);
            throw new RuntimeException("Error re-establishing the database connection", e);
        }
        return connection;
    }

}
