<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
String usuario = (String) session.getAttribute("nUsuario");
if (usuario == null) {
    response.sendRedirect("index.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
    <title>Registrar Usuario</title>
    <style>
        body { font-family: Arial; }
        .form-box {
            background: white;
            padding: 25px;
            border-radius: 10px;
            width: 400px;
            margin: auto;
            box-shadow: 0 2px 8px rgba(0,0,0,0.2);
        }
        .input {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            border-radius: 6px;
            border: 1px solid #ccc;
        }
        .btn {
            padding: 12px;
            width: 100%;
            background: #1E88E5;
            border: none;
            color: white;
            border-radius: 6px;
            cursor: pointer;
        }
    </style>
</head>
<body>

<h2>➕ Registrar Nuevo Usuario</h2>

<div class="form-box">
    <form action="agregarUsuario" method="post">

        <input type="text" name="identificacion" placeholder="Identificación" class="input" required>
        <input type="text" name="nombre" placeholder="Nombre" class="input" required>
        <input type="text" name="apellido" placeholder="Apellido" class="input" required>
        <input type="email" name="email" placeholder="Email" class="input" required>
        <input type="text" name="usuario" placeholder="Usuario" class="input" required>
        <input type="password" name="clave" placeholder="Clave" class="input" required>

        <button class="btn">Registrar</button>
    </form>
</div>

</body>
</html>
