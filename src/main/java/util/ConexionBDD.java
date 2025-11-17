package util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBDD {
    private static String url = "jdbc:mysql://localhost:3306/SistemaVentas?serverTimezone=UTC";
    private static String username = "root";
    private static String password = "12345";

    public static Connection getConnection()throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }
}
