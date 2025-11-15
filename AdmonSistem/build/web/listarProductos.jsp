<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Producto" %>
<%@ page import="modelo.ProductoDAO" %>

<%
    // Validar sesión
    String usuario = (String) session.getAttribute("nUsuario");
    if (usuario == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    ProductoDAO dao = new ProductoDAO();
    List<Producto> lista = dao.listar();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Productos</title>
    <style>
        body {
            font-family: Arial;
            background: #f5f5f5;
        }
        .contenedor {
            width: 90%;
            margin: 20px auto;
        }
        h2 {
            color: #1E88E5;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
            box-shadow: 0 0 10px #0003;
        }
        th, td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
            text-align: center;
        }
        th {
            background: #1E88E5;
            color: white;
        }
        .btn {
            padding: 6px 12px;
            border-radius: 6px;
            text-decoration: none;
            color: white;
        }
        .btn-add { background: #43A047; }
        .btn-edit { background: #1E88E5; }
        .btn-delete { background: #E53935; }
        .btn:hover { opacity: 0.8; }
    </style>
</head>
<body>

<div class="contenedor">

    <h2>📦 Gestión de Productos</h2>
    <a href="registrarProducto.jsp" class="btn btn-add">➕ Agregar Producto</a>
    <br><br>

    <table>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Precio</th>
            <th>Stock</th>
            <th>Categoría</th>
            <th>Acciones</th>
        </tr>

        <% for (Producto p : lista) { %>
        <tr>
            <td><%= p.getId() %></td>
            <td><%= p.getNombre() %></td>
            <td><%= p.getDescripcion() %></td>
            <td>$<%= p.getPrecio() %></td>
            <td><%= p.getStock() %></td>
            <td><%= p.getCategoria() %></td>

            <td>
                <a class="btn btn-edit" href="editarProducto.jsp?id=<%= p.getId() %>">✏️ Editar</a>
                <a class="btn btn-delete"
                   href="ProductoController?accion=eliminar&id=<%= p.getId() %>"
                   onclick="return confirm('¿Eliminar este producto?');">
                    🗑️ Eliminar
                </a>
            </td>
        </tr>
        <% } %>

    </table>

</div>

</body>
</html>
