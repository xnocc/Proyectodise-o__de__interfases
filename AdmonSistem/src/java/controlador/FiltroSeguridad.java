package filtros;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "FiltroSeguridad", urlPatterns = {"/*"})
public class FiltroSeguridad implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        // Rutas permitidas sin sesión
        boolean recursoPublico =
                uri.endsWith("index.jsp") ||
                uri.endsWith("ctrolValidar") ||
                uri.contains("css") ||
                uri.contains("js") ||
                uri.contains("img") ||

                // ➜ PERMITIR EL PANEL
                uri.contains("ControladorUsuario") ||
                uri.contains("front.jsp") ||
                uri.contains("dashboard.jsp") ||
                uri.contains("listarUsuarios.jsp") ||
                uri.contains("perfilUsuario.jsp");

        if (recursoPublico) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession sesion = req.getSession(false);

        if (sesion == null || sesion.getAttribute("idUsuario") == null) {
            res.sendRedirect("index.jsp");
            return;
        }

        // Validación de IP opcional
        String ipGuardada = (String) sesion.getAttribute("ip");
        String ipActual = req.getRemoteAddr();

        if (ipGuardada != null && !ipActual.equals(ipGuardada)) {
            sesion.invalidate();
            res.sendRedirect("index.jsp");
            return;
        }

        chain.doFilter(request, response);
    }
}
