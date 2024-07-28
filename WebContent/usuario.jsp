<%@page import = "entidad.Cliente" %>
<%@page import = "java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detalles del usuario</title>
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
        flex-wrap: wrap;
    }
    .menu a {
        color: #007bff;
        text-decoration: none;
        padding: 10px 15px;
        background-color: white;
        border-radius: 5px;
        transition: background-color 0.3s;
        flex: 1;
        text-align: center;
    }
    .menu a:hover {
        background-color: #f0f0f0;
    }
    .usuario {
        display: flex;
        flex-direction: column;
        align-items: center;
        padding: 10px;
        margin-top: 20px;
        border-top: 1px solid #ccc;
    }
    .usuario a {
        color: #007bff;
        text-decoration: none;
        margin-bottom: 10px;
    }
    .usuario a:hover {
        text-decoration: underline;
    }
    .usuario form {
        margin: 0;
        width: 100%;
        display: flex;
        justify-content: center;
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
    .user-details {
        margin-top: 20px;
    }
    .user-details label {
        display: block;
        font-weight: bold;
        margin-top: 10px;
    }
    .user-details span {
        display: block;
        margin-top: 5px;
    }
    @media (max-width: 600px) {
        .contenedor {
            padding: 10px;
        }
        .menu a {
            flex: 100%;
        }
        .btn {
            width: 100%;
        }
    }
</style>
</head>
<body>
    <div class="contenedor">
        <h1>Detalles del cliente</h1>
         <% Cliente cliente = (Cliente) session.getAttribute("cliente"); %>
         
        <div class="menu">
            <a href="inicio.jsp?clienteDni=<%= cliente.getDni() %>">Inicio</a>    
            <a href="Transferencias.jsp">Transferencias</a>
            <a href="Prestamos.jsp">Préstamos</a>
        </div>

		     <div class="usuario">
           <% if (cliente != null) { %>
                <a href="servletCliente?Param=1&clienteDni=<%= cliente.getDni() %>">
                    <%= cliente.getNombre() + " " + cliente.getApellido() %>
                </a>
            <% } %>
            <form action="servletCliente" method="post">
				<input type="submit" class="btn" value="Cerrar sesión" name="btnLogout">
			</form>
        </div>

        <div class="user-details">
            <h2>Información del Usuario</h2>
            <% if (cliente != null) { %>
                <label>DNI:</label><span><%= cliente.getDni() %></span>
                <label>CUIL:</label><span><%= cliente.getCuil() %></span>
                <label>Nombre:</label><span><%= cliente.getNombre() %></span>
                <label>Apellido:</label><span><%= cliente.getApellido() %></span>
                <label>Sexo:</label><span><%= cliente.getSexo().getDescripcion() %></span>
                <label>Fecha de Nacimiento:</label><span><%= cliente.getFechaNacimiento() %></span>
                <label>Nacionalidad:</label><span><%= cliente.getNacionalidad() %></span>
                <label>País:</label><span><%= cliente.getLocalidad().getProvincia().getPais().getNombre() %></span>
                <label>Provincia:</label><span><%= cliente.getLocalidad().getProvincia().getNombre() %></span>
                <label>Localidad:</label><span><%= cliente.getLocalidad().getNombre() %></span>
                <label>Dirección:</label><span><%= cliente.getDireccion() %></span>
                <label>Email:</label><span><%= cliente.getEmail() %></span>
                <label>Teléfono:</label><span><%= cliente.getTelefono() %></span>
            <% } %>
        </div>
        
   
    </div>  
</body>
</html>
