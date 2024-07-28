package presentacion.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Exceptions.DesplegablesSinConexionException;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Generos;
import entidad.Localidad;
import entidad.Movimiento;
import entidad.Pais;
import entidad.Prestamo;
import entidad.Provincia;
import entidad.SolicitudDeAlta;
import entidad.TipoCuenta;
import entidad.TipoMovimiento;
import entidad.Usuario;
import negocio.IClienteNeg;
import negocio.ICuentaNeg;
import negocio.IGeneroNeg;
import negocio.ILocalidadNeg;
import negocio.IMovimientoNeg;
import negocio.IPaisNeg;
import negocio.IPrestamoNeg;
import negocio.IProvinciaNeg;
import negocio.ISolicitudDeAltaNeg;
import negocio.IUsuarioNeg;
import negocioimpl.ClienteNegImpl;
import negocioimpl.CuentaNegImpl;
import negocioimpl.GeneroNegImpl;
import negocioimpl.LocalidadNegImpl;
import negocioimpl.MovimientoNegImpl;
import negocioimpl.PaisNegImpl;
import negocioimpl.PrestamoNegImpl;
import negocioimpl.ProvinciaNegImpl;
import negocioimpl.SolicitudesDeCuentaNegImpl;
import negocioimpl.UsuarioNegImpl;

@WebServlet("/servletCliente")
public class servletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Objetos que se conectan al Negocio para llamar a los métodos
	IClienteNeg negCliente = new ClienteNegImpl();
	ICuentaNeg negCuenta = new CuentaNegImpl();
	IMovimientoNeg negMovimiento = new MovimientoNegImpl();
	IPrestamoNeg negPrestamo = new PrestamoNegImpl();
	ISolicitudDeAltaNeg negSolicitudes = new SolicitudesDeCuentaNegImpl();
	IUsuarioNeg negUsuario = new UsuarioNegImpl();
	IPaisNeg negPais = new PaisNegImpl();
	ILocalidadNeg negLocalidad = new LocalidadNegImpl();
	IGeneroNeg negGenero = new GeneroNegImpl();
	IProvinciaNeg negProvincia = new ProvinciaNegImpl();

	public servletCliente() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		if (request.getParameter("AccionInicio") != null)
		{
			eventoInicio(request, response);
		} 
		else if (request.getParameter("CrearClienteAdmin") != null) 
		{
			try 
			{
				eventoCargarDesplegables(request, response);
				request.getRequestDispatcher("AdminCrearCliente.jsp").forward(request, response);
			}
			catch (DesplegablesSinConexionException e)
{
				e.printStackTrace();
			}
		}
		else if (request.getParameter("CrearCliente") != null) 
		{
			try
			{
				eventoCargarDesplegables(request, response);
				request.getRequestDispatcher("CrearCliente.jsp").forward(request, response);
			}
			catch (DesplegablesSinConexionException e)
			{
				e.printStackTrace();
			}
		} 
		else if (request.getParameter("movimiento") != null) 
		{
			eventoCargarMovimientos(request, response);
		} 
		else if (request.getParameter("ListarCuentasClientes") != null) 
		{
			eventolistarCuentasClientes(request, response);
		}
		else if (request.getParameter("Listar") != null)
		{
			eventolistarClienteActivos(request, response);
		} 
		else if (request.getParameter("ListarInactivo") != null)
		{
			eventolistarClienteInactivos(request, response);
		} 
		else if (request.getParameter("Param") != null) 
		{
			eventoDetallesUsuario(request, response);
		} 
		else if (request.getParameter("Caja") != null) 
		{
			eventoSolicitarCajaDeAhorro(request, response);
		} 
		else if (request.getParameter("Cuenta") != null) 
		{
			eventoSolicitarCuentaCorriente(request, response);
		} 
		else if (request.getParameter("AltaCuentas") != null) 
		{
			eventoListarAltas(request, response);
		} 
		else if (request.getParameter("ListarSolicitudPrestamos") != null)
		{
			eventoListarSolicitudPrestamos(request, response);
		}
		else if (request.getParameter("SolicitarPrestamos") != null)
		{
			eventoSolicitarPrestamos(request, response);
		} 
		else if (request.getParameter("Estadisticas") != null) 
		{
			eventoEstadisticas(request, response);
		}
		else if (request.getParameter("PrestamosxCliente") != null) 
		{
			eventoCargarPrestamos(request, response);
		}				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// Conectarse / Login 
		if (request.getParameter("btnInicioSesion") != null) 
		{
			eventoLogin(request, response);
		} 
		//Desconectarse / Logout
		else if (request.getParameter("btnLogout") != null) 
		{
			Cliente clienteVacío = new Cliente();
			request.getSession().setAttribute("cliente", clienteVacío);
			request.getRequestDispatcher("Default.jsp").forward(request, response);
		}		
		// Creación, modificación y baja lógica de CLIENTE
		else if (request.getParameter("btnCrearCliente") != null) 
		{
			eventoCrearCliente(request, response);
		} 
		else if (request.getParameter("btnCrearClienteAdmin") != null) 
		{
			eventoCrearClienteAdmin(request, response);
		}
		else if (request.getParameter("btnModificar") != null) 
		{
			try 
			{
				eventoCargarDesplegables(request, response);
			}
			catch (DesplegablesSinConexionException e) 
			{
				e.printStackTrace();
			}
			eventoModificarCliente(request, response);
		}
		else if (request.getParameter("btnModificarCliente") != null) 
		{
			eventoModificoCliente(request, response);
		} 
		else if (request.getParameter("btnEliminar") != null) 
		{
			eventoEliminarCliente(request, response);
		}
		else if (request.getParameter("btnActivar") != null) 
		{
			eventoActivarCliente(request, response);
		} 		
		// Activar Cuenta - Buscar cuenta por CBU - Transferir montos
		else if (request.getParameter("btnActivarCuenta") != null) 
		{
			eventoActivarCuenta(request, response);
		}
		else if (request.getParameter("btnBuscarCBU") != null) 
		{
			eventoBuscarCBU(request, response);
		} 
		else if (request.getParameter("btnTransferir") != null) 
		{
			eventoTransferir(request, response);
		}  
		else if (request.getParameter("btnBuscar") != null) 
		{
			eventoBuscarCliente(request, response);
		} 		
		// Aprobación, denegación o Saldado de Prestamos
		else if (request.getParameter("btnAprobar") != null) 
		{
			eventoAprobarPrestamo(request, response, 0);
		} 
		else if (request.getParameter("btnRechazar") != null) 
		{
			eventoAprobarPrestamo(request, response, 1);
		}
		else if (request.getParameter("btnSolicitarPrestamo") != null)
		{
			eventoConfirmarPrestamo(request, response);
		}		
		// Estadísticas
		else if (request.getParameter("btnEstadisticas") != null) 
		{
			eventoEstadisticasVisualizar(request, response);
		} 
		else if (request.getParameter("btnPagarCuota") != null) 
		{
			eventoPagarCuota(request, response);
		} 
		else if (request.getParameter("btnEliminarCuenta") != null) 
		{
			eventoBajaLogicaCuenta(request, response);
		} 
		else if(request.getParameter("btnAplicarFiltro") != null)
		{
			eventoAplicarFiltro(request, response);
		}
		
	}	

	public void eventoAplicarFiltro(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
	        RequestDispatcher rd = request.getRequestDispatcher("ListarSolicitudPrestamos.jsp");
	        rd.forward(request, response);
	}
	

	//EVENTOS SOBRE PRESTAMOS Y TODAS SUS VARIANTES
	//ADMIN
	public void eventoListarSolicitudPrestamos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Prestamo> listaPrestamosInactivos = (ArrayList<Prestamo>) negPrestamo.getPrestamosInactivos();
		request.setAttribute("listaPrestamosInactivos", listaPrestamosInactivos);

		RequestDispatcher rd = request.getRequestDispatcher("ListarSolicitudPrestamos.jsp");
		rd.forward(request, response);
	}
	
	public void eventoAprobarPrestamo(HttpServletRequest request, HttpServletResponse response, int p)
			throws ServletException, IOException { // doPost

		String id = request.getParameter("prestamoSeleccionadoId");
		int idInt = Integer.parseInt(id);
		
		Prestamo prestamo = negPrestamo.getPrestamos(idInt,1);
		System.out.println( prestamo.toString());
		if(p == 0) {
			negPrestamo.activarPrestamo(prestamo.getCuenta().getNumeroDeCuenta(),idInt,prestamo.getCuenta().getCbu(),prestamo.getImportePedido());
		}
		else if (p == 1) {
			negPrestamo.rechazarPrestamo(idInt);
		}
		

		ArrayList<Prestamo> listaPrestamosInactivos = (ArrayList<Prestamo>) negPrestamo.getPrestamosInactivos();
		request.setAttribute("listaPrestamosInactivos", listaPrestamosInactivos);

		RequestDispatcher rd = request.getRequestDispatcher("ListarSolicitudPrestamos.jsp");
		rd.forward(request, response);
	}
	
	public void eventoConfirmarPrestamo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { //DoPost

		String montoSolicitado = request.getParameter("txtMontoSolicitado");
		String cantidadCuotas = request.getParameter("txtCantidadDeCuotas");
		String cuentaDestino = request.getParameter("txtCuentaDestino");

		// Validar que los parámetros no sean nulos ni estén vacíos
		if (montoSolicitado == null || cantidadCuotas == null || cuentaDestino == null || montoSolicitado.isEmpty()
				|| cantidadCuotas.isEmpty() || cuentaDestino.isEmpty()) {
			System.out.println("Error: Parámetros faltantes o vacíos.");
			return;
		}

		// Verificar que el cliente esté logueado
		HttpSession session = request.getSession();
		Cliente clienteLogueado = (Cliente) session.getAttribute("cliente");

		if (clienteLogueado == null) {
			System.out.println("Error: Cliente no logueado.");
			return;
		}

		try {
			double montoPedido = Double.parseDouble(montoSolicitado);
			int cantidadCuotasInt = Integer.parseInt(cantidadCuotas);
			int cuotaPagar;

			if (cantidadCuotasInt == 1) {
				cuotaPagar = 3;
			} else if (cantidadCuotasInt == 2) {
				cuotaPagar = 6;
			} else {
				cuotaPagar = 12;
			}

			double montoConInteres = montoPedido * 1.1;
			double cuotaConInteres = montoConInteres / cuotaPagar;

			// Crear objeto de préstamo y setear los valores
			Prestamo prestamo = new Prestamo();
			prestamo.setCliente(clienteLogueado);
			Cuenta cuenta = negCuenta.buscarPorCBU(Integer.parseInt(cuentaDestino));

			prestamo.setCuenta(cuenta);

			prestamo.setFecha(new Date(System.currentTimeMillis()));
			prestamo.setImporteConIntereses(montoConInteres);
			prestamo.setImportePedido(montoPedido);
			prestamo.setPlazoDePagoEnMeses(cuotaPagar);
			prestamo.setMontoMensual(cuotaConInteres);
			prestamo.setCuotasPagadas(cuotaPagar);
			prestamo.setEstado("I"); // I para inactivo, A para activo.
			prestamo.setPrestamoSaldado("N"); // N para No, S para Sí 

			negPrestamo.solicitarPrestamo(prestamo);

			this.eventoSolicitarPrestamos(request, response);

		} catch (NumberFormatException e) {
			System.out.println("Error: Formato de número inválido.");
			e.printStackTrace();
		}
	}
		
	public void eventoSolicitarPrestamos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // DoGet

		// capturar cliente y sus cuentas, luego devolverlas como parametro para que el
		// jsp los reciba
		HttpSession session = request.getSession();
		Cliente clienteLogueado = (Cliente) session.getAttribute("cliente");

		// cuentas activas por dni
		ArrayList<Cuenta> cuentasActivas = negCuenta.obtenerCuentasActivasPorCliente(clienteLogueado.getDni());

		request.setAttribute("ListCuentasActivas", cuentasActivas);

		RequestDispatcher rd = request.getRequestDispatcher("SolicitarPrestamo.jsp");
		rd.forward(request, response);
	}
	
	public void eventoCargarPrestamos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cliente clienteLogueado = (Cliente) session.getAttribute("cliente");

		// cuentas activas por dni
		ArrayList<Cuenta> cuentasActivas = negCuenta.obtenerCuentasActivasPorCliente(clienteLogueado.getDni());
		request.setAttribute("ListCuentasActivas", cuentasActivas);
		
		// Prestamos activos por cliente
		ArrayList<Prestamo> prestamosActivos = negPrestamo.getPrestamosXCliente(clienteLogueado);
		request.setAttribute("prestamosActivos", prestamosActivos);
		
		RequestDispatcher rd = request.getRequestDispatcher("Prestamos.jsp");
		rd.forward(request, response);
		
	}
	
	public void eventoPagarCuota(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String idPrestamo = request.getParameter("txtPrestamosActivo");
		String cbu = request.getParameter("txtcuentaADebitar");

		int idPrestamoInt = Integer.parseInt(idPrestamo);
		int cbuInt = Integer.parseInt(cbu);
		
		Cuenta cuenta = negCuenta.buscarPorCBU(cbuInt);		
		int numCuenta = cuenta.getNumeroDeCuenta();
				
		Prestamo prestamo = negPrestamo.getPrestamos(idPrestamoInt,0);							
		
		double importe = prestamo.getMontoMensual();
		
		 // Verificar si el saldo es suficiente para realizar el pago
	    if (importe <= cuenta.getSaldo()) 
	    {
	        // Realizar el pago del préstamo
	        negPrestamo.pagarPrestamo(idPrestamoInt, numCuenta, cbuInt, importe);
	    } 
	    else 
	    {
	        // Si el saldo no es suficiente, configurar un mensaje de error
	        request.setAttribute("mensajeError", "Saldo insuficiente para pagar el préstamo.");
	    }
		eventoCargarPrestamos(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("Prestamos.jsp");
		rd.forward(request, response);
	}
	
	//EVENTOS SOBRE ESTADISTICAS-REPORTES
	public void eventoEstadisticas(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // doGet
		// Redirecciona a la página Estadísticas
		RequestDispatcher rd = request.getRequestDispatcher("Estadisticas.jsp");
		rd.forward(request, response);
	}
		
	public void eventoEstadisticasVisualizar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // doPost

		double promedioSaldos = 0;
		float porcentajeCuentasActivas = 0;
		float porcentajeCuentasInactivas = 0;
		double importeMovido = 0;
		double importeMovidoSemana = 0;

		String opcionSeleccionada = request.getParameter("estadistica");

		// Procesa las fechas en caso de ser necesario
		String fechaDesdeStr = null;
		String fechaHastaStr = null;
		Date fechaDesdeDate = new Date(0);
		Date fechaHastaDate = new Date(0);

		if ("option3".equals(opcionSeleccionada)) {
			fechaDesdeStr = request.getParameter("fechaDesde");
			fechaHastaStr = request.getParameter("fechaHasta");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			try {
				java.util.Date fechaDesdeParsed = sdf.parse(fechaDesdeStr);
				fechaDesdeDate = new java.sql.Date(fechaDesdeParsed.getTime());

				java.util.Date fechaHastaParsed = sdf.parse(fechaHastaStr);
				fechaHastaDate = new java.sql.Date(fechaHastaParsed.getTime());

			} catch (java.text.ParseException e) {
				e.printStackTrace();
				request.setAttribute("error", "Formato de fecha inválido");
				RequestDispatcher rd = request.getRequestDispatcher("/Estadisticas.jsp");
				rd.forward(request, response);
				return;
			}

		}

		// Dependiendo de la seleccion del usuario, se ejecuta una u otra función
		switch (opcionSeleccionada) {
		case "option1":
			promedioSaldos = negCuenta.GetPromedioSaldos();

			String strpromedioSaldos = Double.toString(promedioSaldos);

			request.setAttribute("promedioSaldos", strpromedioSaldos);
			break;

		case "option2":
			porcentajeCuentasActivas = negCuenta.GetPorcentajeCuentasActivas();
			porcentajeCuentasInactivas = negCuenta.GetPorcentajeCuentasInactivas();

			// HAY QUE PARSEAR A STRING PARA MANDARLO COMO ATRIBUTO!
			String strPorcentajeCuentasActivas = Float.toString(porcentajeCuentasActivas);
			String strPorcentajeCuentasInactivas = Float.toString(porcentajeCuentasInactivas);

			request.setAttribute("porcentajeCuentasActivas", strPorcentajeCuentasActivas);
			request.setAttribute("porcentajeCuentasInactivas", strPorcentajeCuentasInactivas);
			break;

		case "option3":
			if (fechaDesdeDate != null && fechaHastaDate != null) {
				importeMovido = negMovimiento.GetCantidadMovida(fechaDesdeDate, fechaHastaDate);

				String strImporteMovido = Double.toString(importeMovido);

				request.setAttribute("importeMovido", strImporteMovido);
			}
			break;

		case "option4":
			importeMovidoSemana = negMovimiento.GetCantidadSemana();

			String strImporteMovidoSemana = Double.toString(importeMovidoSemana);

			request.setAttribute("importeMovidoSemana", strImporteMovidoSemana);
			break;

		default:
			// Nunca debería entrar en el default porque siempre hay una opción
			// seleccionada.
			break;
		}

		request.getRequestDispatcher("/Estadisticas.jsp").forward(request, response);
	}
		
	//EVENTOS SOBRE  CUENTAS DE AHORRO Y CORRIENTES
	//ADMIN
	public void eventoListarAltas(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtener todas las solicitudes activas
		ArrayList<SolicitudDeAlta> solicitudesActivas = (ArrayList<SolicitudDeAlta>) negSolicitudes
				.obtenerTodasLasSolicitudesActivas();

		// Guardar las solicitudes en el request para pasarlas a la página JSP
		request.setAttribute("solicitudes", solicitudesActivas);

		// Redirigir a la página de alta de cuentas
		RequestDispatcher dispatcher = request.getRequestDispatcher("AltaCuentas.jsp");
		dispatcher.forward(request, response);
	}
	
	public void eventoActivarCuenta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int solicitudId = Integer.parseInt(request.getParameter("solicitudId"));

		SolicitudDeAlta solicitud = negSolicitudes.obtenerSolicitudPorId(solicitudId);
		if (solicitud != null) {

			Cuenta nuevaCuenta = new Cuenta();

			nuevaCuenta.setDni(solicitud.getCliente().getDni());
			nuevaCuenta.setFechaDeCreacion(new Date(System.currentTimeMillis()));

			TipoCuenta tipoCuenta = new TipoCuenta();
			tipoCuenta.setId(solicitud.getTipoCuenta().getId());
			tipoCuenta.setDescripcion(solicitud.getTipoCuenta().getDescripcion());
			nuevaCuenta.setTipoCuenta(tipoCuenta);
			int cbu = 0;
			cbu = negCuenta.obtenerUltimoCBU();

			nuevaCuenta.setCbu(cbu + 1);
			nuevaCuenta.setSaldo(10000.00);

			negCuenta.agregarCuenta(nuevaCuenta);
			negSolicitudes.eliminarSolicitud(solicitudId);

			nuevaCuenta = negCuenta.buscarPorCBU(nuevaCuenta.getCbu());

			Movimiento movimiento = new Movimiento();

			movimiento.setCuenta(nuevaCuenta);
			movimiento.setFecha(new Date(System.currentTimeMillis()));
			movimiento.setDetalle("Alta de cuenta");
			movimiento.setImporte(10000);

			TipoMovimiento tipoMovimiento = new TipoMovimiento();
			tipoMovimiento.setId(1);
			tipoMovimiento.setDescripcion("Alta de cuenta");

			movimiento.setTipoMovimiento(tipoMovimiento);
			movimiento.setCuentaDestino(nuevaCuenta.getNumeroDeCuenta());

			negMovimiento.agregarMovimiento(movimiento);

			ArrayList<SolicitudDeAlta> solicitudes = (ArrayList<SolicitudDeAlta>) negSolicitudes
					.obtenerTodasLasSolicitudesActivas();
			request.setAttribute("solicitudes", solicitudes);
			RequestDispatcher rd = request.getRequestDispatcher("AltaCuentas.jsp");
			rd.forward(request, response);
		}
	}
	
	public void eventolistarCuentasClientes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Cuenta> cuentas = negCuenta.obtenerCuentas();
		request.setAttribute("cuentas", cuentas);

		RequestDispatcher dispatcher = request.getRequestDispatcher("ListarCuentas.jsp");
		dispatcher.forward(request, response);
	}

	public void eventoBajaLogicaCuenta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String SnumCuenta = request.getParameter("numCuenta"); 

		int numCuenta = Integer.parseInt(SnumCuenta);
		negCuenta.eliminarCuenta(numCuenta);

		ArrayList<Cuenta> cuentas = negCuenta.obtenerCuentas();
		request.setAttribute("cuentas", cuentas);
		
		// Redirigir a la página de alta de cuentas
		RequestDispatcher dispatcher = request.getRequestDispatcher("ListarCuentas.jsp");
		dispatcher.forward(request, response);		
	}

	//CLIENTE
	public void eventoSolicitarCuentaCorriente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String dni = request.getParameter("clienteDNI");
		if (dni != null && !dni.isEmpty()) {
			ArrayList<SolicitudDeAlta> solicitudesActivas = (ArrayList<SolicitudDeAlta>) negSolicitudes
					.obtenerTodasLasSolicitudesActivas();
			// Verificar si ya tiene una solicitud activa para caja de ahorro
			boolean tieneSolicitudActiva = false;

			for (SolicitudDeAlta solicitud : solicitudesActivas) {
				if (solicitud.getCliente().getDni().equals(dni)) {
					if (solicitud.getTipoCuenta().getId() == 2 && solicitud.getEstado().equals("A")) {
						tieneSolicitudActiva = true;
						break;
					}
				}
			}

			if (!tieneSolicitudActiva) {
				// Crear una instancia de Cliente con el DNI recibido
				Cliente cliente = negCliente.obtenerCliente(dni);

				// Crear una instancia de TipoCuenta para Caja de Ahorro
				TipoCuenta tipoCuenta = new TipoCuenta();
				tipoCuenta.setId(2);
				tipoCuenta.setDescripcion("Cuenta corriente");

				// Crear una nueva solicitud
				SolicitudDeAlta solicitud = new SolicitudDeAlta();
				solicitud.setCliente(cliente);
				solicitud.setTipoCuenta(tipoCuenta);
				solicitud.setEstado("A");

				// Agregar la solicitud a la base de datos
				negSolicitudes.agregarSolicitud(solicitud);
			}
			try {
				response.sendRedirect("inicio.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void eventoSolicitarCajaDeAhorro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String dni = request.getParameter("clienteDNI");
		if (dni != null && !dni.isEmpty()) {
			ArrayList<SolicitudDeAlta> solicitudesActivas = (ArrayList<SolicitudDeAlta>) negSolicitudes
					.obtenerTodasLasSolicitudesActivas();
			// Verificar si ya tiene una solicitud activa para caja de ahorro
			boolean tieneSolicitudActiva = false;

			for (SolicitudDeAlta solicitud : solicitudesActivas) {
				if (solicitud.getCliente().getDni().equals(dni)) {
					if (solicitud.getTipoCuenta().getId() == 1 && solicitud.getEstado().equals("A")) {
						tieneSolicitudActiva = true;
						break;
					}
				}
			}

			if (!tieneSolicitudActiva) {
				// Crear una instancia de Cliente con el DNI recibido
				Cliente cliente = negCliente.obtenerCliente(dni);

				// Crear una instancia de TipoCuenta para Caja de Ahorro
				TipoCuenta tipoCuenta = new TipoCuenta();
				tipoCuenta.setId(1);
				tipoCuenta.setDescripcion("Caja de Ahorro");

				// Crear una nueva solicitud
				SolicitudDeAlta solicitud = new SolicitudDeAlta();
				solicitud.setCliente(cliente);
				solicitud.setTipoCuenta(tipoCuenta);
				solicitud.setEstado("A");

				// Agregar la solicitud a la base de datos
				negSolicitudes.agregarSolicitud(solicitud);
			}
			try {
				response.sendRedirect("inicio.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//EVENTO SOBRE TRANSFERENCIAS
	public void eventoTransferir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// HttpSession session = request.getSession();
		// Cliente clienteLogueado = (Cliente) session.getAttribute("cliente");

		String detalle = request.getParameter("concepto");

		// Obtener cuenta origen seleccionada por el usuario
		String cbuOrigen = request.getParameter("cuentaOrigen");
		Cuenta cuentaOrigen = negCuenta.buscarPorCBU(Integer.parseInt(cbuOrigen));

		// Obtener cuenta destino
		String cbuDestino = request.getParameter("cuentaDestino");
		Cuenta cuentaDestinoObj = negCuenta.buscarPorCBU(Integer.parseInt(cbuDestino));

		// Obtener monto de la transferencia
		String montoStr = request.getParameter("txtMonto");
		double monto = 0.0;

		if (montoStr != null && !montoStr.isEmpty()) {
			try {
				monto = Double.parseDouble(montoStr);
			} catch (NumberFormatException e) {
				request.setAttribute("mensaje", "El monto ingresado no es válido.");
				request.getRequestDispatcher("Transferencias.jsp").forward(request, response);
				return;
			}
		} else {
			request.setAttribute("mensaje", "Debe ingresar un monto válido.");
			request.getRequestDispatcher("Transferencias.jsp").forward(request, response);
			return;
		}

		// Verificar saldo suficiente en la cuenta origen
		if (cuentaOrigen.getSaldo() < monto) {
			request.setAttribute("mensaje", "Saldo insuficiente en la cuenta origen.");
			request.getRequestDispatcher("Transferencias.jsp").forward(request, response);
			return;
		}

		// Realizar la transferencia
		cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
		cuentaDestinoObj.setSaldo(cuentaDestinoObj.getSaldo() + monto);

		int filasActualizadas = negCuenta.modificarSaldoCuenta(cuentaOrigen);
		filasActualizadas += negCuenta.modificarSaldoCuenta(cuentaDestinoObj);

		if (filasActualizadas == 2) {
			Movimiento movimientoOrigen = new Movimiento();
			Movimiento movimientoDestino = new Movimiento();

			movimientoOrigen.setCuenta(cuentaOrigen);
			movimientoOrigen.setFecha(new Date(System.currentTimeMillis()));
			movimientoOrigen.setDetalle(detalle);
			movimientoOrigen.setImporte(monto);

			TipoMovimiento tm = new TipoMovimiento();
			tm.setId(4);
			tm.setDescripcion("Transferencia");

			movimientoOrigen.setTipoMovimiento(tm);
			movimientoOrigen.setCuentaOrigen(cuentaOrigen.getNumeroDeCuenta());
			movimientoOrigen.setCuentaDestino(cuentaDestinoObj.getNumeroDeCuenta());

			movimientoDestino.setCuenta(cuentaDestinoObj);
			movimientoDestino.setFecha(new Date(System.currentTimeMillis()));
			movimientoDestino.setDetalle(detalle);
			movimientoDestino.setImporte(monto);
			movimientoDestino.setTipoMovimiento(tm);
			movimientoDestino.setCuentaOrigen(cuentaOrigen.getNumeroDeCuenta());
			movimientoDestino.setCuentaDestino(cuentaDestinoObj.getNumeroDeCuenta());

			negMovimiento.agregarMovimiento(movimientoOrigen);
			negMovimiento.agregarMovimiento(movimientoDestino);

			request.setAttribute("mensaje", "Transferencia realizada con éxito.");
		} else {
			request.setAttribute("mensaje", "Error al realizar la transferencia. Inténtelo nuevamente.");
		}

		request.getRequestDispatcher("Transferencias.jsp").forward(request, response);
	}
	
	public void eventoBuscarCBU(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Cliente clienteLogeado = (Cliente) session.getAttribute("cliente");

		if (clienteLogeado != null) {
			try {
				int cbu = Integer.parseInt(request.getParameter("txtCBU"));

				Cuenta cuentaDestino = negCuenta.buscarPorCBU(cbu);
				Cliente clienteDestino = negCliente.obtenerCliente(cuentaDestino.getDni());

				// Obtener todas las cuentas activas
				ArrayList<Cuenta> cuentasActivas = negCuenta.obtenerCuentasActivas();
				ArrayList<Cuenta> cuentasCliente = new ArrayList<>();

				for (Cuenta c : cuentasActivas) {
					if (Integer.valueOf(clienteLogeado.getDni()).equals(Integer.valueOf(c.getDni()))) {
						cuentasCliente.add(c);
					}
				}

				if (!cuentasCliente.isEmpty()) {
					request.setAttribute("cliente", clienteLogeado);
					request.setAttribute("cuentaDestino", cuentaDestino);
					request.setAttribute("cuentasCliente", cuentasCliente);
					request.setAttribute("clienteDestino", clienteDestino);
					request.getRequestDispatcher("Transferencias.jsp").forward(request, response);
					return;
				} else {
					request.setAttribute("mensaje", "El cliente no tiene cuentas activas.");
				}
			} catch (NumberFormatException e) {
				request.setAttribute("mensaje", "CBU inválido.");
			} catch (Exception e) {
				request.setAttribute("mensaje", "Error al buscar la cuenta: " + e.getMessage());
			}
		} else {
			request.setAttribute("mensaje", "No se encontró el cliente logeado.");
		}
		request.getRequestDispatcher("Transferencias.jsp").forward(request, response);
	}
	
	public void eventoCargarMovimientos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String numCuentaStr = request.getParameter("NumCuenta");

		if (numCuentaStr == null || numCuentaStr.isEmpty()) {
			request.setAttribute("mensaje", "No se proporcionó un número de cuenta válido.");
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}

		int numCuenta = Integer.parseInt(numCuentaStr);

		ArrayList<Movimiento> movimientosCliente = new ArrayList<>();
		ArrayList<Movimiento> movimientos = (ArrayList<Movimiento>) negMovimiento.getMovimientos();

		for (Movimiento m : movimientos) {
			if (m.getCuenta().getNumeroDeCuenta() == numCuenta) {
				movimientosCliente.add(m);
			}
		}
		request.setAttribute("movimientosCliente", movimientosCliente);
		request.getRequestDispatcher("MovimientosDeCuentas.jsp").forward(request, response);
	}
		
	//EVENTO DETALLES USUARIOS
	public void eventoDetallesUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String dni = request.getParameter("clienteDni");
		if (dni != null && !dni.isEmpty()) {
			Cliente c = negCliente.obtenerCliente(dni);
			if (c != null) {
				ArrayList<Usuario> listUsuarios = negUsuario.obtenerUsuarioActivos();
				Usuario usuarioCliente = null;

				for (Usuario usuario : listUsuarios) {
					// Comparamos el DNI del cliente con el DNI del usuario
					if (usuario.getCliente().getDni().equals(c.getDni())) {
						// Encontramos el usuario correspondiente al cliente
						usuarioCliente = usuario;
						break;
					}
				}
				if (usuarioCliente != null) {
					request.getSession().setAttribute("usuarioCliente", usuarioCliente);
					if ("Admin".equals(usuarioCliente.getTipoUsuario())) {
						RequestDispatcher rd = request.getRequestDispatcher("/DetalleUsuarioAdmin.jsp");
						rd.forward(request, response);
					} else if ("Cliente".equals(usuarioCliente.getTipoUsuario())) {
						RequestDispatcher rd = request.getRequestDispatcher("/usuario.jsp");
						rd.forward(request, response);
					}
				}
			}
		} else {
			// Redirigimos al inicio si el DNI es nulo o vacío
			response.sendRedirect("inicio.jsp");
		}
	}
		
	//EVENTO SOBRE LISTADOS
	public void eventolistarClienteActivos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Cliente> listC = negCliente.obtenerClientesActivos();

		request.setAttribute("listaC", listC);

		RequestDispatcher rd = request.getRequestDispatcher("ListadoDeClientes.jsp");
		rd.forward(request, response);
	}
	
	public void eventolistarClienteInactivos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Cliente> listC = negCliente.obtenerClientesInactivos();

		request.setAttribute("listaCI", listC);

		RequestDispatcher rd = request.getRequestDispatcher("ListadoDeClientes.jsp");
		rd.forward(request, response);
	}
	
	public void eventoBuscarCliente(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
	    String filtro = request.getParameter("buscarCliente");
System.out.println(filtro);
	    ArrayList<Cliente> listaClientes = negCliente.buscarClientes(filtro);
System.out.println(listaClientes);	    
	    request.setAttribute("listaC", listaClientes);
	    request.getRequestDispatcher("ListadoDeClientes.jsp").forward(request, response);
	}
		
	//EVENTOS SOBRE LOS CLIENTES ALTA-BAJA-MODIFICACION
	//CLIENTE
	public void eventoCrearCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Localidad ob_localidad;
		Generos ob_genero;

		int id_sexo;
		int id_localidad;

		String dni = request.getParameter("txtDNI");
		String cuil = request.getParameter("txtCUIL");
		String nombre = request.getParameter("txtNombre");
		String apellido = request.getParameter("txtApellido");
		String sexo = request.getParameter("txtSexo");
		String fechaNacimientoStr = request.getParameter("txtFecNac");
		String direccion = request.getParameter("txtDireccion");
		String localidad = request.getParameter("txtLocalidad");
		String email = request.getParameter("txtEmail");
		String telefono = request.getParameter("txtTelefono");
		String nacionalidad = request.getParameter("txtNacionalidad");

		String usuario = request.getParameter("txtUsuario");
		String contraseña = request.getParameter("txtContrasenia");
		String repetirContraseña = request.getParameter("txtRepeContrasenia");

		Date fechaNacimiento = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			// SE PARSEA LAS VARIABLES OBTENIDAS POR EL "request.getParameter"
			java.util.Date parsedDate = sdf.parse(fechaNacimientoStr);
			fechaNacimiento = new java.sql.Date(parsedDate.getTime());
			id_sexo = Integer.parseInt(sexo);
			id_localidad = Integer.parseInt(localidad);

			// CARGO LOS OBJETOS
			// LA LOCALIDAD TIENE UN OBJETO PROVINCIA QUE A SU VEZ TIENE UN OBJETO PAIS
			ob_localidad = negLocalidad.GetLocalidad(id_localidad);
			ob_genero = negGenero.GetGenero(id_sexo);

		} catch (java.text.ParseException e) {
			e.printStackTrace();
			request.setAttribute("error", "Formato de fecha de nacimiento inválido");
			RequestDispatcher rd = request.getRequestDispatcher("/CrearCliente.jsp");
			rd.forward(request, response);
			return;
		}
		// verificacion de las contraseñas
		if (!contraseña.equals(repetirContraseña)) {
			System.out.println("Las contraseñas no coinciden");
			request.setAttribute("error", "Las contraseñas no coinciden");
			RequestDispatcher rd = request.getRequestDispatcher("/CrearCliente.jsp");
			rd.forward(request, response);
			return;
		}

		// Crear objeto Cliente
		Cliente nuevo_cliente = new Cliente(dni, cuil, nombre, apellido, ob_genero, nacionalidad, fechaNacimiento,
				direccion, ob_localidad, email, telefono);

		// AGREGAR CLIENTE TAMBIEN CREA UNA CUENTA DE TIPO CAJA DE AHORRO POR DEFECTO Y
		// UN NUEVO USUARIO !!!!!
		negCliente.agregarCliente(nuevo_cliente, usuario, contraseña);

		// BUSCO LE CLIENTE QUE ACABO DE AGREGAR
		Cliente clienteAgregado = negCliente.obtenerCliente(dni);
		if (clienteAgregado != null) {
			Movimiento movimiento = new Movimiento();
			ArrayList<Cuenta> c = new ArrayList<Cuenta>();

			c = negCuenta.obtenerCuentasActivas();
			Cuenta nuevaCuenta = new Cuenta();
			for (Cuenta cuenta : c) {
				if (cuenta.getDni().equals(clienteAgregado.getDni())) {
					nuevaCuenta = cuenta;
					break;
				}
			}
			if (nuevaCuenta != null) {
				movimiento.setCuenta(nuevaCuenta);
				movimiento.setFecha(new Date(System.currentTimeMillis()));
				movimiento.setDetalle("Alta de cuenta");
				movimiento.setImporte(10000);

				TipoMovimiento tm = new TipoMovimiento();
				tm.setId(1);
				tm.setDescripcion("Alta de cuenta");

				movimiento.setTipoMovimiento(tm);
				movimiento.setCuentaDestino(nuevaCuenta.getNumeroDeCuenta());

				negMovimiento.agregarMovimiento(movimiento);
				// redireccionar para ADMIN
				RequestDispatcher rd = request.getRequestDispatcher("/clienteCreado.jsp");
				rd.forward(request, response);
			}
		}
	}	
	//ADMIN
	public void eventoModificarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // DOGET
		String dni = request.getParameter("dniC");
		Cliente cliente = negCliente.obtenerCliente(dni);
		Usuario usuario = negUsuario.obtenerUsuario(cliente.getDni());

		request.setAttribute("clienteModificar", cliente);
		request.setAttribute("usuarioModificar", usuario);
		RequestDispatcher rd = request.getRequestDispatcher("ModificarCliente.jsp");
		rd.forward(request, response);
	}
	
	public void eventoModificoCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // DOPOST
		String dni = request.getParameter("txtDNI");
		String cuil = request.getParameter("txtCUIL");
		String nombre = request.getParameter("txtNombre");
		String apellido = request.getParameter("txtApellido");
		String sexo = request.getParameter("txtSexo");
		String nacionalidad = request.getParameter("txtNacionalidad");
		String fechaNacimientoStr = request.getParameter("txtFecNac");
		String direccion = request.getParameter("txtDireccion");
		String localidad = request.getParameter("txtLocalidad");
		String email = request.getParameter("txtEmail");
		String telefono = request.getParameter("txtTelefono");
		String contraseña = request.getParameter("txtContrasenia");

		Localidad ob_localidad;
		Generos ob_genero;

		Date fechaNacimiento = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			// SE PARSEA LAS VARIABLES OBTENIDAS POR EL "request.getParameter"
			java.util.Date parsedDate = sdf.parse(fechaNacimientoStr);
			fechaNacimiento = new java.sql.Date(parsedDate.getTime());

		} catch (java.text.ParseException e) {
			e.printStackTrace();
			request.setAttribute("error", "Formato de fecha de nacimiento inválido");
			RequestDispatcher rd = request.getRequestDispatcher("/CrearCliente.jsp");
			rd.forward(request, response);
			return;
		}
		// CARGO LOS OBJETOS
		// LA LOCALIDAD TIENE UN OBJETO PROVINCIA QUE A SU VEZ TIENE UN OBJETO PAIS
		ob_localidad = negLocalidad.GetLocalidad(Integer.parseInt(localidad));
		ob_genero = negGenero.GetGenero(Integer.parseInt(sexo));

		Cliente cm = new Cliente(dni, cuil, nombre, apellido, ob_genero, nacionalidad, fechaNacimiento, direccion,
				ob_localidad, email, telefono);

		Usuario usuario = negUsuario.obtenerUsuario(cm.getDni());
		usuario.setContraseña(contraseña);
		
		negCliente.modificarCliente(cm,usuario);

		ArrayList<Cliente> listC = negCliente.obtenerClientesActivos();

		request.setAttribute("listaC", listC);

		RequestDispatcher rd = request.getRequestDispatcher("ListadoDeClientes.jsp");
		rd.forward(request, response);

	}
	
	public void eventoActivarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dni = request.getParameter("dniC");
		negCliente.activarCliente(dni);

		ArrayList<Usuario> listU = negUsuario.obtenerUsuarioActivos();
		request.setAttribute("listaUI", listU);

		RequestDispatcher rd = request.getRequestDispatcher("ListadoDeClientes.jsp");
		rd.forward(request, response);
	}
	
	public void eventoEliminarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dni = request.getParameter("dniC");
		
		negCliente.desactivarCliente(dni);

		ArrayList<Cliente> listC = negCliente.obtenerClientesActivos();
		request.setAttribute("listaC", listC);

		RequestDispatcher rd = request.getRequestDispatcher("ListadoDeClientes.jsp");
		rd.forward(request, response);
	}
	
	public void eventoCrearClienteAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Localidad ob_localidad;
		Generos ob_genero;

		int id_sexo;
		int id_localidad;

		String dni = request.getParameter("txtDNI");
		String cuil = request.getParameter("txtCUIL");
		String nombre = request.getParameter("txtNombre");
		String apellido = request.getParameter("txtApellido");
		String sexo = request.getParameter("txtSexo");
		String fechaNacimientoStr = request.getParameter("txtFecNac");
		String direccion = request.getParameter("txtDireccion");
		String localidad = request.getParameter("txtLocalidad");
		String email = request.getParameter("txtEmail");
		String telefono = request.getParameter("txtTelefono");
		String nacionalidad = request.getParameter("txtNacionalidad");

		String usuario = request.getParameter("txtUsuario");
		String contraseña = request.getParameter("txtContrasenia");
		String repetirContraseña = request.getParameter("txtRepeContrasenia");

		Date fechaNacimiento = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			// SE PARSEA LAS VARIABLES OBTENIDAS POR EL "request.getParameter"
			java.util.Date parsedDate = sdf.parse(fechaNacimientoStr);
			fechaNacimiento = new java.sql.Date(parsedDate.getTime());
			id_sexo = Integer.parseInt(sexo);
			id_localidad = Integer.parseInt(localidad);

			// CARGO LOS OBJETOS
			// LA LOCALIDAD TIENE UN OBJETO PROVINCIA QUE A SU VEZ TIENE UN OBJETO PAIS
			ob_localidad = negLocalidad.GetLocalidad(id_localidad);
			ob_genero = negGenero.GetGenero(id_sexo);

		} catch (java.text.ParseException e) {
			e.printStackTrace();
			request.setAttribute("error", "Formato de fecha de nacimiento inválido");
			RequestDispatcher rd = request.getRequestDispatcher("/CrearClienteAdmin.jsp");
			rd.forward(request, response);
			return;
		}
		// verificacion de las contraseñas
		if (!contraseña.equals(repetirContraseña)) {
			System.out.println("Las contraseñas no coinciden");
			request.setAttribute("error", "Las contraseñas no coinciden");
			RequestDispatcher rd = request.getRequestDispatcher("/CrearClienteAdmin.jsp");
			rd.forward(request, response);
			return;
		}

		// Crear objeto Cliente
		Cliente nuevo_cliente = new Cliente(dni, cuil, nombre, apellido, ob_genero, nacionalidad, fechaNacimiento,
				direccion, ob_localidad, email, telefono);

		// AGREGAR CLIENTE TAMBIEN CREA UNA CUENTA DE TIPO CAJA DE AHORRO POR DEFECTO Y
		// UN NUEVO USUARIO !!!!!
		negCliente.agregarCliente(nuevo_cliente, usuario, contraseña);

		// BUSCO LE CLIENTE QUE ACABO DE AGREGAR
		Cliente clienteAgregado = negCliente.obtenerCliente(dni);
		if (clienteAgregado != null) {
			Movimiento movimiento = new Movimiento();
			ArrayList<Cuenta> c = new ArrayList<Cuenta>();

			c = negCuenta.obtenerCuentasActivas();
			Cuenta nuevaCuenta = new Cuenta();
			for (Cuenta cuenta : c) {
				if (cuenta.getDni().equals(clienteAgregado.getDni())) {
					nuevaCuenta = cuenta;
					break;
				}
			}
			if (nuevaCuenta != null) {
				movimiento.setCuenta(nuevaCuenta);
				movimiento.setFecha(new Date(System.currentTimeMillis()));
				movimiento.setDetalle("Alta de cuenta");
				movimiento.setImporte(10000);

				TipoMovimiento tm = new TipoMovimiento();
				tm.setId(1);
				tm.setDescripcion("Alta de cuenta");

				movimiento.setTipoMovimiento(tm);
				movimiento.setCuentaDestino(nuevaCuenta.getNumeroDeCuenta());

				negMovimiento.agregarMovimiento(movimiento);
				
				RequestDispatcher rd = request.getRequestDispatcher("AdminCrearCliente.jsp");
				rd.forward(request, response);
			}
		}
	}
			
	//EVENTOS DE LOGIN Y CARGA DE COMPONENTES
	public void eventoInicio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Cliente clienteLogeado = (Cliente) session.getAttribute("cliente");

		if (clienteLogeado != null) {
			// Obtener las cuentas activas del cliente
			ArrayList<Cuenta> cuentasActivas = negCuenta.obtenerCuentasActivasPorCliente(clienteLogeado.getDni());

			// Actualizar las cuentas en la sesión
			session.setAttribute("cuentasActivas", cuentasActivas);

			// Redirigir a la página de inicio
			RequestDispatcher rd = request.getRequestDispatcher("inicio.jsp");
			rd.forward(request, response);
		} else {
			// Manejar el caso en que no se encuentra el cliente logeado
			request.setAttribute("mensaje", "Debe estar logueado para acceder a esta página.");
			RequestDispatcher rd = request.getRequestDispatcher("Default.jsp");
			rd.forward(request, response);
		}
	}

	public void eventoLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nombre = request.getParameter("txtNombre");
		String contraseña = request.getParameter("txtcontrasenia");

		Usuario usuario = negUsuario.loginUsuario(nombre, contraseña);

		if (usuario != null) {
			request.getSession().setAttribute("usuario", usuario);

			if ("Admin".equals(usuario.getTipoUsuario())) {
				// Si es administrador, redirige a la página de inicio de administrador
				Cliente clienteActual = negCliente.obtenerCliente(usuario.getCliente().getDni());
				request.getSession().setAttribute("cliente", clienteActual);
				RequestDispatcher rd = request.getRequestDispatcher("InicioAdmin.jsp");
				rd.forward(request, response);
			} else if ("Cliente".equals(usuario.getTipoUsuario())) {
				// Si es cliente, obtiene y configura las cuentas del cliente
				Cliente clienteActual = negCliente.obtenerCliente(usuario.getCliente().getDni());

				if (clienteActual != null) {
					ArrayList<Cuenta> cuentasActivas = negCuenta
							.obtenerCuentasActivasPorCliente(clienteActual.getDni());
					ArrayList<Cuenta> cuentasInactivas = negCuenta
							.obtenerCuentasInactivasPorCliente(clienteActual.getDni());

					clienteActual.setCuentas(new ArrayList<Cuenta>());
					clienteActual.getCuentas().addAll(cuentasActivas);
					clienteActual.getCuentas().addAll(cuentasInactivas);

					request.getSession().setAttribute("cliente", clienteActual);
					request.getSession().setAttribute("cuentasActivas", cuentasActivas);
					request.getSession().setAttribute("cuentasInactivas", cuentasInactivas);
				} else {
					// Manejo si no se pudo obtener el cliente
					request.setAttribute("errorMessage", "No se pudo obtener información del cliente");
					RequestDispatcher rd = request.getRequestDispatcher("Default.jsp");
					rd.forward(request, response);
					return; // Termina la ejecución del método
				}

				RequestDispatcher rd = request.getRequestDispatcher("inicio.jsp");
				rd.forward(request, response);
			} else {
				// Manejo si el tipo de usuario no es reconocido
				request.setAttribute("errorMessage", "Tipo de usuario desconocido");
				RequestDispatcher rd = request.getRequestDispatcher("Default.jsp");
				rd.forward(request, response);
			}
		} else {
			// Manejo si el usuario o contraseña son incorrectos
			request.setAttribute("errorMessage", "Usuario o contraseña incorrectos");
			RequestDispatcher rd = request.getRequestDispatcher("Default.jsp");
			rd.forward(request, response);
		}
	}

	public void eventoCargarDesplegables(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DesplegablesSinConexionException {
		
		try 
		{
			// Obtener lista de géneros
			ArrayList<Generos> listGeneros = negGenero.cargarGeneros();
			request.setAttribute("listaG", listGeneros);

			// Obtener lista de países
			ArrayList<Pais> listPaises = negPais.obtenerPaises();
			request.setAttribute("listaPaises", listPaises);

			// Obtener lista de países
			ArrayList<Provincia> provincias = negProvincia.obtenerProvincias();
			request.setAttribute("listaP", provincias);

			ArrayList<Localidad> localidades = negLocalidad.GetLocalidades();
			request.setAttribute("listaL", localidades);

			if (listGeneros == null || listPaises == null) 
			{
				throw new DesplegablesSinConexionException();
			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("Default.jsp");
			rd.forward(request, response);
		}
	}
	
}
