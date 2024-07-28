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
    private static volatile DatabaseUtil instance;
    private Connection connection;

    private static final Logger logger = LogManager.getLogger(DatabaseUtil.class);

    private final String url;

    private final String username;

    private final String password;

    private DatabaseUtil() {
        try {
            Properties properties = new Properties();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("db.properties");
            if (inputStream != null) {
                properties.load(inputStream);
                String host = properties.getProperty("db.host");
                String port = properties.getProperty("db.port");
                String dbName = properties.getProperty("db.name");
                url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
                username = properties.getProperty("db.username");
                password = properties.getProperty("db.password");
            } else {
                logger.error("db.properties file not found");
                throw new RuntimeException("db.properties file not found");
            }

            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
            logger.info("Database connection established");
        } catch (ClassNotFoundException | SQLException | IOException e) {
            logger.error("Error connecting to database", e);
            throw new RuntimeException("Error connecting to database", e);
        }
    }


    public static DatabaseUtil getInstance() {
        if (instance == null) {
            synchronized (DatabaseUtil.class) {
                if (instance == null) {
                    instance = new DatabaseUtil();
                }
            }
        }
        return instance;
    }


    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, username, password);
                logger.info("Database connection established");
            }
        } catch (SQLException e) {
            logger.error("Error re-establishing the database connection", e);
            throw new RuntimeException("Error re-establishing the database connection", e);
        }
        return connection;
    }

}
