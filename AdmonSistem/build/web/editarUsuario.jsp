<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%
    // El controlador envía el usuario a editar
    Usuario usuario = (Usuario) request.getAttribute("usuario");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Usuario</title>
</head>
<body>

<h2>Editar Usuario</h2>

<form action="UsuarioController" method="post">
    <input type="hidden" name="accion" value="actualizar">
    <input type="hidden" name="id" value="<%= usuario.getId() %>">

    <label>Nombre:</label><br>
    <input type="text" name="nombre" value="<%= usuario.getNombre() %>" required><br><br>

    <label>Correo:</label><br>
    <input type="email" name="correo" value="<%= usuario.getCorreo() %>" required><br><br>

    <label>Rol:</label><br>
    <select name="rol">
        <option value="ADMIN" <%= usuario.getRol().equals("ADMIN") ? "selected" : "" %>>Administrador</option>
        <option value="EMPLEADO" <%= usuario.getRol().equals("EMPLEADO") ? "selected" : "" %>>Empleado</option>
        <option value="CLIENTE" <%= usuario.getRol().equals("CLIENTE") ? "selected" : "" %>>Cliente</option>
    </select>
    <br><br>

    <button type="submit">Actualizar</button>
</form>

<br>
<a href="UsuarioController?accion=listar">Volver</a>

</body>
</html>
