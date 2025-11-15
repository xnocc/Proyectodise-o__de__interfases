package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import modelo.Usuario;
import modelo.UsuarioDAO;

@WebServlet(name = "editarUsuario", urlPatterns = {"/editarUsuario"})
public class CtrolEditarUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("cidd"));
        String identificacion = request.getParameter("cid");
        String nombre = request.getParameter("cnombre");
        String apellido = request.getParameter("capellido");
        String email = request.getParameter("cmail");
        String usuario = request.getParameter("cusuario");
        String clave = request.getParameter("cclave");
        int idperfil = Integer.parseInt(request.getParameter("cperfil"));

        Usuario u = new Usuario();
        u.setIddato(id);
        u.setIdentificacion(identificacion);
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setEmail(email);
        u.setUsuario(usuario);
        u.setClave(clave);
        u.setIdperfil(idperfil);

        UsuarioDAO dao = new UsuarioDAO();
        int estado = dao.actualizarUsuarios(u);

        if (estado > 0) {
            response.sendRedirect("listarUsuarios.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
