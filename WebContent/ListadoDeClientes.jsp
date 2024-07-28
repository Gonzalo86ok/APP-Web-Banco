<%@page import="entidad.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de clientes</title>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
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
	max-width: 1500px;
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
}

.btn:hover {
	background-color: #0056b3;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
	overflow-x: auto;
	display: block;
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

@media ( max-width : 600px) {
	.menu {
		flex-direction: column;
		align-items: center;
	}
	.usuario {
		flex-direction: column;
		align-items: center;
	}
	.btn {
		width: 100%;
		margin-top: 10px;
	}
	table {
		display: block;
		width: 100%;
		overflow-x: auto;
	}
	th, td {
		padding: 5px;
	}
}
</style>
</head>
<body>
	<div class="contenedor">
		<h1>Lista de clientes</h1>
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
		    <label for="buscarCliente">Buscar Cliente:</label>
		    <input type="text" id="buscarCliente" name="buscarCliente" required> 
		    <input type="submit" value="Buscar" name="btnBuscar">
		</form>


		<form action="servletCliente" method="post">
			<label for="filtroMovimiento">Filtrar por Rango de Movimiento:</label> 
			<select name="filtroMovimiento">
				<option value="todos">Todos</option>
				<option value="menor">Menor que</option>
				<option value="menorIgual">Menor igual que</option>
				<option value="mayor">Mayor que</option>
				<option value="mayorIgual">Mayor igual que</option>
			</select> 
			<input type="text" id="movimiento" name="movimiento" required> 
			<input type="submit" value="Aplicar Filtro" name="btnAplicarFiltro">
		</form>

		<%
			if (request.getAttribute("listaC") != null) {
		%>
		<h2>Clientes Activos</h2>
		<%
			} else {
		%>
		<h2>Clientes Inactivos</h2>
		<%
			}
		%>

		<table id="table_id" class="display">
			<thead>
				<tr>
					<th>DNI</th>
					<th>CUIL</th>
					<th>Nombre</th>
					<th>Apellido</th>
					<th>Sexo</th>
					<th>Nacionalidad</th>
					<th>Fech.Nac.</th>
					<th>Direccion</th>
					<th>Localidad</th>
					<th>Email</th>
					<th>Telefono</th>
					<th>Modificar</th>
					<th>Alta/Baja</th>
				</tr>
			</thead>
			<tbody>
				<%
					ArrayList<Cliente> lc = null;
					if (request.getAttribute("listaC") != null) {
						lc = (ArrayList<Cliente>) request.getAttribute("listaC");
					} else {
						lc = (ArrayList<Cliente>) request.getAttribute("listaCI");
					}
					if (lc != null && !lc.isEmpty()) {
						for (Cliente c : lc) {
				%>
				<tr>
					<td><%=c.getDni()%></td>
					<td><%=c.getCuil()%></td>
					<td><%=c.getNombre()%></td>
					<td><%=c.getApellido()%></td>
					<td><%=c.getSexo().getDescripcion()%></td>
					<td><%=c.getNacionalidad()%></td>
					<td><%=c.getFechaNacimiento()%></td>
					<td><%=c.getDireccion()%></td>
					<td><%=c.getLocalidad().getNombre()%></td>
					<td><%=c.getEmail()%></td>
					<td><%=c.getTelefono()%></td>
					<td>
						<form action="servletCliente" method="post">
							<input type="hidden" name="dniC" value="<%=c.getDni()%>">
							<%
								if (request.getAttribute("listaC") != null) {
							%>
							<input type="submit" name="btnModificar" value="Modificar">
							<%
								} else {
							%>
							<%
								}
							%>
						</form>
					</td>
					<td>
						 <form action="servletCliente" method="post">
                            <input type="hidden" name="dniC" value="<%=c.getDni()%>">
                            <% if (request.getAttribute("listaC") != null) { %>
                                <input type="submit" name="btnEliminar" value="Eliminar" onclick="return confirm('¿Está seguro que desea eliminar al cliente con DNI: <%= c.getDni()%>?');">
                            <% } else { %>
                                <input type="submit" name="btnActivar" value="Activar">
                            <% } %>
                        </form>
					</td>
				</tr>
				<%
						}
					}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>
