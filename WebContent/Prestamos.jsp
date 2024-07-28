<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Prestamo"%>
<%@ page import="entidad.Cuenta" %>   
<%@ page import="entidad.Cliente" %> 

<%@page import="java.util.ArrayList"%>
<%@ page import="entidad.Cliente" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prestamos</title>
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
      .form-group {
        margin-bottom: 25px; /* Aumenta el espacio entre las líneas */
    }
    .form-control {
        width: 100%;
        padding: 10px;
        margin-top: 5px;
        box-sizing: border-box;
    }
</style>
</head>
<body>
    <div class="contenedor">
        <h1>Prestamos</h1>
        <div class="menu">
            <a href="servletCliente?AccionInicio=1">Inicio</a>
            <a href="Transferencias.jsp">Transferencias</a>
        </div>
        
        <!-- LISTAR PRESTAMOS, en caso de ser admin Sólo Activos / Saldados -->
        
        <!-- LISTAR PRESTAMOS, en caso de ser Cliente, Activos, inactivos y saldados (TODOS LOS PRESTAMOS QUE LE CORRESPONDAN) -->
        
        <div class="usuario">
            <% 
                Cliente cliente = (Cliente) session.getAttribute("cliente");
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
        
        <h2>Pagar Cuota Prestamos</h2>
        <div class="menu">
            <% 
            ArrayList<Cuenta> cuentasCliente = (ArrayList<Cuenta>) request.getAttribute("ListCuentasActivas");
            ArrayList<Prestamo> prestamosCliente = (ArrayList<Prestamo>) request.getAttribute("prestamosActivos");
            %>
            
            
             <!-- Mostrar mensaje de error si existe -->
            <c:if test="${not empty requestScope.mensajeError}">
                <p style="color: red;">${requestScope.mensajeError}</p>
            </c:if>
            
            
            
            
            
            <!-- Formulario de solicitud de prestamo -->
            <form action="servletCliente" method="post">
                <div class="form-group">
                    <label for="txtPrestamosActivo">Seleccione el prestamo a pagar</label>
                    <select id="txtPrestamosActivo" name="txtPrestamosActivo" class="form-control">
                        <% for (Prestamo prestamo : prestamosCliente) { %>
                            <option value="<%= prestamo.getId() %>">                    
                                $<%= prestamo.getImporteConIntereses() %> - CUOTAS RESTANTES: <%= prestamo.getCuotasPagadas() %> - VALOR DE CUOTA: $ <%= prestamo.getMontoMensual() %>
                            </option>
                        <% } %>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="cuentaADebitar">Seleccionar cuenta a debitar:</label>
                    <select id="cuentaADebitar" name="txtcuentaADebitar" class="form-control">
                        <% for (Cuenta cuenta : cuentasCliente) { %>
                            <option value="<%= cuenta.getCbu() %>">
                                <%= cuenta.getTipoCuenta().getDescripcion() %> - CBU: <%= cuenta.getCbu() %> - Numero de Cuenta: <%= cuenta.getNumeroDeCuenta() %> - SALDO: $ <%= cuenta.getSaldo() %>
                            </option>
                        <% } %>
                    </select>
                </div>
                                                           
                <div class="form-group">
                    <!-- Botón de submit para transferir -->
                    <input type="submit" name="btnPagarCuota" value="Pagar Cuota" class="btn btn-primary">   
                </div>
            </form>
        </div>
    </div>
</body>
</html>
