<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, modelo.Finanza" %>

<%
    List<Finanza> lista = (List<Finanza>) request.getAttribute("finanzas");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Finanzas</title>
    <style>
        body {
            font-family: Arial;
            background: #f4f4f4;
            margin: 0;
        }
        .container {
            width: 95%;
            margin: auto;
            padding: 20px;
        }
        h2 {
            color: #1E88E5;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            background: white;
            border-collapse: collapse;
            box-shadow: 0 0 8px rgba(0,0,0,0.2);
        }
        th, td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
        }
        th {
            background: #1E88E5;
            color: white;
        }
        a.btn {
            padding: 10px 15px;
            text-decoration: none;
            background: #1E88E5;
            color: white;
            border-radius: 4px;
        }
        a.danger {
            background: #e53935;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>📊 Finanzas del Usuario</h2>

    <a href="CtrolFinanzas?accion=nuevo" class="btn">➕ Agregar Registro</a>
    <br><br>

    <table>
        <tr>
            <th>Tipo</th>
            <th>Monto</th>
            <th>Descripción</th>
            <th>Fecha</th>
            <th>Acciones</th>
        </tr>

        <%
            if (lista != null) {
                for (Finanza f : lista) {
        %>
        <tr>
            <td><%= f.getTipo() %></td>
            <td>$ <%= f.getMonto() %></td>
            <td><%= f.getDescripcion() %></td>
            <td><%= f.getFecha() %></td>
            <td>
                <a href="CtrolFinanzas?accion=editar&id=<%= f.getId() %>" class="btn">✏ Editar</a>
                <a href="CtrolFinanzas?accion=eliminar&id=<%= f.getId() %>" 
                   class="btn danger"
                   onclick="return confirm('¿Eliminar registro?');">🗑 Eliminar</a>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>

</div>

</body>
</html>
