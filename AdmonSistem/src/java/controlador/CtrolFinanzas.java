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
                // 1. Cargar la lista de finanzas en el Request
                request.setAttribute("finanzas", dao.listarFinanzasPorUsuario(idUsuario));
                
                // 2. Establecer el JSP de contenido a incluir en el layout
                request.setAttribute("pagina", "finanzas.jsp"); 
                
                // 3. Cargar el layout principal (front.jsp)
                request.getRequestDispatcher("front.jsp").forward(request, response);
                break;

            case "nuevo":
                // 1. Establecer la página del formulario
                request.setAttribute("pagina", "agregarFinanza.jsp"); 
                // 2. Cargar el layout principal
                request.getRequestDispatcher("front.jsp").forward(request, response);
                break;

            case "editar":
                int idEdit = Integer.parseInt(request.getParameter("id"));
                Finanza f = dao.obtenerFinanzaPorId(idEdit);
                request.setAttribute("finanza", f);
                
                // 1. Establecer la página del formulario de edición
                request.setAttribute("pagina", "editarFinanza.jsp"); 
                // 2. Cargar el layout principal
                request.getRequestDispatcher("front.jsp").forward(request, response);
                break;

            case "eliminar":
                int idDel = Integer.parseInt(request.getParameter("id"));
                dao.eliminarFinanza(idDel);
                
                // CORRECCIÓN: Después de eliminar, REDIRIGIMOS al LISTAR para recargar todo.
                response.sendRedirect("CtrolFinanzas?accion=listar"); 
                break;

            default:
                // Redirigir al listar por defecto
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

        // CLAVE: Redirige al doGet de "listar" para que cargue la lista y el layout.
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
        int idUsuario = (int) request.getSession().getAttribute("idUsuario");
        f.setIdUsuario(idUsuario);

        dao.actualizarFinanza(f);

        // CLAVE: Redirige al doGet de "listar" para que cargue la lista y el layout.
        response.sendRedirect("CtrolFinanzas?accion=listar"); 
    }
}