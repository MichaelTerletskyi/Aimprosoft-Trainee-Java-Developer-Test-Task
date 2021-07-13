package repositories.utils;

import exceptions.IncorrectConfigFieldException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

/**
 * @Create 10/26/2020
 */

public class JDBCUtil {

    public static Connection getConnection() {
        PropertiesDispatcher props = new PropertiesDispatcher();
        Connection connection = null;
        try {
            Class.forName(props.getPropValue("databaseDriver"));
            connection = DriverManager.getConnection(
                    props.getPropValue("databaseURL"),
                    props.getPropValue("username"),
                    props.getPropValue("password"));
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
            System.err.println("Connection error");
        }
        return connection;
    }

    private static class PropertiesDispatcher {
        InputStream inputStream;
        String properties = null;

        String getPropValue(String param) throws IOException {
            try {
                Properties prop = new Properties();
                String propFileName = "databaseConfig.properties";
                inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
                if (inputStream != null) {
                    prop.load(inputStream);
                } else {
                    throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
                }
                properties = prop.getProperty(param);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                inputStream.close();
            }
            return Optional.ofNullable(properties).orElseThrow(() -> new IncorrectConfigFieldException("Incorrect param filed name"));
        }
    }
}