<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="entidad.Cliente"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Estadísticas</title>
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
input[type="date"] select {
            width: 100%;
            padding: 8px 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
.hidden {
    display: none;
}
</style>
<script type="text/javascript">
function onRadioChange() {
    var option3 = document.getElementById("option3");
    var dateInput = document.getElementById("dateInput");
    if (option3.checked) {
        dateInput.classList.remove("hidden");
    } else {
        dateInput.classList.add("hidden");
    }
}
</script>
</head>
<body>
	<div class="contenedor">
		<h1>Estadísticas</h1>

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
			<a href="servletCliente?Listar=1">Lista de Clientes Activos</a>
			<a href="servletCliente?ListarInactivo=2">Lista de Clientes Inactivos</a>
			<a href="servletCliente?CrearCliente=1">Crear cliente</a>
			<a href="servletCliente?AltaCuentas=1">Altas De Cuentas</a>
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

		<form action="servletCliente" method="post">
			<div class="opciones">
				<label for="option1">
					<input type="radio" id="option1" name="estadistica" value="option1" onchange="onRadioChange()" checked>
					Promedio de saldos entre todas las cuentas
				</label>
				<br>
				<label for="option2">
					<input type="radio" id="option2" name="estadistica" value="option2" onchange="onRadioChange()">
					Porcentaje de cuentas Activas / Inactivas
				</label>
				<br>
				<label for="option3">
					<input type="radio" id="option3" name="estadistica" value="option3" onchange="onRadioChange()">
					Importe monetario movilizado
				</label>
				<br>
				<label for="option4">
					<input type="radio" id="option4" name="estadistica" value="option4" onchange="onRadioChange()">
					Movimientos realizados ésta semana
				</label>
			</div>

			<div id="dateInput" class="hidden">
				<label for="calendar">Desde:</label>
				<input type="date" id="calendar" name="fechaDesde">
				<label for="calendar">Hasta:</label>
				<input type="date" id="calendar" name="fechaHasta">
			</div>

			<input type="submit" class="btn" value="Aceptar" name="btnEstadisticas">
		</form>

		<div>
			<%
				if(request.getAttribute("promedioSaldos") != null){
					String promedioSaldos = (String) request.getAttribute("promedioSaldos");
			%>
					<label>Promedio de saldos entre todas las cuentas: $<%= promedioSaldos %></label>
			<%
				}
				else if(request.getAttribute("porcentajeCuentasActivas") != null && request.getAttribute("porcentajeCuentasInactivas") != null){
					String porcentajeCuentasActivas = (String) request.getAttribute("porcentajeCuentasActivas");
					String porcentajeCuentasInactivas = (String) request.getAttribute("porcentajeCuentasInactivas");
			%>
					<label>Porcentaje de cuentas Activas: <%= porcentajeCuentasActivas %></label>
					<label>Porcentaje de cuentas Inactivas: <%= porcentajeCuentasInactivas %></label>
			<%
				}
				else if(request.getAttribute("importeMovido") != null){
					String importeMovido = (String) request.getAttribute("importeMovido");
			%>
					<label>Importe monetario movilizado: $<%= importeMovido %></label>
			<%
				}
				else if(request.getAttribute("importeMovidoSemana") != null){
					String importeMovidoSemana = (String) request.getAttribute("importeMovidoSemana");
			%>
					<label>Importe monetario movilizado ésta última semana: $<%= importeMovidoSemana %></label>
			<%
				}
			%>
		</div>
	</div>
</body>

</html>