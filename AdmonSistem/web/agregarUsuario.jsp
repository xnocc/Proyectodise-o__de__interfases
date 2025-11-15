<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    HttpSession s = request.getSession(false);
    if (s == null || s.getAttribute("idUsuario") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrar Usuario</title>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>

<body style="font-family:Arial; background:#fafafa; margin:20px;">

<h2><i class="fa fa-user-plus"></i> Registrar Usuario</h2>

<form action="CtrolUsuario" method="post"
      style="background:white; padding:20px; width:400px; border-radius:10px;
             box-shadow:0 0 10px rgba(0,0,0,0.1);">

    <label>Identificación</label><br>
    <input type="text" name="identificacion" required style="width:100%; padding:8px;"><br><br>

    <label>Nombre</label><br>
    <input type="text" name="nombre" required style="width:100%; padding:8px;"><br><br>

    <label>Apellido</label><br>
    <input type="text" name="apellido" required style="width:100%; padding:8px;"><br><br>

    <label>Email</label><br>
    <input type="email" name="email" required style="width:100%; padding:8px;"><br><br>

    <label>Usuario</label><br>
    <input type="text" name="usuario" required style="width:100%; padding:8px;"><br><br>

    <label>Contraseña</label><br>
    <input type="password" name="clave" required style="width:100%; padding:8px;"><br><br>

    <input type="hidden" name="accion" value="Agregar">

    <button style="background:#2E7D32; padding:10px 20px; color:white;
                   border:none; border-radius:6px; cursor:pointer;">
        Guardar Usuario
    </button>
</form>

</body>
</html>
