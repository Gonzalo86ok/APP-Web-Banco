<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="entidad.Cuenta" %>   
<%@ page import="entidad.Cliente" %>  
<%@ page import="java.util.ArrayList" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Solicitud de prestamos</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f9;
        margin: 0;
        padding: 20px;
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
    }
    .contenedor {
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        width: 100%;
        max-width: 600px;
        box-sizing: border-box;
        display: flex;
        flex-direction: column;
        align-items: center;
    }
    h1 {
        text-align: center;
        color: #333;
        margin-bottom: 20px;
    }
    .menu {
        display: flex;
        justify-content: center;
        gap: 15px;
        margin-bottom: 20px;
    }
    .menu a {
        color: #007bff;
        text-decoration: none;
        padding: 10px 15px;
        background-color: white;
        border-radius: 5px;
        transition: background-color 0.3s;
        display: inline-block;
    }
    .menu a:hover {
        background-color: #f0f0f0;
    }
    .usuario {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 10px;
        margin-top: 20px;
        border-top: 1px solid #ccc;
        width: 100%;
    }
    .usuario a {
        color: #007bff;
        text-decoration: none;
    }
    .usuario a:hover {
        text-decoration: underline;
    }
    .usuario form {
        margin: 0;
    }
    .btn {
        background-color: #007bff;
        color: white;
        border: none;
        padding: 10px 20px; 
        border-radius: 5px;
        cursor: pointer;
        text-align: center;
        display: inline-block; 
        width: auto; 
        margin-top: 10px;
    }
    .btn:hover {
        background-color: #0056b3;
    }
    form {
        width: 100%;
        display: flex;
        flex-direction: column;
        gap: 10px;
    }
    label {
        font-weight: bold;
        margin-bottom: 5px;
    }
    input[type="text"], select {
        width: 100%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
    }
     select#cuentaDestino {
        margin-bottom: 10px; 
    }
    @media (max-width: 600px) {
        .contenedor {
            padding: 10px;
        }
    }
</style>
</head>
<body>
    <div class="contenedor">
        <h1>Solicitud de prestamos</h1>
        <div class="menu">        
            <a href="servletCliente?AccionInicio=1">Inicio</a>
            <a href="Transferencias.jsp">Transferencias</a>
            <a href="servletCliente?PrestamosxCliente">Préstamos</a>
        </div>
        
        <div class="usuario">
            <% Cliente cliente = (Cliente) session.getAttribute("cliente"); %>
            <% if (cliente != null) { %>
                <a href="servletCliente?Param=1&clienteDni=<%= cliente.getDni() %>">
                    <%= cliente.getNombre() + " " + cliente.getApellido() %>
                </a>
            <% } %>  
            
            <form action="servletCliente" method="post">
                <input type="submit" class="btn" value="Cerrar sesión" name="btnLogout">
            </form>             
        </div>
        
        <% ArrayList<Cuenta> cuentasCliente = (ArrayList<Cuenta>) request.getAttribute("ListCuentasActivas"); %>
        
        <!-- Formulario de solicitud de prestamo -->
        <form action="servletCliente" method="post">
            <label for="txtMontoSolicitado">MONTO:</label>
            <input type="text" id="txtMontoSolicitado" name="txtMontoSolicitado"> 
                
            <label for="txtCantidadDeCuotas">Cantidad de Cuotas</label>
            <select id="txtCantidadDeCuotas" name="txtCantidadDeCuotas" required>
                <option value="">Seleccione cantidad de cuotas</option>                
                <option value="1">3 Cuotas </option>
                <option value="2">6 Cuotas</option>
                <option value="3">12 Cuotas</option>    
            </select>
            
            <label for="cuentaDestino">Seleccionar cuenta de destino:</label>
            <select id="cuentaDestino" name="txtCuentaDestino">
                <% for (Cuenta cuenta : cuentasCliente) { %>
                    <option value="<%= cuenta.getCbu() %>">
                        <%= cuenta.getTipoCuenta().getDescripcion() %> - CBU: <%= cuenta.getCbu() %> - Numero de Cuenta: <%= cuenta.getNumeroDeCuenta() %>
                    </option>
                <% } %>
            </select>
            
            <!-- Botón de submit para transferir -->
            <input type="submit" name="btnSolicitarPrestamo" value="Solicitar Prestamos" class="btn">   
        </form>
    </div>
</body>
</html>
