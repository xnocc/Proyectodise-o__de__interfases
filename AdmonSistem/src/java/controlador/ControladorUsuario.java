package controlador;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class ControladorUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        String pagina;

        if (accion == null) {
            accion = "dashboard";
        }

        switch (accion) {
            case "dashboard":
                pagina = "Dashboard.jsp";   // <--- RESPETA MAYÚSCULAS
                break;

            case "listar":
                pagina = "listarUsuarios.jsp";
                break;

            case "perfil":
                pagina = "perfilUsuario.jsp";
                break;
            case "finanzas":
                // 1. Delegar la carga de datos a CtrolFinanzas
                // Esto ejecuta CtrolFinanzas?accion=listar internamente,
                // lo que cargará 'finanzas' en el objeto request.
                RequestDispatcher rd = request.getRequestDispatcher("/CtrolFinanzas?accion=listar");
                rd.include(request, response);

                // 2. Establecer el JSP de contenido a incluir en el layout
                pagina = "finanzas.jsp";
                break;

            default:
                pagina = "Dashboard.jsp";
        }

        request.setAttribute("pagina", pagina);
        request.getRequestDispatcher("front.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
