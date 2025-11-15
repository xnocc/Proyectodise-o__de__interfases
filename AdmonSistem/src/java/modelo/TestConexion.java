package modelo;

public class TestConexion {
    public static void main(String[] args) {
        Conexion con = new Conexion();
        if (con.crearConexion() != null) {
            System.out.println("✅ Conexión exitosa desde TestConexion.");
        } else {
            System.out.println("❌ Falló la conexión desde TestConexion.");
        }
    }
}
