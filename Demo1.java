import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class Demo1 {
    private static final String DATABASE_DRIVER="com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL="jdbc:mysql://localhost:3306/Petistaan_jdbc";
    private static final String DATABASE_USER="root";
    private static final String DATABASE_PASSWORD="Gani@123";
    public static void main(String[] args) {
        Connection connection=null;
        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL,DATABASE_USER,DATABASE_PASSWORD);
            System.out.println("successfully connected");

        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }
        finally{
            try {
                if(Objects.nonNull(connection))
                 connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
