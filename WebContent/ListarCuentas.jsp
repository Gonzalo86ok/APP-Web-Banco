<%@ page import="entidad.Cliente"%>
<%@ page import="entidad.Cuenta"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inicio Admin</title>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">

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
        max-width: 1000px;
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
     table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px; /* Espacio arriba de la tabla */
    }
    th, td {
        border: 1px solid #ddd; /* Borde delgado entre celdas */
        padding: 8px;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
    }
    tr:nth-child(even) {
        background-color: #f9f9f9;
    }
</style>
</head>
<body>
    <div class="contenedor">
        <h1>Lista de cuentas clientes</h1>
        <div class="menu">    
	          <% Cliente cliente = (Cliente) session.getAttribute("cliente"); %>
	            <% if (cliente != null) { %>
	                <a href="InicioAdmin.jsp?clienteDni=<%= cliente.getDni() %>">Inicio</a>
	            <% } else { %>
	                <a href="InicioAdmin.jsp">Inicio</a>
	            <% } %>       
            <a href="servletCliente?Listar=1">Lista de Clientes Activos</a>
            <a href="servletCliente?ListarInactivo=2">Lista de Clientes Inactivos</a>
            <a href="servletCliente?CrearClienteAdmin=1">Crear cliente</a>
            <a href="servletCliente?AltaCuentas=1">Altas De Cuentas</a>  
            <a href="servletCliente?ListarSolicitudPrestamos=1">Listar Solicitudes de Prestamos</a>
            <a href="servletCliente?Estadisticas=1">Ver Estadísticas</a>        
        </div>
        
         <div class="usuario">
          <% 
                if (cliente != null) {
            %>
                <a href="servletCliente?Param=1&clienteDni=<%= cliente.getDni() %>">
                    <%= cliente.getNombre() + " " + cliente.getApellido() %>
                </a>
            <% } %>          
            <form action="servletCliente" method="post">
				<input type="submit" class="btn" value="Cerrar sesión" name="btnLogout">
			</form>
        </div>
        
        <table id="table_id" class="display">
            <thead>
                <tr>
                    <th>NUMERO DE CUENTA</th>
                    <th>DNI CLIENTE</th>
                    <th>FECHA DE CREACION</th>
                    <th>ID TIPO DE CUENTA</th>
                    <th>CBU</th>
                    <th>SALDO</th>
                    <th>ESTADO</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <% 
                    ArrayList<Cuenta> cuentasCliente = (ArrayList<Cuenta>) request.getAttribute("cuentas");
                    if (cuentasCliente != null && !cuentasCliente.isEmpty()) {
                        for (Cuenta cuenta : cuentasCliente) {
                %>
                            <tr>
                                <td><%= cuenta.getNumeroDeCuenta() %></td>
                                <td><%= cuenta.getDni() %></td>
                                <td><%= new java.text.SimpleDateFormat("dd-MM-yyyy").format(cuenta.getFechaDeCreacion()) %></td>
                                <td><%= cuenta.getTipoCuenta().getDescripcion() %></td>
                                <td><%= cuenta.getCbu() %></td>
                                <td><%= cuenta.getSaldo() %></td>
                                <td><%= cuenta.getEstado() %></td>
                                <td>                                                                   
                                    <form action="servletCliente" method="post" style="display: inline;">
                                        <input type="hidden" name="numCuenta" value="<%= cuenta.getNumeroDeCuenta() %>">
                                        <!-- Botón para eliminar con confirmación -->
                                        <button type="submit" name="btnEliminarCuenta" onclick="return confirm('¿Está seguro que desea eliminar la cuenta número <%= cuenta.getNumeroDeCuenta() %>?')">Eliminar</button>
                                    </form>
                                </td>
                            </tr>
                <% 
                        }
                    } else {
                %>
                        <tr>
                            <td colspan="8">No hay cuentas para mostrar.</td>
                        </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </div>
</body>

</html>
