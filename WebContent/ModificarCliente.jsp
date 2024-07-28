<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Pais"%>
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Localidad"%>
<%@page import="entidad.Generos"%>
<%@page import="entidad.Cliente"%>
<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar Cliente</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f9;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
	overflow: auto;
}

.container {
	background-color: #fff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	width: 100%;
	max-width: 500px;
	box-sizing: border-box;
}

h2 {
	text-align: center;
	color: #333;
}

form {
	display: flex;
	flex-direction: column;
}

label {
	margin-top: 10px;
	font-weight: bold;
}

input[type="text"], input[type="date"], input[type="email"], input[type="password"]
	{
	width: 100%;
	padding: 8px 10px;
	margin-top: 5px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

input[type="submit"] {
	background-color: #4CAF50;
	color: white;
	border: none;
	padding: 10px 20px;
	margin-top: 20px;
	border-radius: 4px;
	cursor: pointer;
	width: 100%;
}

input[type="submit"]:hover {
	background-color: #45a049;
}
</style>
</head>
<body>
	<div class="container">

		<h2>Modificar Cliente</h2>
		
		<%
			Cliente cliente = (Cliente) request.getAttribute("clienteModificar");
			Usuario usuario = (Usuario) request.getAttribute("usuarioModificar");
		%>

		<form action="servletCliente" method="post">
			<label for="txtDNI">DNI</label> 
			<input type="text" id="txtDNI" name="txtDNI" value="<%=cliente.getDni()%>" required maxlength="8" readonly> 
			
			<label for="txtCUIL">CUIL</label> 
			<input type="text" id="txtCUIL" name="txtCUIL" value="<%=cliente.getCuil()%>" required maxlength="11"> 
			
			<label for="txtNombre">Nombre</label> 
			<input type="text" id="txtNombre" name="txtNombre" value="<%=cliente.getNombre()%>" required>

			<label for="txtApellido">Apellido</label> 
			<input type="text" id="txtApellido" name="txtApellido" value="<%=cliente.getApellido()%>" required>
			
			<label for="txtSexo">Sexo</label>
			<select id="txtSexo" name="txtSexo" required>
				<option value="<%=cliente.getSexo().getId()%>"><%=cliente.getSexo().getDescripcion()%></option>
				<%
					ArrayList<Generos> listaGeneros = (ArrayList<Generos>) request.getAttribute("listaG");
					if (listaGeneros != null) {
						for (Generos genero : listaGeneros) {
				%>
				<option value="<%=genero.getId()%>"><%=genero.getDescripcion()%></option>
				<%
					}
					} else {
				%>
				<option value="">No hay géneros disponibles</option>
				<%
					}
				%>
			</select> 
			
			<label for="txtNacionalidad">Nacionalidad</label> 
			<input type="text" id="txtNacionalidad" name="txtNacionalidad" value="<%=cliente.getNacionalidad()%>" required> 
			
			<label for="txtFecNac">Fecha de Nacimiento</label> 
			<input type="date" id="txtFecNac" value="<%=cliente.getFechaNacimiento()%>" name="txtFecNac" required> 
			
			<label for="txtDireccion">Dirección</label>
			<input type="text" id="txtDireccion" value="<%=cliente.getDireccion()%>" name="txtDireccion" required>
			
			<label for="txtLocalidad">Localidad</label> 
			<select id="txtLocalidad"
				name="txtLocalidad" required>
				<option value="<%=cliente.getLocalidad().getId()%>"><%=cliente.getLocalidad().getNombre()%></option>
				<%
					//System.out.println(idProvincia);
					ArrayList<Localidad> localidades = (ArrayList<Localidad>) request.getAttribute("listaL");
					if (localidades != null) {
						for (Localidad localidad : localidades) {
				%>
				<option value="<%=localidad.getId()%>"><%=localidad.getNombre()%></option>
				<%
					}
					} else {
				%>
				<option value="">No hay localidades disponibles</option>
				<%
					}
				%>
			</select>
			
			<label for="txtEmail">Email</label> 
			<input type="email" id="txtEmail" value="<%=cliente.getEmail()%>" name="txtEmail" required>
			
			<label for="txtTelefono">Teléfono</label> 
			<input type="text" id="txtTelefono" name="txtTelefono" value="<%=cliente.getTelefono()%>" required> 
			
			<label for="txtContrasenia">Contraseña</label> 
			<input type="text" id="txtContrasenia" name="txtContrasenia" value="<%=usuario.getContraseña()%>" required> 
			
			<input type="submit" value="Modificar" name="btnModificarCliente">
			
		</form>
	</div>
</body>
</html>
