<%@page import="entidad.Cliente"%>
<%@page import="java.util.List"%>
<%@page import="entidad.SolicitudDeAlta"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alta de cuentas</title>
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
        width: auto%;
        border-collapse: collapse;
        margin-top: 20px;       
    }
    table, th, td {
        border: 1px solid #ccc;
    }
    th, td {
        padding: 10px;
        text-align: left;
    }
    th {
        background-color: #f4f4f9;
    }
</style>
</head>
<body>
    <div class="contenedor">
        <h1>Alta de cuentas</h1>
        
        <div class="menu">      
         <%
           	Cliente cliente = (Cliente) session.getAttribute("cliente");
           %>     
            <%
                 	if (cliente != null) {
                 %>
                <a href="InicioAdmin.jsp?clienteDni=<%=cliente.getDni()%>">Inicio</a>
            <%
            	} else {
            %>
                <a href="InicioAdmin.jsp">Inicio</a>
            <%
            	}
            %>        
            <a href="servletCliente?Listar=1" >Lista de Clientes Activos</a>
            <a href="servletCliente?ListarInactivo=2" >Lista de Clientes Inactivos</a>
            <a href="servletCliente?CrearClienteAdmin=1">Crear cliente</a>
            <a href="servletCliente?ListarCuentasClientes=1">Listar Cuentas</a>
            <a href="servletCliente?ListarSolicitudPrestamos=1">Listar Solicitudes de Prestamos</a>
            <a href="servletCliente?Estadisticas=1">Ver Estadísticas</a>            
        </div>
              
        <div class="usuario">
          <%
          	if (cliente != null) {
          %>
                <a href="servletCliente?Param=1&clienteDni=<%=cliente.getDni()%>">
                    <%=cliente.getNombre() + " " + cliente.getApellido()%>
                </a>
            <%
            	}
            %>          
            <form action="servletCliente" method="post">
				<input type="submit" class="btn" value="Cerrar sesión" name="btnLogout">
			</form>
        </div>    
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>DNI Cliente</th>
                    <th>Tipo de Cuenta</th>
                    <th>Estado</th>
                    <th></th>
                </tr>
            </thead>
             <tbody>
                <% 
                    List<SolicitudDeAlta> solicitudes = (List<SolicitudDeAlta>) request.getAttribute("solicitudes");
                    for (SolicitudDeAlta solicitud : solicitudes) {
                %>
                   <tr>
					    <td><%= solicitud.getId() %></td>
					    <td><%= solicitud.getCliente().getDni() %></td> 
					    <td><%= solicitud.getTipoCuenta().getDescripcion() %></td>
					    <td><%= solicitud.getEstado() %></td>
					    <td>
					        <form action="servletCliente" method="post">
					            <input type="hidden" name="solicitudId" value="<%= solicitud.getId() %>">
					            <input type="submit" name="btnActivarCuenta" value="Activar">
					        </form>
					    </td>
					</tr>

                <% } %>
            </tbody>
        </table>
     
    </div>
</body>
</html>