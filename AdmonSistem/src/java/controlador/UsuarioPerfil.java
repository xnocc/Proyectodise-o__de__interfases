package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "perfilUsuario", urlPatterns = {"/perfilUsuario"})
public class UsuarioPerfil extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession(false);

        if (sesion == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        request.setAttribute("nombreCompleto", sesion.getAttribute("nombreCompleto"));
        request.setAttribute("usuario", sesion.getAttribute("nUsuario"));
        request.setAttribute("perfil", sesion.getAttribute("perfil"));

        request.getRequestDispatcher("perfil.jsp").forward(request, response);
    }
}
