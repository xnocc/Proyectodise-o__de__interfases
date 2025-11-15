package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import modelo.UsuarioDAO;

@WebServlet(name = "eliminarUsuario", urlPatterns = {"/eliminarUsuario"})
public class CtrolEliminarUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        UsuarioDAO dao = new UsuarioDAO();
        int estado = dao.eliminarUsuarios(id);

        if (estado > 0) {
            response.sendRedirect("listarUsuarios.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
