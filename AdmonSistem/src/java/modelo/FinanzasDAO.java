package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FinanzasDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // ---------------------------------------------------------------------------------------
    // ✔ AGREGAR FINANZA
    // ---------------------------------------------------------------------------------------
    public int agregarFinanza(Finanza f) {
        String sql = "INSERT INTO finanzas (id_usuario, tipo, monto, descripcion, fecha) VALUES (?, ?, ?, ?, ?)";

        try {
            con = cn.crearConexion();
            ps = con.prepareStatement(sql);

            ps.setInt(1, f.getIdUsuario());
            ps.setString(2, f.getTipo());
            ps.setDouble(3, f.getMonto());
            ps.setString(4, f.getDescripcion());
            ps.setString(5, f.getFecha());

            return ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERROR agregarFinanza(): " + e.getMessage());
            return 0;
        }
    }

    // ---------------------------------------------------------------------------------------
    // ✔ ACTUALIZAR FINANZA
    // ---------------------------------------------------------------------------------------
    public int actualizarFinanza(Finanza f) {
        String sql = "UPDATE finanzas SET tipo=?, monto=?, descripcion=?, fecha=? WHERE id=?";

        try {
            con = cn.crearConexion();
            ps = con.prepareStatement(sql);

            ps.setString(1, f.getTipo());
            ps.setDouble(2, f.getMonto());
            ps.setString(3, f.getDescripcion());
            ps.setString(4, f.getFecha());
            ps.setInt(5, f.getId());

            return ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERROR actualizarFinanza(): " + e.getMessage());
            return 0;
        }
    }

    // ---------------------------------------------------------------------------------------
    // ✔ ELIMINAR FINANZA
    // ---------------------------------------------------------------------------------------
    public int eliminarFinanza(int id) {
        String sql = "DELETE FROM finanzas WHERE id=?";

        try {
            con = cn.crearConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            return ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERROR eliminarFinanza(): " + e.getMessage());
            return 0;
        }
    }

    // ---------------------------------------------------------------------------------------
    // ✔ LISTAR FINANZAS POR USUARIO
    // ---------------------------------------------------------------------------------------
    public List<Finanza> listarFinanzasPorUsuario(int idUsuario) {
        List<Finanza> lista = new ArrayList<>();
        String sql = "SELECT * FROM finanzas WHERE id_usuario=? ORDER BY fecha DESC";

        try {
            con = cn.crearConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();

            while (rs.next()) {
                Finanza f = new Finanza();

                f.setId(rs.getInt("id"));
                f.setIdUsuario(rs.getInt("id_usuario"));
                f.setTipo(rs.getString("tipo"));
                f.setMonto(rs.getDouble("monto"));
                f.setDescripcion(rs.getString("descripcion"));
                f.setFecha(rs.getString("fecha"));

                lista.add(f);
            }

        } catch (SQLException e) {
            System.out.println("ERROR listarFinanzasPorUsuario(): " + e.getMessage());
        }

        return lista;
    }

    // ---------------------------------------------------------------------------------------
    // ✔ OBTENER FINANZA POR ID
    // ---------------------------------------------------------------------------------------
    public Finanza obtenerFinanzaPorId(int id) {
        Finanza f = null;
        String sql = "SELECT * FROM finanzas WHERE id=?";

        try {
            con = cn.crearConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                f = new Finanza();
                f.setId(rs.getInt("id"));
                f.setIdUsuario(rs.getInt("id_usuario"));
                f.setTipo(rs.getString("tipo"));
                f.setMonto(rs.getDouble("monto"));
                f.setDescripcion(rs.getString("descripcion"));
                f.setFecha(rs.getString("fecha"));
            }

        } catch (SQLException e) {
            System.out.println("ERROR obtenerFinanzaPorId(): " + e.getMessage());
        }

        return f;
    }
}
