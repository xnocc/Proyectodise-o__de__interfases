<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
String usuario = (String) session.getAttribute("nUsuario");
String nombreCompleto = (String) session.getAttribute("nombreCompleto");

if (usuario == null) {
    response.sendRedirect("index.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panel – Contabilidad Pro</title>
    <style>
        body { margin: 0; font-family: Arial; background: #f4f4f4; }
        .header {
            width: 100%;
            background: #1E88E5;
            padding: 18px;
            color: white;
            font-size: 22px;
        }
        .sidebar {
            width: 260px;
            background: #263238;
            height: calc(100vh - 60px);
            float: left;
            padding: 20px;
            color: white;
        }
        .menu-item {
            background: #37474F;
            padding: 12px;
            border-radius: 6px;
            margin-top: 15px;
        }
        a { color: white; text-decoration: none; font-size: 16px; }
        .main {
            margin-left: 260px;
            padding: 20px;
        }
        iframe {
            width: 100%;
            height: calc(100vh - 100px);
            border: none;
            border-radius: 10px;
            background: white;
            box-shadow: 0 0 8px rgba(0,0,0,0.2);
        }
    </style>
</head>
<body>

<div class="header">
    📊 Contabilidad Pro
    <span style="float:right; font-size:18px;">
        <%= nombreCompleto %> |
        <a href="cerrarSesion" style="color:white;">Cerrar Sesión</a>
    </span>
</div>

<div class="sidebar">
    <h3>Navegación</h3>

    <div class="menu-item">
        <a href="front.jsp" target="marco">🏠 Dashboard</a>
    </div>

    <div class="menu-item">
        <a href="listarUsuarios.jsp" target="marco">👤 Gestión de Usuarios</a>
    </div>

    <div class="menu-item">
        <a href="perfilUsuario" target="marco">🧾 Mi Perfil</a>
    </div>

    <div class="menu-item">
        <a href="finanzas" target="marco">💰 Finanzas Personales</a>
    </div>

</div>

<div class="main">
    <iframe name="marco" src="front.jsp"></iframe>
</div>

</body>
</html>
