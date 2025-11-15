package Interfaces;

import java.util.List;
import modelo.Producto;

public interface CRUD_Producto {

    public int agregarProducto(Producto p);
    public int actualizarProducto(Producto p);
    public int eliminarProducto(int id);
    public Producto listarProducto_Id(int id);
    public List<Producto> listarProductos();
}
