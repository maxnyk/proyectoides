package proyectoides;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectbd {
    private String url;
    private String username;
    private String password;

    public connectbd(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}