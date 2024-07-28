package datosimpl;

import java.sql.*;
import java.util.ArrayList;

import datos.IPrestamoDAO;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Prestamo;

public class PrestamoDaoImpl implements IPrestamoDAO {
	
	private Conexion cn;
	
	public PrestamoDaoImpl() {
        this.cn = new Conexion();
    }
	
	@Override
	public ArrayList<Prestamo> getPrestamos() {
		ClienteDaoImpl clienteDao = new ClienteDaoImpl();
		CuentaDaoImpl cuentaDao = new CuentaDaoImpl();

		ArrayList<Prestamo> prestamos = new ArrayList<>();
		ArrayList<Cliente> listaCliente = clienteDao.obtenerClientesActivos();
		ArrayList<Cuenta> listaCuenta = cuentaDao.obtenerCuentasActivas();
		
		cn = new Conexion();
		cn.Open();

		try {
			if (cn != null) {
				String query = "SELECT * FROM prestamos";
				try (CallableStatement cs = cn.prepareCall(query)) {
					ResultSet rs = cs.executeQuery();

					while (rs.next()) {
						
						Prestamo prestamo = new Prestamo();
						prestamo.setId(rs.getInt("id"));
						
						for(Cliente cliente : listaCliente){
							if(cliente.getDni().equals(rs.getString("dni_cliente"))){
								prestamo.setCliente(cliente);
							}
						}
						
						for(Cuenta cuenta : listaCuenta){
							if(cuenta.getCbu() == rs.getInt("cbu")){
								prestamo.setCuenta(cuenta);
							}
						}
						
						prestamo.setFecha(rs.getDate("fecha"));
						prestamo.setImporteConIntereses(rs.getDouble("importe_con_intereses"));
						prestamo.setImportePedido(rs.getDouble("importe_pedido"));
						prestamo.setPlazoDePagoEnMeses(rs.getInt("plazo_de_pago_en_meses"));
						prestamo.setMontoMensual(rs.getDouble("monto_mensual"));
						prestamo.setCuotasPagadas(rs.getInt("cuotas_a_pagar"));
						prestamo.setEstado(rs.getString("estado"));
						prestamo.setPrestamoSaldado(rs.getString("prestamo_saldado"));
						prestamos.add(prestamo);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return prestamos;
	}
	
	public ArrayList<Prestamo> getPrestamosActivos() {
		ArrayList<Prestamo> prestamos = getPrestamos();
		ArrayList<Prestamo> prestamosActivos = new ArrayList<Prestamo>();
		
		
		for(Prestamo prestamo : prestamos) {
			if(prestamo.getEstado().equalsIgnoreCase("A")) {
				prestamosActivos.add(prestamo);
			}
		}
		return prestamosActivos;
	}

	public ArrayList<Prestamo> getPrestamosInactivos() {
		ArrayList<Prestamo> prestamos = getPrestamos();
		ArrayList<Prestamo> prestamosInactivos = new ArrayList<Prestamo>();
		
		
		for(Prestamo prestamo : prestamos) {
			if(prestamo.getEstado().equalsIgnoreCase("I") && prestamo.getPrestamoSaldado().equalsIgnoreCase("N")) {
				prestamosInactivos.add(prestamo);
			}
		}
		return prestamosInactivos;
	}
	
	public ArrayList<Prestamo> getPrestamosSaldados() {
		ArrayList<Prestamo> prestamos = getPrestamos();
		ArrayList<Prestamo> prestamosSaldados = new ArrayList<Prestamo>();
		
		
		for(Prestamo prestamo : prestamos) {
			if(prestamo.getPrestamoSaldado().equalsIgnoreCase("S")) {
				prestamosSaldados.add(prestamo);
			}
		}
		return prestamosSaldados;
	}

	public ArrayList<Prestamo> getPrestamosXCliente(Cliente cliente){
		 ArrayList<Prestamo> prestamosActivos = getPrestamosActivos();
		    ArrayList<Prestamo> prestamosDelCliente = new ArrayList<>();

		    for (Prestamo prestamo : prestamosActivos) {
		        if (prestamo.getCliente().getDni().equals(cliente.getDni())) {
		            prestamosDelCliente.add(prestamo);
		        }
		    }
		return prestamosDelCliente;
	}
	
	@Override
	public void solicitarPrestamo(Prestamo prestamo) {
	   
	    String query = "INSERT INTO prestamos (dni_cliente, cbu, fecha, importe_con_intereses, importe_pedido, plazo_de_pago_en_meses, monto_mensual, cuotas_a_pagar, estado, prestamo_saldado) "
	    				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    	    
	    try (Connection connection = cn.Open(); PreparedStatement pst = connection.prepareStatement(query)){
	        
	        pst.setString(1, prestamo.getCliente().getDni());
	        pst.setInt(2, prestamo.getCuenta().getCbu());
	        pst.setDate(3, new java.sql.Date(prestamo.getFecha().getTime()));
	        pst.setDouble(4, Math.round(prestamo.getImporteConIntereses() * 100.0) / 100.0); // Redondear a dos decimales
	        pst.setDouble(5, prestamo.getImportePedido());
	        pst.setInt(6, prestamo.getPlazoDePagoEnMeses());
	        pst.setDouble(7, Math.round(prestamo.getMontoMensual() * 100.0) / 100.0); // Redondear a dos decimales
	        pst.setInt(8, prestamo.getCuotasPagadas());
	        pst.setString(9, prestamo.getEstado());
	        pst.setString(10, prestamo.getPrestamoSaldado());

	        int rowsAffected = pst.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            System.out.println("Prestamo insertado correctamente.");
	        } else {
	            System.out.println("Error al insertar el prestamo.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	}

	@Override
	public void activarPrestamo(int cuenta,int id, int cbu,double importe_pedido) {
		String query = "{CALL ActivarPrestamo(?,?,?,?)}";
		cn.Open();

		try (CallableStatement cst = cn.prepareCall(query)) {

			cst.setInt(1, cuenta);
			cst.setInt(2, id);
			cst.setInt(3, cbu);
			cst.setDouble(4, importe_pedido);
		
			cst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
	}

	@Override
	public void rechazarPrestamo(int idInt) {
		String query = "UPDATE prestamos SET estado = 'R' WHERE id = ? ";
	
		try (Connection connection = cn.Open(); PreparedStatement pst = connection.prepareStatement(query)) {
			pst.setInt(1, idInt);
			pst.executeUpdate();
	
		} catch (Exception e) {
			e.printStackTrace();
	
		} finally {
			cn.close();
		}
	}

	@Override
	public void saldarPrestamo(int idInt) {

		String query = "UPDATE prestamos SET prestamo_saldado = 'S' WHERE id = ? ";
		
		try (Connection connection = cn.Open(); PreparedStatement pst = connection.prepareStatement(query)) {
			pst.setInt(1, idInt);
			pst.executeUpdate();
	
		} catch (Exception e) {
			e.printStackTrace();
	
		} finally {
			cn.close();
		}
	}
	
	@Override
	public Prestamo getPrestamos(int id,int p) {
		
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		if(p == 0) {
			prestamos = getPrestamosActivos();			
		}else if (p==1) {
			prestamos = getPrestamosInactivos();
		}
						
		for(Prestamo prestamo : prestamos) {
			if(prestamo.getId() == id) {
				return prestamo;
			}
		}
		return new Prestamo();
	}
	
	@Override
	public void pagarPrestamo(int idPrestamoInt, int numCuenta, int cbuInt, double importe) {
		
		String query = "{CALL pagarCuota(?,?,?,?)}";
		cn.Open();

		try (CallableStatement cst = cn.prepareCall(query)) {

			cst.setInt(1, idPrestamoInt);			
			cst.setInt(2, numCuenta);
			cst.setInt(3, cbuInt);
			cst.setDouble(4, importe);
		
			cst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
	}

}

