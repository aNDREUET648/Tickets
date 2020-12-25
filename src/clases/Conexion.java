package clases;
import java.sql.*;
import java.util.TimeZone;
/*
 *      @author aNDREUET
 */
public class Conexion {

    // Declaramos los datos de Conexion a la bd
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASS = "Andreuet.123d";
    private static final String URL = "jdbc:mysql://localhost:3306/Tickets?serverTimezone=" + TimeZone.getDefault().getID();
    //private static final String URL = "jdbc:mysql://localhost:3306/Tickets";

    public static Connection conector() {

        try {
            // Al cargarse la clase, se registra como driver JDBC.
            Class.forName(DRIVER);
            // Método que establece la conexión  la BD
            Connection conectar = DriverManager.getConnection(URL, USER, PASS);
            return conectar;
        } 
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error de conexion" + e);
        }
        return (null);
    }
}
