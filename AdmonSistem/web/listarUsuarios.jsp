<%@ page import="java.sql.*, modelo.Conexion" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    table {
        width: 100%;
        border-collapse: collapse;
        background: white;
        border-radius: 10px;
        overflow: hidden;
        box-shadow: 0 2px 8px rgba(0,0,0,0.2);
    }
    th {
        background: #1E88E5;
        color: white;
        padding: 12px;
    }
    td {
        padding: 10px;
        border-bottom: 1px solid #ccc;
    }
    tr:hover {
        background: #f1f1f1;
    }
    .btn {
        padding: 6px 10px;
        border-radius: 6px;
        border: none;
        cursor: pointer;
        color: white;
        text-decoration: none;
    }
    .edit { background: #0288D1; }
    .del { background: #D32F2F; }
    .add {
        background: #43A047;
        padding: 10px 15px;
        text-decoration: none;
        color: white;
        border-radius: 6px;
    }
</style>

<h2>👤 Gestión de Usuarios</h2>

<a href="regUsuario.jsp" class="add">➕ Agregar Usuario</a>
<br><br>

<table>
    <tr>
        <th>ID</th>
        <th>Identificación</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Email</th>
        <th>Usuario</th>
        <th>Acciones</th>
    </tr>

    <%
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = new Conexion().crearConexion();
            ps = con.prepareStatement("SELECT * FROM datos");
            rs = ps.executeQuery();

            while (rs.next()) {
    %>

    <tr>
        <td><%= rs.getInt("iddato") %></td>
        <td><%= rs.getString("identificacion") %></td>
        <td><%= rs.getString("nombre") %></td>
        <td><%= rs.getString("apellido") %></td>
        <td><%= rs.getString("email") %></td>
        <td><%= rs.getString("usuario") %></td>

        <td>
            <a href="editarUsuario.jsp?id=<%= rs.getInt("iddato") %>" class="btn edit">✏️ Editar</a>
            <a href="deleteUsuario?id=<%= rs.getInt("iddato") %>" class="btn del">🗑️ Eliminar</a>
        </td>
    </tr>

    <%
            }
        } catch (Exception e) {
            out.print("Error: " + e.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    %>

</table>
