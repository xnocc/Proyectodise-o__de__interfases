package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

import modelo.Producto;
import modelo.ProductoDAO;

@WebServlet(name="ProductoController", urlPatterns={"/ProductoController"})
public class ProductoController extends HttpServlet {

    ProductoDAO dao = new ProductoDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        switch (accion) {
            case "Agregar":
                agregar(request, response);
                break;

            case "Actualizar":
                actualizar(request, response);
                break;

            default:
                response.sendRedirect("cpanel.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        switch (accion) {
            case "eliminar":
                eliminar(request, response);
                break;

            default:
                response.sendRedirect("cpanel.jsp");
        }
    }

    private void agregar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Producto p = new Producto();
        p.setNombre(request.getParameter("nombre"));
        p.setDescripcion(request.getParameter("descripcion"));
        p.setPrecio(Double.parseDouble(request.getParameter("precio")));
        p.setStock(Integer.parseInt(request.getParameter("stock")));
        p.setCategoria(request.getParameter("categoria"));

        dao.agregar(p);
        response.sendRedirect("listarProductos.jsp");
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Producto p = new Producto();
        p.setId(Integer.parseInt(request.getParameter("id")));
        p.setNombre(request.getParameter("nombre"));
        p.setDescripcion(request.getParameter("descripcion"));
        p.setPrecio(Double.parseDouble(request.getParameter("precio")));
        p.setStock(Integer.parseInt(request.getParameter("stock")));
        p.setCategoria(request.getParameter("categoria"));

        dao.actualizar(p);
        response.sendRedirect("listarProductos.jsp");
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        dao.eliminar(id);
        response.sendRedirect("listarProductos.jsp");
    }
}
