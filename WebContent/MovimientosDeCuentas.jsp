<%@ page import = "entidad.Cliente" %>
<%@ page import = "entidad.Movimiento" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movimientos Caja de ahorro</title>
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
        width: 100%;
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
        <h1>Movimientos de cuentas</h1>
        <div class="menu">
            <a href="inicio.jsp">Inicio</a>
            <a href="Transferencias.jsp">Transferencias</a>
            <a href="Prestamos.jsp">Préstamos</a>
        </div>
        
        <div class="usuario">
            <% 
                Cliente cliente = (Cliente) session.getAttribute("cliente");
                if (cliente != null) { 
            %>
                <a href="servletCliente?Param=1">
                    <%= cliente.getNombre() + " " + cliente.getApellido() %>
                </a>
            <% } else { %>
                <a href="servletCliente?Param=1">Nombre de Usuario</a>
            <% } %>
            <form action="servletCliente" method="post">
				<input type="submit" class="btn" value="Cerrar sesión" name="btnLogout">
			</form>
        </div>
        
        <h2>Movimientos</h2>
        <table>
            <thead>
                <tr>
                    <th>Fecha</th>
                    <th>Detalles</th>
                    <th>Monto</th>
                </tr>
            </thead>
            <tbody>
                   <% 
                ArrayList<Movimiento> movimientosCliente = (ArrayList<Movimiento>) request.getAttribute("movimientosCliente");
                if (movimientosCliente != null && !movimientosCliente.isEmpty()) {
                    for (Movimiento movimiento : movimientosCliente) {
            %>
                        <tr>
                            <td><%= new java.text.SimpleDateFormat("dd-MM-yyyy").format(movimiento.getFecha()) %></td>
                            <td><%= movimiento.getDetalle() %></td>
                             <td>
						        $ 
						        <% 
						            if (movimiento.getCuentaOrigen() == movimiento.getCuenta().getNumeroDeCuenta()) { 
						                out.print("-");
						            }
						        %> 
						        <%= movimiento.getImporte() %>
						    </td>
                        </tr>
            <% 
                    }
                } else {
            %>
                    <tr>
                        <td colspan="3">No hay movimientos para mostrar.</td>
                    </tr>
            <% 
                }
            %>
            </tbody>
        </table>
    </div>
</body>
</html>
