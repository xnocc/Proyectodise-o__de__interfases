package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        } finally {
             // Es buena práctica cerrar recursos
             try { if(ps != null) ps.close(); if(con != null) con.close(); } catch (SQLException e) {}
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
        } finally {
             try { if(ps != null) ps.close(); if(con != null) con.close(); } catch (SQLException e) {}
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
        } finally {
             try { if(ps != null) ps.close(); if(con != null) con.close(); } catch (SQLException e) {}
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
        } finally {
             try { if(rs != null) rs.close(); if(ps != null) ps.close(); if(con != null) con.close(); } catch (SQLException e) {}
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
        } finally {
             try { if(rs != null) rs.close(); if(ps != null) ps.close(); if(con != null) con.close(); } catch (SQLException e) {}
        }

        return f;
    }
    
    // ---------------------------------------------------------------------------------------
    // 🟢 CORREGIDO: CALCULAR RESUMEN FINANCIERO PARA EL DASHBOARD
    // ---------------------------------------------------------------------------------------
    /**
     * Calcula la suma total de Ingresos y Gastos para un usuario.
     * @param idUsuario ID del usuario.
     * @return Map<String, Double> con claves "Ingreso" y "Gasto". (Singular)
     */
    public Map<String, Double> calcularResumenFinanciero(int idUsuario) {
        Map<String, Double> resumen = new HashMap<>();
        // 1. INICIALIZAR CON CLAVES SINGULARES, que es lo que espera el JSP
        resumen.put("Ingreso", 0.0);
        resumen.put("Gasto", 0.0);
        
        // Consulta SQL para sumar montos agrupados por tipo ("Ingreso" o "Gasto")
        String sql = "SELECT tipo, SUM(monto) AS total FROM finanzas WHERE id_usuario=? GROUP BY tipo";

        try {
            con = cn.crearConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();

            while (rs.next()) {
                String tipo = rs.getString("tipo");
                double total = rs.getDouble("total");
                
                // 2. USAR LA LÓGICA DE ASIGNACIÓN ROBUSTA (IgnoreCase)
                // Y ASIGNAR AL KEY SINGULAR que creamos en el map.
                if (tipo.equalsIgnoreCase("Ingreso")) {
                    resumen.put("Ingreso", total);
                } else if (tipo.equalsIgnoreCase("Gasto")) {
                    resumen.put("Gasto", total);
                }
            }

        } catch (SQLException e) {
            System.out.println("ERROR calcularResumenFinanciero(): " + e.getMessage());
        } finally {
             try { if(rs != null) rs.close(); if(ps != null) ps.close(); if(con != null) con.close(); } catch (SQLException e) {}
        }

        return resumen;
    }
}