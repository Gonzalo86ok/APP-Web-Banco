<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidad.Cliente" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidad.Cuenta" %>
<%@ page import="entidad.Prestamo" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Inicio</title>
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
        .informacion-cuenta {
            display: block;
            text-align: center;
            margin: 15px 0;
        }
        .cuenta {
            margin-bottom: 15px;
        }
        .cuenta a {
            margin-right: 15px;
            color: #007bff;
            text-decoration: none;
        }
        .cuenta a:hover {
            text-decoration: underline;
        }
        .btn {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
        }
        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="contenedor">
        <h1>Inicio</h1>
        <div class="menu">
            <a href="Transferencias.jsp">Transferencias</a>
            <a href="servletCliente?PrestamosxCliente">Préstamos</a>
            <a href="servletCliente?SolicitarPrestamos=1">Solicitar Prestamos</a>
            
        </div>
        
        <div class="usuario">
            <% Cliente cliente = (Cliente) session.getAttribute("cliente"); %>
            <% ArrayList<Cuenta> cuentasActivas = (ArrayList<Cuenta>) session.getAttribute("cuentasActivas"); %>
            
            <% boolean tieneCajaAhorro = false; %>
            <% boolean tieneCuentaCorriente = false; %>
            
            <% if (cliente != null) { %>
                <a href="servletCliente?Param=1&clienteDni=<%= cliente.getDni() %>">
                    <%= cliente.getNombre() + " " + cliente.getApellido() %>
                </a>
            <% } %>
            
            <form action="servletCliente" method="post">
				<input type="submit" class="btn" value="Cerrar sesión" name="btnLogout">
			</form>
        </div>     
                        
                               
          <% if (cuentasActivas != null && !cuentasActivas.isEmpty()) { %>
            <div class="informacion-cuenta">
                <% int numCajasAhorro = 0; %>
                <% int numCuentasCorrientes = 0; %>
                
                <% for (Cuenta cuenta : cuentasActivas) { %>
                
                    <% if ("Caja de ahorro".equals(cuenta.getTipoCuenta().getDescripcion())) { %>
                        <% numCajasAhorro++; %>
                        <div class="cuenta">
                           <a href="servletCliente?movimiento=1&NumCuenta=<%= cuenta.getNumeroDeCuenta() %>">Caja de ahorro</a>
                            <span>$ <%= cuenta.getSaldo() %></span>
                        </div>
                        
                    <% } else if ("Cuenta corriente".equals(cuenta.getTipoCuenta().getDescripcion())) { %>
                        <% numCuentasCorrientes++; %>
                        <div class="cuenta">
                           <a href="servletCliente?movimiento=1&NumCuenta=<%= cuenta.getNumeroDeCuenta() %>">Cuenta corriente</a>
                            <span>$ <%= cuenta.getSaldo() %></span>
                        </div>
                    <% } %>
                    
                <% } %>
                                         
                <% if (numCajasAhorro == 1) { %>
                    <div class="informacion-cuenta">
                        <a href="servletCliente?Caja=1&clienteDNI=<%= cliente.getDni() %>">Solicitar caja de ahorro</a> 
                    </div>               
                <%} %>
                <% if (numCuentasCorrientes == 0) { %>
                    <div class="informacion-cuenta">
                        <a href="servletCliente?Cuenta=1&clienteDNI=<%= cliente.getDni() %>">Solicitar cuenta corriente</a> 
                    </div>
                <% } %>         
            </div>                    
        <% } %>       
    </div>           
</body>
</html>
