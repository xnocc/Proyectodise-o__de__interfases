<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Producto" %>
<%@ page import="modelo.ProductoDAO" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    ProductoDAO dao = new ProductoDAO();
    Producto p = null;

    for (Producto x : dao.listar()) {
        if (x.getId() == id) {
            p = x;
            break;
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Editar Producto</title>
    <style>
        body { font-family: Arial; background: #f5f5f5; }
        .formulario {
            width: 450px;
            margin: auto;
            margin-top: 20px;
            padding: 25px;
            background: white;
            box-shadow: 0 0 10px #0003;
            border-radius: 10px;
        }
        h2 { color: #1E88E5; }
        input, textarea {
            width: 100%; padding: 10px;
            margin: 8px 0; border-radius: 6px;
            border: 1px solid #aaa;
        }
        .btn {
            background: #1E88E5; color: white;
            padding: 10px; width: 100%;
            border-radius: 8px; cursor: pointer;
            text-decoration: none; text-align:center;
        }
        .btn:hover { opacity: .9; }
    </style>
</head>
<body>

<div class="formulario">
    <h2>✏️ Editar Producto</h2>

    <form action="ProductoController" method="post">
        <input type="hidden" name="accion" value="Actualizar">
        <input type="hidden" name="id" value="<%= p.getId() %>">

        <label>Nombre</label>
        <input type="text" name="nombre" value="<%= p.getNombre() %>" required>

        <label>Descripción</label>
        <textarea name="descripcion"><%= p.getDescripcion() %></textarea>

        <label>Precio</label>
        <input type="number" step="0.01" name="precio" value="<%= p.getPrecio() %>" required>

        <label>Stock</label>
        <input type="number" name="stock" value="<%= p.getStock() %>" required>

        <label>Categoría</label>
        <input type="text" name="categoria" value="<%= p.getCategoria() %>" required>

        <button class="btn" type="submit">Actualizar</button>
    </form>
</div>

</body>
</html>
