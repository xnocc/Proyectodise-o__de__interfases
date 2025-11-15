<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Mensaje</title>
    <style>
        .msg {
            margin: 50px auto;
            padding: 20px;
            width: 400px;
            border-radius: 8px;
            text-align: center;
            font-size: 18px;
        }
        .ok {
            background: #d4edda;
            color: #155724;
        }
        .error {
            background: #f8d7da;
            color: #721c24;
        }
    </style>
</head>
<body>

<%
    String tipo = (String) request.getAttribute("tipo");
    String texto = (String) request.getAttribute("mensaje");
%>

<div class="msg <%= "ok".equals(tipo) ? "ok" : "error" %>">
    <%= texto %>
</div>

<br>
<a href="index.jsp">Volver al inicio</a>

</body>
</html>
