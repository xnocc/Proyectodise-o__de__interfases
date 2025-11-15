<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="modelo.Finanza" %>

<%
    Finanza f = (Finanza) request.getAttribute("finanza");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Editar Finanza</title>
    <style>
        body { 
            font-family: Arial; 
            background: #f4f4f4; 
            margin: 0;
        }
        .form {
            width: 400px;
            margin: auto;
            margin-top: 40px;
            padding: 20px;
            background: white;
            box-shadow: 0 0 8px rgba(0,0,0,0.2);
            border-radius: 8px;
        }
        input, select, textarea {
            width: 100%;
            margin-top: 10px;
            padding: 10px;
        }
        button {
            margin-top: 15px;
            padding: 10px;
            width: 100%;
            background: #1E88E5;
            border: none;
            color: white;
            font-size: 16px;
            border-radius: 6px;
        }
    </style>
</head>
<body>

<div class="form">
    <h2>✏ Editar Registro</h2>

    <form action="CtrolFinanzas" method="POST">
        <input type="hidden" name="accion" value="actualizar">
        <input type="hidden" name="id" value="<%= f.getId() %>">

        <label>Tipo:</label>
        <select name="tipo" required>
            <option value="Ingreso" <%= f.getTipo().equals("Ingreso") ? "selected" : "" %>>Ingreso</option>
            <option value="Gasto" <%= f.getTipo().equals("Gasto") ? "selected" : "" %>>Gasto</option>
        </select>

        <label>Monto:</label>
        <input type="number" step="0.01" required name="monto" value="<%= f.getMonto() %>">

        <label>Descripción:</label>
        <textarea name="descripcion" required><%= f.getDescripcion() %></textarea>

        <label>Fecha:</label>
        <input type="date" name="fecha" value="<%= f.getFecha() %>" required>

        <button type="submit">Actualizar</button>
    </form>
</div>

</body>
</html>
