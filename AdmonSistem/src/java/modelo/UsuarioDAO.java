package modelo;

import Interfaces.CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements CRUD {

    private final Conexion cn = new Conexion();

    @Override
    public int agregarUsuario(Usuario u) {

        String q = "INSERT INTO datos (identificacion, nombre, apellido, email, usuario, clave, id_perfil) VALUES (?,?,?,?,?,?,?)";

        try (Connection con = cn.crearConexion();
             PreparedStatement ps = con.prepareStatement(q)) {

            ps.setString(1, u.getIdentificacion());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getUsuario());
            ps.setString(6, u.getClave());
            ps.setInt(7, u.getIdperfil());

            return ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERROR AL REGISTRAR: " + e.getMessage());
        }

        return 0;
    }

    @Override
    public int actualizarUsuarios(Usuario u) {

        String q = "UPDATE datos SET identificacion=?, nombre=?, apellido=?, email=?, usuario=?, clave=?, id_perfil=? WHERE iddato=?";

        try (Connection con = cn.crearConexion();
             PreparedStatement ps = con.prepareStatement(q)) {

            ps.setString(1, u.getIdentificacion());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getUsuario());
            ps.setString(6, u.getClave());
            ps.setInt(7, u.getIdperfil());
            ps.setInt(8, u.getIddato());

            return ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERROR AL ACTUALIZAR: " + e.getMessage());
        }

        return 0;
    }

    @Override
    public int eliminarUsuarios(int id) {

        String q = "DELETE FROM datos WHERE iddato=?";

        try (Connection con = cn.crearConexion();
             PreparedStatement ps = con.prepareStatement(q)) {

            ps.setInt(1, id);
            return ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERROR AL ELIMINAR: " + e.getMessage());
        }

        return 0;
    }

    @Override
    public Usuario listarUsuarios_Id(int id) {

        Usuario u = null;
        String q = "SELECT * FROM datos WHERE iddato=?";

        try (Connection con = cn.crearConexion();
             PreparedStatement ps = con.prepareStatement(q)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                u = new Usuario();
                u.setIddato(rs.getInt("iddato"));
                u.setIdentificacion(rs.getString("identificacion"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setEmail(rs.getString("email"));
                u.setUsuario(rs.getString("usuario"));
                u.setClave(rs.getString("clave"));
                u.setIdperfil(rs.getInt("id_perfil"));
            }

        } catch (SQLException e) {
            System.out.println("ERROR AL BUSCAR: " + e.getMessage());
        }

        return u;
    }

    @Override
    public List<Usuario> listadoUsuarios() {

        List<Usuario> lista = new ArrayList<>();

        String q = "SELECT * FROM datos";

        try (Connection con = cn.crearConexion();
             PreparedStatement ps = con.prepareStatement(q);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Usuario u = new Usuario();
                u.setIddato(rs.getInt("iddato"));
                u.setIdentificacion(rs.getString("identificacion"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setEmail(rs.getString("email"));
                u.setUsuario(rs.getString("usuario"));
                u.setClave(rs.getString("clave"));
                u.setIdperfil(rs.getInt("id_perfil"));

                lista.add(u);
            }

        } catch (SQLException e) {
            System.out.println("ERROR LISTANDO: " + e.getMessage());
        }

        return lista;
    }
}
