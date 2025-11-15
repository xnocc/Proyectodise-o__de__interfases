package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String USER = "root";
    private static final String PASS = "xnocc"; // si tu root tiene contraseña, escríbela aquí
    private static final String SERVER = "localhost:3306";
    private static final String BD = "tienda";

    public Connection crearConexion() {
        Connection con = null;

        try {
            System.out.println("🔍 Intentando cargar el driver JDBC...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ Driver JDBC cargado correctamente.");

            String url = "jdbc:mysql://" + SERVER + "/" + BD + "?useSSL=false&serverTimezone=UTC";
            System.out.println("🔗 Intentando conectar con la base de datos: " + url);

            con = DriverManager.getConnection(url, USER, PASS);
            System.out.println("✅ Conexión establecida correctamente con la base de datos '" + BD + "'.");

        } catch (ClassNotFoundException ex) {
            System.out.println("❌ ERROR: Driver JDBC no encontrado");
            ex.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ ERROR DE CONEXIÓN: " + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }
}
