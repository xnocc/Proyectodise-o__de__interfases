<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, modelo.Finanza" %>
<%@ page import="java.text.NumberFormat" %> 
<%@ page import="java.util.Locale" %> <%-- Necesario para el formato colombiano --%>

<%
    List<Finanza> lista = (List<Finanza>) request.getAttribute("finanzas");
    
    // Configuración del formato de moneda para Colombia (es_CO)
    Locale colombia = new Locale("es", "CO");
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(colombia);
%>

<%-- ELIMINADAS: <html>, <head>, y <body> --%>

<style>
    /* Mantenemos los estilos aquí para el contenido, quitando el body/margin */
    .container {
        width: 100%;
        margin: auto;
        padding: 0;
    }
    h2 {
        color: #1E88E5;
        margin-bottom: 20px;
    }
    table {
        width: 100%;
        background: white;
        border-collapse: collapse;
        box-shadow: 0 0 8px rgba(0,0,0,0.2);
    }
    th, td {
        padding: 12px;
        border-bottom: 1px solid #ddd;
    }
    th {
        background: #1E88E5;
        color: white;
    }
    a.btn {
        padding: 6px 10px;
        text-decoration: none;
        background: #1E88E5;
        color: white;
        border-radius: 4px;
        display: inline-block;
    }
    a.danger {
        background: #e53935;
    }
</style>


<div class="container">
    <h2>📊 Finanzas del Usuario</h2>

    <%-- El enlace llama directamente a CtrolFinanzas?accion=nuevo --%>
    <a href="CtrolFinanzas?accion=nuevo" class="btn">➕ Agregar Registro</a>
    <br><br>

    <table>
        <tr>
            <th>Tipo</th>
            <th>Monto</th>
            <th>Descripción</th>
            <th>Fecha</th>
            <th>Acciones</th>
        </tr>

        <%
            if (lista != null) {
                for (Finanza f : lista) {
        %>
        <tr>
            <td><%= f.getTipo() %></td>
            <%-- CORRECCIÓN CLAVE: Aplicar el formato de moneda colombiana --%>
            <td><%= currencyFormat.format(f.getMonto()) %></td> 
            
            <td><%= f.getDescripcion() %></td>
            <td><%= f.getFecha() %></td>
            <td>
                <%-- Enlaces a CtrolFinanzas corregidos --%>
                <a href="CtrolFinanzas?accion=editar&id=<%= f.getId() %>" class="btn">✏ Editar</a>
                <a href="CtrolFinanzas?accion=eliminar&id=<%= f.getId() %>" 
                    class="btn danger"
                    onclick="return confirm('¿Eliminar registro?');">🗑 Eliminar</a>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>

</div>

<%-- ELIMINADA: </body> y </html> --%>