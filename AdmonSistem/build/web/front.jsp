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
    <meta charset="UTF-8">
    <title>Contabilidad Pro</title>

    <style>
        body { margin: 0; font-family: Arial; }

        .sidebar {
            width: 250px;
            height: 100vh;
            background: #1e2a36;
            padding: 20px;
            position: fixed;
            color: white;
        }

        .sidebar h2 {
            color: #90caf9;
        }

        .sidebar a {
            display: block;
            background: #263644;
            padding: 10px;
            margin-bottom: 10px;
            color: white;
            text-decoration: none;
            border-radius: 6px;
        }

        .sidebar a:hover {
            background: #1565c0;
        }

        .content {
            margin-left: 270px;
            padding: 20px;
        }
    </style>
</head>

<body>

<div class="sidebar">
    <h2>Contabilidad Pro</h2>

    <a href="ControladorUsuario?accion=dashboard">🏠 Dashboard</a>
    <a href="ControladorUsuario?accion=listar">👤 Gestión de Usuarios</a>
    <a href="ControladorUsuario?accion=perfil">📄 Mi Perfil</a>
    <a href="ControladorUsuario?accion=finanzas">💰 Finanzas Personales</a>
</div>

<div class="content">
    <jsp:include page="${pagina}" />
</div>

</body>
</html>
