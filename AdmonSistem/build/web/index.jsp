<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Iniciar Sesión</title>
</head>
<body style="
    margin:0;
    padding:0;
    background:#f0f4f8;
    font-family:Arial, sans-serif;
">

<!-- CONTENEDOR PRINCIPAL -->
<div style="
    width:100%;
    height:100vh;
    display:flex;
    justify-content:center;
    align-items:center;
">

    <!-- TARJETA DEL LOGIN -->
    <div style="
        width:380px;
        background:white;
        padding:30px;
        border-radius:12px;
        box-shadow:0 4px 12px rgba(0,0,0,0.1);
        text-align:center;
    ">

        <!-- LOGO (puedes cambiar la imagen por la que quieras) -->
        <img src="https://cdn-icons-png.flaticon.com/512/1828/1828506.png" 
             width="90" 
             style="margin-bottom:20px;" />

        <h2 style="
            margin-bottom:20px;
            color:#333;
        ">Iniciar Sesión</h2>

        <form method="post" action="ctrolValidar">

            <div style="text-align:left; margin-bottom:15px;">
                <label style="font-weight:bold;">Usuario</label><br>
                <input type="text" name="cusuario" required
                       style="
                           width:95%;
                           padding:10px;
                           border-radius:6px;
                           border:1px solid #ccc;
                           margin-top:5px;
                       "/>
            </div>

            <div style="text-align:left; margin-bottom:20px;">
                <label style="font-weight:bold;">Contraseña</label><br>
                <input type="password" name="cclave" required
                       style="
                           width:95%;
                           padding:10px;
                           border-radius:6px;
                           border:1px solid #ccc;
                           margin-top:5px;
                       "/>
            </div>

            <input type="submit" name="accion" value="Ingresar"
                   style="
                       width:100%;
                       padding:12px;
                       background:#1E88E5;
                       border:none;
                       color:white;
                       font-size:16px;
                       border-radius:6px;
                       cursor:pointer;
                       transition:0.3s;
                   "
                   onmouseover="this.style.background='#1565C0'"
                   onmouseout="this.style.background='#1E88E5'"
            />

        </form>

    </div>
</div>

</body>
</html>
