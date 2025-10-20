package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {
    private static final String DATABASE_URL = "database.url";
    private static final String DATABASE_USER = "database.user";
    private static final String DATABASE_PASSWORD = "database.password";
    private static final PropertiesConfig propertiesConfig =PropertiesConfig.getInstance();

    private DatabaseConfig(){

    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                propertiesConfig.getProperty(DATABASE_URL),propertiesConfig.getProperty(DATABASE_USER),
                propertiesConfig.getProperty(DATABASE_PASSWORD));
    }
}
