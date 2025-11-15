package controlador;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * SecurityFilter - protege las páginas internas de la aplicación asegurando que
 * exista una sesión válida con el atributo "idUsuario".
 *
 * Usa la anotación @WebFilter("/*") para interceptar todas las peticiones.
 * Ajusta la lista de rutas públicas según las páginas y recursos de tu proyecto.
 */
@WebFilter("/*")
public class SecurityFilter implements Filter {

    private FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();           // e.g. /miApp/index.jsp
        String context = req.getContextPath();      // e.g. /miApp
        String path = uri.substring(context.length()).toLowerCase(); // ruta relativa

        // --- Rutas públicas (no requieren sesión) ---
        boolean isPublic = 
                path.equals("/") ||
                path.equals("/index.jsp") ||
                path.equals("/ctrolvalidar") ||    // servlet de login (anotación en CtrolValidar)
                path.equals("/controladorusuario") || // registro de usuario si lo quieres público
                path.endsWith("/mensaje.jsp") ||
                path.endsWith("/error.jsp") ||
                path.endsWith(".css") ||
                path.endsWith(".js") ||
                path.endsWith(".png") ||
                path.endsWith(".jpg") ||
                path.endsWith(".jpeg") ||
                path.endsWith(".gif") ||
                path.endsWith(".ico") ||
                path.contains("/assets/") ||        // si usas carpeta assets
                path.contains("/vendor/") ||        // libs si las pones así
                path.contains("/webjars/");         // o webjars

        // Obtener sesión existente (no crear si no existe)
        HttpSession sesion = req.getSession(false);
        boolean logged = (sesion != null && sesion.getAttribute("idUsuario") != null);

        // Si la ruta es pública, dejar pasar siempre
        if (isPublic) {
            chain.doFilter(request, response);
            return;
        }

        // Si no está logueado y la ruta no es pública -> redirigir a index.jsp
        if (!logged) {
            // Opcional: si quieres enviar un mensaje, puedes añadir ?msg=...
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        // Seguridad adicional opcional: verificar IP si la guardaste en sesión
        String ipSesion = (String) sesion.getAttribute("ip");
        if (ipSesion != null) {
            String ipActual = req.getRemoteAddr();
            if (!ipSesion.equals(ipActual)) {
                // IP cambió -> invalidar sesión y redirigir a login
                try {
                    sesion.invalidate();
                } catch (IllegalStateException ex) {
                    // ya invalidada, no hacer nada
                }
                res.sendRedirect(req.getContextPath() + "/index.jsp");
                return;
            }
        }

        // Todo OK -> permitir continuar
        chain.doFilter(request, response);
    }
}
