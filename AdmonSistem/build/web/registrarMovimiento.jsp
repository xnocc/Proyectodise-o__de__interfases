<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    HttpSession s = request.getSession(false);
    if (s == null || s.getAttribute("idUsuario") == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    String tipo = request.getParameter("tipo");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrar Movimiento</title>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>

<body style="font-family:Arial; background:#fafafa; margin:20px;">

<h2><i class="fa fa-edit"></i> Registrar <%= tipo.equals("ingreso") ? "Ingreso" : "Gasto" %></h2>

<form action="CtrolFinanzas" method="post"
      style="background:white; padding:20px; width:400px; border-radius:10px;
             box-shadow:0 0 10px rgba(0,0,0,0.1);">

    <input type="hidden" name="tipo" value="<%= tipo %>">

    <label>Monto</label><br>
    <input type="number" step="0.01" name="monto" required 
           style="width:100%; padding:8px;"><br><br>

    <label>Descripción</label><br>
    <textarea name="descripcion" required
              style="width:100%; padding:8px; height:80px;"></textarea><br><br>

    <input type="hidden" name="accion" value="Agregar">

    <button style="background:#1565C0; padding:10px 20px; 
                   color:white; border:none; border-radius:6px; cursor:pointer;">
        Guardar
    </button>
</form>

</body>
</html>
