<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Cliente Creado</title>
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
	text-align: center;
}

h2 {
	color: #333;
}

a {
	display: inline-block;
	margin-top: 20px;
	padding: 10px 20px;
	background-color: #4CAF50;
	color: white;
	text-decoration: none;
	border-radius: 4px;
}

a:hover {
	background-color: #45a049;
}
</style>
</head>
<body>

	<div class="container">
		<h2>Cliente creado exitosamente</h2>
		<p>El cliente ha sido creado con éxito.</p>
		<a href="Default.jsp">Login</a>
	</div>

</body>
</html>