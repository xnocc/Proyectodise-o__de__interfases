package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    public Usuario Login_datos(String usuario, String clave) {

        Usuario datos = null;

        String sql = "SELECT * FROM usuarios WHERE usuario=? AND clave=?";

        try (Connection con = new Conexion().crearConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, clave);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                datos = new Usuario();

                datos.setIddato(rs.getInt("id"));
                datos.setIdentificacion(rs.getString("identificacion"));
                datos.setNombre(rs.getString("nombre"));
                datos.setApellido(rs.getString("apellido"));
                datos.setEmail(rs.getString("email"));
                datos.setUsuario(rs.getString("usuario"));
                datos.setClave(rs.getString("clave"));
                datos.setIdperfil(rs.getInt("id_perfil"));
            }

        } catch (SQLException e) {
            System.out.println("ERROR EN LOGIN: " + e.getMessage());
        }

        return datos;
    }
}
