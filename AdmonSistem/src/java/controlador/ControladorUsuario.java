package controlador;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import modelo.FinanzasDAO;

public class ControladorUsuario extends HttpServlet {

    // Instancia del DAO a nivel de clase para ser reutilizada por cada solicitud
    private final FinanzasDAO finanzasDAO = new FinanzasDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        String pagina = null; // Inicializamos 'pagina' a null
        
        // Si no se especifica acción, por defecto vamos al dashboard
        if (accion == null) {
            accion = "dashboard";
        }

        switch (accion) {
            case "dashboard":
                // 1. OBTENER EL ID DEL USUARIO DE LA SESIÓN
                HttpSession sesion = request.getSession(false);
                
                // Verificación de seguridad: si no hay sesión o ID, redirigir al login
                if (sesion == null || sesion.getAttribute("idUsuario") == null) {
                    response.sendRedirect("login.jsp");
                    return;
                }
                
                int idUsuario = (int) sesion.getAttribute("idUsuario");

                // 2. CARGAR EL RESUMEN FINANCIERO (Ingresos y Gastos totales)
                // Esto llama al método agregado en FinanzasDAO
                Map<String, Double> resumen = finanzasDAO.calcularResumenFinanciero(idUsuario);

                // 3. CARGAR LOS MOVIMIENTOS RECIENTES (la lista completa de finanzas)
                // La vista (Dashboard.jsp) filtrará para mostrar solo los 5 más recientes
                List<modelo.Finanza> movimientosRecientes = finanzasDAO.listarFinanzasPorUsuario(idUsuario);

                // 4. GUARDAR AMBOS OBJETOS EN EL REQUEST
                request.setAttribute("resumenFinanciero", resumen);
                request.setAttribute("movimientosRecientes", movimientosRecientes);

                // 5. Establecer la página de destino
                pagina = "Dashboard.jsp";
                break;

            case "listar": // Gestión de Usuarios
                // Aquí deberías cargar la lista de usuarios si esta funcionalidad existe
                pagina = "listarUsuarios.jsp";
                break;

            case "perfil": // Mi Perfil
                // Aquí deberías cargar la información del perfil del usuario
                pagina = "perfilUsuario.jsp";
                break;
                
            default:
                // Si la acción no es reconocida, volvemos al dashboard
                pagina = "Dashboard.jsp";
                break;
        }

        // Si se definió una página, la cargamos dentro del layout principal
        if (pagina != null) {
            request.setAttribute("pagina", pagina);
            request.getRequestDispatcher("front.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirigimos todas las peticiones POST a GET para simplificar la navegación
        doGet(request, response);
    }
}