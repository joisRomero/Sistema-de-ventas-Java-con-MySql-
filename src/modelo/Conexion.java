package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author joisRomero
 */

public class Conexion {
    public static Connection getConnection() throws Exception{
        Connection conexion = null;
        String url = "jdbc:mysql://localhost:3306/bd_sistema_ventas?autoReconnect=true";
        String user = "root";
        String pass = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        return conexion;
    }
}
