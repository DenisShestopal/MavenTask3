import java.sql.*;
import java.util.Properties;

public class AppRunner {

    public static void main(String ...args) {

        DbProperties dbProperties = new DbProperties();
        Properties properties = dbProperties.readConfig();
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();) {
            String sqlQuery = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            System.out.println("Users list:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String admin = resultSet.getString("admin");
                String isBlocked = resultSet.getString("blocked");
                String userLogin = resultSet.getString("login");
                String userPassword = resultSet.getString("password");
                System.out.printf("ID: %s , Admin: %s, Is Blocked: %s, Login: %s, Password: %s}\n",
                        id, admin, isBlocked, userLogin, userPassword);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException exception1) {
            exception1.printStackTrace();
        } catch (Exception exception2) {
            exception2.printStackTrace();
        }
    }
}
