package modelo;

import Interfaces.CRUD_Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO implements CRUD_Producto {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // ==========================
    // MÉTODOS QUE EL CONTROLLER USA
    // ==========================
    public int agregar(Producto p) {
        return agregarProducto(p);
    }

    public int actualizar(Producto p) {
        return actualizarProducto(p);
    }

    public int eliminar(int id) {
        return eliminarProducto(id);
    }

    // ==========================
    // TUS MÉTODOS ORIGINALES
    // ==========================

    @Override
    public int agregarProducto(Producto p) {
        String sql = "INSERT INTO productos(nombre, descripcion, precio, stock, categoria) VALUES(?,?,?,?,?)";
        int r = 0;

        try {
            con = new Conexion().crearConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setString(5, p.getCategoria());
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("ERROR agregar: " + e.getMessage());
        }
        return r;
    }

    @Override
    public int actualizarProducto(Producto p) {
        String sql = "UPDATE productos SET nombre=?, descripcion=?, precio=?, stock=?, categoria=? WHERE id=?";
        int r = 0;

        try {
            con = new Conexion().crearConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setString(5, p.getCategoria());
            ps.setInt(6, p.getId());
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("ERROR actualizar: " + e.getMessage());
        }
        return r;
    }

    @Override
    public int eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id=?";
        int r = 0;

        try {
            con = new Conexion().crearConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("ERROR eliminar: " + e.getMessage());
        }
        return r;
    }

    @Override
    public Producto listarProducto_Id(int id) {
        String sql = "SELECT * FROM productos WHERE id=?";
        Producto p = new Producto();

        try {
            con = new Conexion().crearConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                p.setCategoria(rs.getString("categoria"));
            }

        } catch (Exception e) {
            System.out.println("ERROR listar_Id: " + e.getMessage());
        }
        return p;
    }

    @Override
    public List<Producto> listarProductos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try {
            con = new Conexion().crearConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                p.setCategoria(rs.getString("categoria"));

                lista.add(p);
            }

        } catch (Exception e) {
            System.out.println("ERROR listar: " + e.getMessage());
        }
        return lista;
    }
}
