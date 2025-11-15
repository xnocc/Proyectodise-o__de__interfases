<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String nombre = (String) session.getAttribute("nombreCompleto");
    String usuario = (String) session.getAttribute("nUsuario");
    Integer perfil = (Integer) session.getAttribute("perfil");

    if (usuario == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

<h2 style="color:#1E88E5;">📄 Mi Perfil</h2>

<div style="padding:20px; background:#fafafa; border-radius:10px; width:400px; box-shadow:0 2px 6px rgba(0,0,0,0.2);">

    <p><strong>Nombre Completo:</strong> <%= nombre %></p>
    <p><strong>Usuario:</strong> <%= usuario %></p>
    <p><strong>Perfil / Rol:</strong> <%= perfil %></p>

    <a href="editarUsuario?idUsuario=<%= session.getAttribute("idUsuario") %>"
       style="padding:10px 15px; background:#0288D1; color:white; border-radius:6px; text-decoration:none;">
        ✏️ Editar Perfil
    </a>

</div>
