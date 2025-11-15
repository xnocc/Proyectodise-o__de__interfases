package controlador;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import modelo.Finanza;
import modelo.FinanzasDAO;

@WebServlet(name = "CtrolFinanzas", urlPatterns = {"/CtrolFinanzas"})
public class CtrolFinanzas extends HttpServlet {

    FinanzasDAO dao = new FinanzasDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession(false);

        if (sesion == null || sesion.getAttribute("idUsuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int idUsuario = (int) sesion.getAttribute("idUsuario");

        String action = request.getParameter("accion");
        if (action == null) action = "listar";

        switch (action) {

            case "listar":
                request.setAttribute("finanzas", dao.listarFinanzasPorUsuario(idUsuario));
                request.getRequestDispatcher("finanzas.jsp").forward(request, response);
                break;

            case "nuevo":
                request.getRequestDispatcher("agregarFinanza.jsp").forward(request, response);
                break;

            case "editar":
                int idEdit = Integer.parseInt(request.getParameter("id"));
                Finanza f = dao.obtenerFinanzaPorId(idEdit);
                request.setAttribute("finanza", f);
                request.getRequestDispatcher("editarFinanza.jsp").forward(request, response);
                break;

            case "eliminar":
                int idDel = Integer.parseInt(request.getParameter("id"));
                dao.eliminarFinanza(idDel);
                response.sendRedirect("CtrolFinanzas?accion=listar");
                break;

            default:
                response.sendRedirect("CtrolFinanzas?accion=listar");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession(false);

        if (sesion == null || sesion.getAttribute("idUsuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int idUsuario = (int) sesion.getAttribute("idUsuario");

        String action = request.getParameter("accion");
        if (action == null) action = "";

        switch (action) {

            case "agregar":
                guardarNuevaFinanza(request, response, idUsuario);
                break;

            case "actualizar":
                actualizarFinanza(request, response);
                break;
        }
    }


    private void guardarNuevaFinanza(HttpServletRequest request, HttpServletResponse response, int idUsuario)
            throws IOException {

        Finanza f = new Finanza();
        f.setIdUsuario(idUsuario);
        f.setTipo(request.getParameter("tipo"));
        f.setMonto(Double.parseDouble(request.getParameter("monto")));
        f.setDescripcion(request.getParameter("descripcion"));
        f.setFecha(request.getParameter("fecha"));

        dao.agregarFinanza(f);

        response.sendRedirect("CtrolFinanzas?accion=listar");
    }


    private void actualizarFinanza(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Finanza f = new Finanza();
        f.setId(Integer.parseInt(request.getParameter("id")));
        f.setTipo(request.getParameter("tipo"));
        f.setMonto(Double.parseDouble(request.getParameter("monto")));
        f.setDescripcion(request.getParameter("descripcion"));
        f.setFecha(request.getParameter("fecha"));

        dao.actualizarFinanza(f);

        response.sendRedirect("CtrolFinanzas?accion=listar");
    }

}
