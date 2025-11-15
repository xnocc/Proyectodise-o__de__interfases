<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, modelo.Finanza" %>
<%@ page import="java.text.NumberFormat" %> 
<%@ page import="java.util.Locale" %> 

<%
    // Recuperar el resumen y los movimientos del Controlador
    Map<String, Double> resumen = (Map<String, Double>) request.getAttribute("resumenFinanciero"); 
    // List<Finanza> movimientos = (List<Finanza>) request.getAttribute("movimientosRecientes");
    
    double ingresos = resumen.getOrDefault("Ingreso", 0.0);
    double gastos = resumen.getOrDefault("Gasto", 0.0);
    double balance = ingresos - gastos;
    String estado = balance >= 0 ? "Superávit" : "Déficit";
    
    // Formato de moneda colombiana
    Locale colombia = new Locale("es", "CO");
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(colombia);

    // Lógica para el ancho de la barra (Gráfico de barras simple)
    double totalFlujo = ingresos + gastos;
    double porcentajeIngresos = totalFlujo > 0 ? (ingresos / totalFlujo) * 100 : 0;
    double porcentajeGastos = totalFlujo > 0 ? (gastos / totalFlujo) * 100 : 0;
%>

<style>
    .resumen-card {
        padding: 20px; 
        background: #e3f2fd; 
        border-radius: 10px; 
        margin-bottom: 20px;
    }
    .flujo-bar {
        display: flex;
        width: 100%;
        height: 30px;
        margin-top: 15px;
        border-radius: 5px;
        overflow: hidden;
    }
    .bar-ingresos {
        background-color: #4CAF50; /* Verde */
        height: 100%;
        text-align: center;
        color: white;
        line-height: 30px;
        font-weight: bold;
    }
    .bar-gastos {
        background-color: #F44336; /* Rojo */
        height: 100%;
        text-align: center;
        color: white;
        line-height: 30px;
        font-weight: bold;
    }
</style>

<h1 style="color:#1E88E5;">🏠 Dashboard</h1>

<p>Bienvenido al sistema Contabilidad Pro.</p>

<div class="resumen-card">
    <h3>📊 Resumen Financiero</h3>
    
    <div style="display: flex; justify-content: space-around; text-align: center; margin-bottom: 20px;">
        <div style="color: #4CAF50;">
            <strong>Ingresos:</strong><br>
            <%= currencyFormat.format(ingresos) %>
        </div>
        <div style="color: #F44336;">
            <strong>Gastos:</strong><br>
            <%= currencyFormat.format(gastos) %>
        </div>
        <div style="color: <%= balance >= 0 ? "#1E88E5" : "#e53935" %>;">
            <strong>Balance:</strong><br>
            <%= currencyFormat.format(balance) %> (<%= estado %>)
        </div>
    </div>
    
    <h4>Flujo de Caja (Comparación)</h4>
    
    <div class="flujo-bar">
        <%-- Barra de Ingresos --%>
        <div class="bar-ingresos" style="width: <%= porcentajeIngresos %>%;">
            <% if (porcentajeIngresos > 10) { %>
                <%= String.format("%.0f", porcentajeIngresos) %> %
            <% } %>
        </div>
        
        <%-- Barra de Gastos --%>
        <div class="bar-gastos" style="width: <%= porcentajeGastos %>%;">
             <% if (porcentajeGastos > 10) { %>
                <%= String.format("%.0f", porcentajeGastos) %> %
            <% } %>
        </div>
    </div>
    <small style="display: block; margin-top: 5px;">Muestra la proporción de Ingresos vs. Gastos sobre el total del flujo.</small>

</div>

<%-- ELIMINADA: La sección "⚙️ Información General del Sistema" --%>

<div class="resumen-card" style="width: 60%;">
    <h3>🗓️ Movimientos Financieros Recientes</h3>
    
    <%-- Aquí deberías incluir un fragmento de la tabla de finanzas más recientes.
         Si tienes un JSP para los movimientos recientes, lo incluyes aquí.
         Si quieres mostrar solo una lista:
    --%>
    <ul>
        <%
        // Asumiendo que 'movimientosRecientes' contiene la lista de Finanzas
        List<Finanza> movimientos = (List<Finanza>) request.getAttribute("movimientosRecientes");
        if (movimientos != null && !movimientos.isEmpty()) {
            // Mostrar solo los primeros 5 movimientos
            for (int i = 0; i < Math.min(movimientos.size(), 5); i++) {
                Finanza f = movimientos.get(i);
                String color = "Ingreso".equalsIgnoreCase(f.getTipo()) ? "#4CAF50" : "#F44336";
                out.println("<li><strong style='color: "+color+"'>"+f.getTipo()+"</strong> de "+currencyFormat.format(f.getMonto())+" por '"+f.getDescripcion()+"' ("+f.getFecha()+")</li>");
            }
        } else {
            out.println("<li>No hay movimientos recientes registrados.</li>");
        }
        %>
    </ul>
    
</div>