package datosimpl;

import entidad.Cuenta;
import entidad.TipoCuenta;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import datos.ICuentaDAO;

public class CuentaDaoImpl implements ICuentaDAO {

	private Conexion cn = new Conexion();

	@Override
	public ArrayList<Cuenta> obtenerCuentasActivas() {
		ArrayList<Cuenta> listCuenta = new ArrayList<>();

		String query = "SELECT c.numero_de_cuenta, c.dni_cliente, c.fecha_de_creacion, "
				+ "c.id_tipo_cuenta, tc.descripcion AS tipo_cuenta, " + "c.cbu, c.saldo, c.estado " + "FROM cuentas c "
				+ "INNER JOIN TipoDeCuenta tc ON c.id_tipo_cuenta = tc.id " + "WHERE c.estado = 'A'";

		try (Connection connection = cn.Open();
				CallableStatement cs = connection.prepareCall(query);
				ResultSet rs = cs.executeQuery()) {

			while (rs.next()) {
				Cuenta cuenta = new Cuenta();
				cuenta.setNumeroDeCuenta(rs.getInt("numero_de_cuenta"));
				cuenta.setDni(rs.getString("dni_cliente"));
				cuenta.setFechaDeCreacion(rs.getDate("fecha_de_creacion"));

				TipoCuenta tipoCuenta = new TipoCuenta();
				tipoCuenta.setId(rs.getInt("id_tipo_cuenta"));
				tipoCuenta.setDescripcion(rs.getString("tipo_cuenta"));
				cuenta.setTipoCuenta(tipoCuenta);

				cuenta.setCbu(rs.getInt("cbu"));
				cuenta.setSaldo(rs.getDouble("saldo"));
				cuenta.setEstado(rs.getString("estado"));

				listCuenta.add(cuenta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}

		return listCuenta;
	}

	public ArrayList<Cuenta> obtenerCuentasInactivas() {

		ArrayList<Cuenta> listCuenta = new ArrayList<Cuenta>();
		String query = "SELECT c.numero_de_cuenta, c.dni_cliente, c.fecha_de_creacion, "
				+ "c.id_tipo_cuenta, tc.descripcion AS tipo_cuenta, " + "c.cbu, c.saldo, c.estado " + "FROM cuentas c "
				+ "INNER JOIN TipoDeCuenta tc ON c.id_tipo_cuenta = tc.id " + "WHERE c.estado = 'I'";

		try (Connection connection = cn.Open();
				CallableStatement cs = connection.prepareCall(query);
				ResultSet rs = cs.executeQuery()) {

			while (rs.next()) {
				Cuenta cuenta = new Cuenta();

				cuenta.setNumeroDeCuenta(rs.getInt("numero_de_cuenta"));
				cuenta.setDni(rs.getString("dni_cliente"));
				cuenta.setFechaDeCreacion(rs.getDate("fecha_de_creacion"));

				TipoCuenta tipoCuenta = new TipoCuenta();
				tipoCuenta.setId(rs.getInt("id_tipo_cuenta"));
				tipoCuenta.setDescripcion(rs.getString("tipo_cuenta"));
				cuenta.setTipoCuenta(tipoCuenta);

				cuenta.setCbu(rs.getInt("cbu"));
				cuenta.setSaldo(rs.getDouble("saldo"));
				cuenta.setEstado(rs.getString("estado"));

				listCuenta.add(cuenta);
			}
			return listCuenta;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return new ArrayList<Cuenta>();
	}

	public Cuenta obtenerCuentaPorTipo(String dniCliente, int id_tipoCuenta) {

		ArrayList<Cuenta> cuentas = this.obtenerCuentasActivas();

		for (Cuenta cuenta : cuentas) {
			if (cuenta.getDni().equalsIgnoreCase(dniCliente) && cuenta.getTipoCuenta().getId() == id_tipoCuenta) {
				return cuenta;
			}
		}
		return new Cuenta();
	}

	public void agregarCuenta(Cuenta c) {

		String query = "INSERT INTO Cuentas (dni_cliente, fecha_de_creacion, id_tipo_cuenta, cbu, saldo) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try (Connection connection = cn.Open(); PreparedStatement pst = connection.prepareStatement(query)) {
			pst.setString(1, c.getDni());
			pst.setDate(2, c.getFechaDeCreacion());
			pst.setInt(3, c.getTipoCuenta().getId());
			pst.setInt(4, c.getCbu());
			pst.setDouble(5, c.getSaldo());

			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
	}

	public String ObtenerUltimoNumCuenta() {
		String ultimo = "0";
		cn.Open();

		try {
			String query = "SELECT MAX(numero_de_cuenta) AS ultimo_num_cuenta FROM cuentas";
			PreparedStatement pst = cn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				ultimo = rs.getString("ultimo_num_cuenta");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return ultimo;
	}

	public int modificarSaldoCuenta(Cuenta c) {
		String query = "{CALL ModificarSaldoCuenta(?, ?)}";
		int filas = 0;

		try (Connection connection = cn.Open(); CallableStatement cst = connection.prepareCall(query)) {

			cst.setInt(1, c.getNumeroDeCuenta());
			cst.setDouble(2, c.getSaldo());

			filas = cst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filas;
	}

	public void eliminarCuenta(int numCuenta) {
	    try (Connection connection = cn.Open();
	         PreparedStatement ps = connection.prepareStatement(
	                 "UPDATE Cuentas SET estado = 'I' WHERE numero_de_cuenta = ?")) {

	        ps.setInt(1, numCuenta);

	        int filasAfectadas = ps.executeUpdate();
	        if (filasAfectadas > 0) {
	            System.out.println("La cuenta se dio de baja correctamente.");
	        } else {
	            System.out.println("No se encontró la cuenta o no se realizó la baja.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void activarCuenta(String dni) {

		try (Connection connection = cn.Open();
				CallableStatement cs = connection.prepareCall("{CALL ModificarEstadoCuenta(?, ?)}"))

		{

			cs.setString(1, dni);
			cs.setString(2, "A");

			cs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Cuenta buscarPorCBU(int cbu) {

		ArrayList<Cuenta> cuentas = this.obtenerCuentasActivas();

		for (Cuenta cuenta : cuentas) {
			if (cuenta.getCbu() == cbu) {
				return cuenta;
			}
		}
		return new Cuenta();
	}

	@Override
	public ArrayList<Cuenta> obtenerCuentasInactivasPorCliente(String dniCliente) {

		ArrayList<Cuenta> cuentas = this.obtenerCuentasInactivas();
		ArrayList<Cuenta> cuentas_cliente = new ArrayList<Cuenta>();

		for (Cuenta cuenta : cuentas) {
			System.out.println(cuenta);
			if (cuenta.getDni().contentEquals(dniCliente)) {
				cuentas_cliente.add(cuenta);
			}
		}
		return cuentas_cliente;
	}

	@Override
	public ArrayList<Cuenta> obtenerCuentasActivasPorCliente(String dniCliente) {

		ArrayList<Cuenta> cuentas = this.obtenerCuentasActivas();
		ArrayList<Cuenta> cuentas_cliente = new ArrayList<Cuenta>();

		for (Cuenta cuenta : cuentas) {
			if (cuenta.getDni().contentEquals(dniCliente)) 
			{
				cuentas_cliente.add(cuenta);
			}
		}
		return cuentas_cliente;
	}

	@Override
	public int obtenerUltimoCBU() 
	{		
		String query = "SELECT cbu FROM cuentas";
		cn.Open();
		int ultimo = 0;
		try (CallableStatement cs = cn.prepareCall(query);
				ResultSet rs = cs.executeQuery()) 
		{
			while (rs.next()) 
			{
				ultimo = rs.getInt("cbu");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally
		{
			cn.close();
		}
		return ultimo;
	}

	@Override
	public float GetPromedioSaldos() {
	    ArrayList<Cuenta> cuentas = this.obtenerCuentasActivas();
	    
	    if (cuentas == null || cuentas.isEmpty()) {
	        return 0;
	    }
	    
	    float sumaSaldos = 0;
	    
	    for (Cuenta cuenta : cuentas) {
	        sumaSaldos += cuenta.getSaldo();
	    }
	    
	    float promedio = sumaSaldos / cuentas.size();
	    
	    return promedio;
	}

	@Override
	public float GetPorcentajeCuentasActivas() {
	    ArrayList<Cuenta> cuentasActivas = this.obtenerCuentasActivas();
	    ArrayList<Cuenta> cuentasInactivas = this.obtenerCuentasInactivas();

	    int totalCuentas = cuentasActivas.size() + cuentasInactivas.size();
	    
	    // Evitar división por cero
	    if (totalCuentas == 0) {
	        return 0;
	    }

	    float porcentaje = ((float) cuentasActivas.size() * 100) / totalCuentas;
	    
	    return porcentaje;
	}

	@Override
	public float GetPorcentajeCuentasInactivas() {
		ArrayList<Cuenta> cuentasActivas = this.obtenerCuentasActivas();
	    ArrayList<Cuenta> cuentasInactivas = this.obtenerCuentasInactivas();

	    int totalCuentas = cuentasActivas.size() + cuentasInactivas.size();
	    
	    // Evitar división por cero
	    if (totalCuentas == 0) {
	        return 0;
	    }

	    float porcentaje = ((float) cuentasInactivas.size() * 100) / totalCuentas;
	    
	    return porcentaje;
	}

	@Override
	public ArrayList<Cuenta> obtenerCuentas() {
		
		ArrayList<Cuenta> listCuenta = new ArrayList<Cuenta>();
		ArrayList<TipoCuenta> listTipoCuenta = this.obtenerTipoCuenta();
		System.out.println("tipo cuentas:"  + listTipoCuenta);
		
		

		String query = "SELECT * FROM CUENTAS";
		cn.Open();
		try (
				CallableStatement cs = cn.prepareCall(query);
				ResultSet rs = cs.executeQuery()) {

			while (rs.next()) {
				Cuenta cuenta = new Cuenta();
				cuenta.setNumeroDeCuenta(rs.getInt("numero_de_cuenta"));
				cuenta.setDni(rs.getString("dni_cliente"));
				cuenta.setFechaDeCreacion(rs.getDate("fecha_de_creacion"));

				for (TipoCuenta tipo : listTipoCuenta) {
					if(tipo.getId()==rs.getInt("id_tipo_cuenta")) {
						
						cuenta.setTipoCuenta(tipo);

					}
				}

				cuenta.setCbu(rs.getInt("cbu"));
				cuenta.setSaldo(rs.getDouble("saldo"));
				cuenta.setEstado(rs.getString("estado"));

				listCuenta.add(cuenta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}

		return listCuenta;
	}
	
	public ArrayList<TipoCuenta> obtenerTipoCuenta() {
		
		ArrayList<TipoCuenta> listTipoCuenta = new ArrayList<>();

		String query = "SELECT * FROM TIPODECUENTA";

		try (Connection connection = cn.Open();
				CallableStatement cs = connection.prepareCall(query);
				ResultSet rs = cs.executeQuery()) {

			while (rs.next()) {
				TipoCuenta tipoCuenta = new TipoCuenta();
				tipoCuenta.setId(rs.getInt("id"));
				tipoCuenta.setDescripcion(rs.getString("descripcion"));
				

				listTipoCuenta.add(tipoCuenta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}

		return listTipoCuenta;
	}
}