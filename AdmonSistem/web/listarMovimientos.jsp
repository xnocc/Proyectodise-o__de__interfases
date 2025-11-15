<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*, modelo.Conexion" %>

<%
    HttpSession s = request.getSession(false);
    if (s == null || s.getAttribute("idUsuario") == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    int idUsuario = (Integer) s.getAttribute("idUsuario");

    Connection con = new Conexion().crearConexion();
    PreparedStatement ps = con.prepareStatement("SELECT * FROM movimientos WHERE id_usuario=? ORDER BY fecha DESC");
    ps.setInt(1, idUsuario);
    ResultSet rs = ps.executeQuery();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Movimientos</title>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>

<body style="font-family:Arial; background:#fafafa; margin:20px;">

<h2><i class="fa fa-file-invoice-dollar"></i> Movimientos Registrados</h2>

<table border="1" cellspacing="0" cellpadding="8"
       style="background:white; border-radius:10px; box-shadow:0 0 10px rgba(0,0,0,0.1); width:100%;">

<tr style="background:#1565C0; color:white;">
    <th>Fecha</th>
    <th>Tipo</th>
    <th>Monto</th>
    <th>Descripción</th>
</tr>

<%
    while (rs.next()) {
%>

<tr>
    <td><%= rs.getString("fecha") %></td>
    <td style="color:<%= rs.getString("tipo").equals("ingreso") ? "green" : "red" %>;">
        <%= rs.getString("tipo") %>
    </td>
    <td><%= rs.getDouble("monto") %></td>
    <td><%= rs.getString("descripcion") %></td>
</tr>

<%
    }

    rs.close();
    ps.close();
    con.close();
%>

</table>

</body>
</html>
