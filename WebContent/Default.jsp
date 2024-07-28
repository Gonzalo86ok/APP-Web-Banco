
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f9;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.container {
	background-color: #fff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	width: 100%;
	max-width: 400px;
	text-align: center;
}

h1 {
	color: #333;
}

.form-group {
	margin: 15px 0;
}

input[type="text"], input[type="password"] {
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

a {
	display: block;
	margin-top: 20px;
	color: #4CAF50;
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}

.error-message {
	color: red;
	margin: 10px 0;
}
</style>
</head>
<body>
	<div class="container">
		<h1>Iniciar sesión</h1>

		<%
			String errorMessage = (String) request.getAttribute("errorMessage");
			if (errorMessage != null) {
		%>
		<div class="error-message"><%=errorMessage%></div>
		<%
			}
		%>
		<form action="servletCliente" method="post">
			<div class="form-group">
			
				<label for="txtNombre">Nombre de Usuario</label> 
				<input type="text" id="txtNombre" name="txtNombre" required maxlength="50">
					
			</div>
			<div class="form-group">
			
				<label for="txtcontraseña">Contraseña</label> <input type="password"
					id="txtcontrasenia" name="txtcontrasenia" required>
					
			</div>
			<div class="form-group">
				<input type="submit" name="btnInicioSesion" value="Iniciar sesión" class="btn" >
			</div>
			<div class="form-group">
				<a href="servletCliente?CrearCliente=1">Crear usuario</a>
			</div>
		</form>
	</div>
</body>
</html>
