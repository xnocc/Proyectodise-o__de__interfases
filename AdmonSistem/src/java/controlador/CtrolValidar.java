package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import modelo.Usuario;
import modelo.LoginDAO;

@WebServlet(name = "ctrolValidar", urlPatterns = {"/ctrolValidar"})
public class CtrolValidar extends HttpServlet {

    LoginDAO logindao = new LoginDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null || !accion.equalsIgnoreCase("Ingresar")) {
            response.sendRedirect("index.jsp");
            return;
        }

        String usu = request.getParameter("cusuario");
        String cla = request.getParameter("cclave");

        Usuario datos = logindao.Login_datos(usu, cla);

        if (datos != null) {

            HttpSession sesion = request.getSession(false);
            if (sesion != null) {
                sesion.invalidate();
            }

            sesion = request.getSession(true);

            sesion.setAttribute("idUsuario", datos.getIddato());
            sesion.setAttribute("nUsuario", datos.getUsuario());
            sesion.setAttribute("nombreCompleto", datos.getNombre() + " " + datos.getApellido());
            sesion.setAttribute("perfil", datos.getIdperfil());
            sesion.setMaxInactiveInterval(1800);

            String ipCliente = request.getRemoteAddr();
            sesion.setAttribute("ip", ipCliente);

            // 👈 ESTA ERA LA CAUSA DEL PROBLEMA
            response.sendRedirect("ControladorUsuario?accion=dashboard");

        } else {
            request.setAttribute("mensaje", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
