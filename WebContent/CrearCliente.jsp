<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidad.Pais" %>
<%@ page import="entidad.Provincia" %>
<%@ page import="entidad.Localidad" %>
<%@ page import="entidad.Generos" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Crear nuevo Cliente</title>
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
            max-width: 600px;
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

        input[type="text"], input[type="date"], input[type="email"], input[type="password"], select {
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
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Crear Usuario</h2>
	      <form action="servletCliente" method="post">
	    <label for="txtDNI">DNI</label>
	    <input type="text" id="txtDNI" name="txtDNI" required maxlength="8">
	
	    <label for="txtCUIL">CUIL</label>
	    <input type="text" id="txtCUIL" name="txtCUIL" required maxlength="11">
	
	    <label for="txtNombre">Nombre</label>
	    <input type="text" id="txtNombre" name="txtNombre" required>
	
	    <label for="txtApellido">Apellido</label>
	    <input type="text" id="txtApellido" name="txtApellido" required>
	
	    <label for="txtSexo">Sexo</label>
	    <select id="txtSexo" name="txtSexo" required>
	        <option value="">Seleccione un género</option>
	        <% ArrayList<Generos> listaGeneros = (ArrayList<Generos>) request.getAttribute("listaG");
	           if (listaGeneros != null) {
	               for (Generos genero : listaGeneros) { %>
	                   <option value="<%=genero.getId()%>"><%=genero.getDescripcion()%></option>
	        <%     }
	           } else { %>
	               <option value="">No hay géneros disponibles</option>
	        <% } %>
	    </select>
	
	    <label for="txtFecNac">Fecha de Nacimiento</label>
	    <input type="date" id="txtFecNac" name="txtFecNac" required>
	
	    <label for="txtNacionalidad">Nacionalidad</label>
	    <input type="text" id="txtNacionalidad" name="txtNacionalidad" required>
	    
	 		
	    <label for="txtPais">País</label>
            <select id="txtPais" name="txtPais" required>
                <option value="">Seleccione un país</option>
                <% 
                   ArrayList<Pais> paises = (ArrayList<Pais>) request.getAttribute("listaPaises");
                   if (paises != null) {
                       for (Pais pais : paises) { %>
                           <option value="<%=pais.getId()%>"><%=pais.getNombre()%></option>
                <%     }
                   } else { %>
                       <option value="">No hay países disponibles</option>
                <% } %>
            </select>
            
        
            <label for="txtProvincia">Provincia</label>
            <select id="txtProvincia" name="txtProvincia" required>
                <option value="">Seleccione una provincia</option>
                <% ArrayList<Provincia> provincias = (ArrayList<Provincia>) request.getAttribute("listaP");
                   if (!provincias.isEmpty()) {
                       for (Provincia provincia : provincias) { %>
                           <option value="<%=provincia.getId()%>"><%=provincia.getNombre()%></option>
                <%     }
                   } else { %>
                       <option value="">No hay provincias disponibles</option>
                <% } %>
            </select>
	
	    <label for="txtLocalidad">Localidad</label>
	    <select id="txtLocalidad" name="txtLocalidad" required>
	        <option value="">Seleccione una localidad</option>
	        <% ArrayList<Localidad> localidades = (ArrayList<Localidad>) request.getAttribute("listaL");
	           if (localidades != null) {
	               for (Localidad localidad : localidades) { %>
	                   <option value="<%=localidad.getId()%>"><%=localidad.getNombre()%></option>
	        <%     }
	           } else { %>
	               <option value="">No hay localidades disponibles</option>
	        <% } %>
	    </select>
	
	    <label for="txtDireccion">Dirección</label>
	    <input type="text" id="txtDireccion" name="txtDireccion" required>
	
	    <label for="txtEmail">Email</label>
	    <input type="email" id="txtEmail" name="txtEmail" required>
	
	    <label for="txtTelefono">Teléfono</label>
	    <input type="text" id="txtTelefono" name="txtTelefono" required>
	
	    <label for="txtUsuario">Nombre de Usuario</label>
	    <input type="text" id="txtUsuario" name="txtUsuario" required>
	
	    <label for="txtContraseña">Contraseña</label>
	    <input type="password" id="txtContrasenia" name="txtContrasenia" required>
	
	    <label for="txtRepeContraseña">Repetir la contraseña</label>
	    <input type="password" id="txtRepeContrasenia" name="txtRepeContrasenia" required>
	
	    <input type="submit" value="Aceptar" name="btnCrearCliente">
	</form>

    </div>
</body>
</html>
