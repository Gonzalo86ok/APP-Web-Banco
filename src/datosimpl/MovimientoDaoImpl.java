package datosimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import datos.IMovimientoDAO;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.TipoMovimiento;

public class MovimientoDaoImpl implements IMovimientoDAO {

	private Conexion cn = new Conexion();

	public List<Movimiento> getMovimientos() {

		List<Movimiento> movimientos = new ArrayList<>();
		String query = "SELECT * FROM movimientos";

		try (Connection connection = cn.Open();
				CallableStatement cs = connection.prepareCall(query);
				ResultSet rs = cs.executeQuery()) {
			while (rs.next()) {
				Movimiento movimiento = new Movimiento();
				movimiento.setId(rs.getInt("id"));

				// Crear y configurar objeto Cuenta
				Cuenta cuenta = new Cuenta();
				cuenta.setNumeroDeCuenta(rs.getInt("numero_de_cuenta"));
				movimiento.setCuenta(cuenta);

				movimiento.setFecha(rs.getDate("fecha"));
				movimiento.setDetalle(rs.getString("detalle"));
				movimiento.setImporte(rs.getDouble("importe"));

				// Crear y configurar objeto TipoMovimiento
				TipoMovimiento tipoMovimiento = new TipoMovimiento();
				tipoMovimiento.setId(rs.getInt("id_tipo_movimiento"));
				movimiento.setTipoMovimiento(tipoMovimiento);

				movimiento.setCuentaOrigen(rs.getInt("cuenta_origen"));
				movimiento.setCuentaDestino(rs.getInt("cuenta_destino"));

				movimientos.add(movimiento);
			}

		} catch (SQLException e) {
			// Manejar la excepción según tus requerimientos
			e.printStackTrace(); // Puedes personalizar el manejo de la excepción aquí
		} finally {

			cn.close();
		}
		return movimientos;
	}

	public void agregarMovimiento(Movimiento movimiento) {
		cn.Open();
		try {
			String query = "INSERT INTO Movimientos (numero_de_cuenta, fecha, detalle, importe, id_tipo_movimiento, cuenta_origen, cuenta_destino) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

			try (CallableStatement cs = cn.prepareCall(query)) {
				cs.setInt(1, movimiento.getCuenta().getNumeroDeCuenta());
				cs.setDate(2, new java.sql.Date(movimiento.getFecha().getTime()));
				cs.setString(3, movimiento.getDetalle());
				cs.setDouble(4, movimiento.getImporte());
				cs.setInt(5, movimiento.getTipoMovimiento().getId());
				cs.setInt(6, movimiento.getCuentaOrigen());
				cs.setInt(7, movimiento.getCuentaDestino());

				cs.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
	}

	@Override
	public double GetCantidadSemana() {
		String query = "SELECT SUM(importe) AS total_importe FROM Movimientos WHERE fecha >= CURDATE() - INTERVAL 7 DAY;";
		
		double importeSemanal = 0;
		
		cn.Open();
		try (CallableStatement cs = cn.prepareCall(query); ResultSet rs = cs.executeQuery()) {
			while (rs.next()) {
				importeSemanal = rs.getDouble("total_importe");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return importeSemanal;
	}

	@Override
	public double GetCantidadMovida(Date fechaDesdeDate, Date fechaHastaDate) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    String fechaDesde = sdf.format(fechaDesdeDate);
	    String fechaHasta = sdf.format(fechaHastaDate);
	    double cantidadMovida = 0;
	    
	    String query = "SELECT SUM(importe) AS total_importe FROM Movimientos WHERE fecha >= " + fechaDesde + " AND fecha <= " + fechaHasta + ";";
	    
	    try {
	        cn.Open();
	        try (CallableStatement cs = cn.prepareCall(query); ResultSet rs = cs.executeQuery()) {
				while (rs.next()) {
					cantidadMovida = rs.getDouble("total_importe");
				}
			}
	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    finally {cn.close();}
	    
	    return cantidadMovida;
	}

}
