package datosimpl;

import entidad.Cliente;
import entidad.SolicitudDeAlta;
import entidad.TipoCuenta;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import datos.ISolicitudesDeCuentaDAO;

public class SolicitudDeAltaDaoImpl implements ISolicitudesDeCuentaDAO {

	private Conexion cn = new Conexion();

	public void agregarSolicitud(SolicitudDeAlta solicitud) {

		String query = "INSERT INTO solicitudDeAlta (dniCliente, tipoDeCuenta, estado) VALUES (?, ?, ?)";

		cn.Open();
		try (CallableStatement cst = cn.prepareCall(query)) {
			cst.setString(1, solicitud.getCliente().getDni());
			cst.setInt(2, solicitud.getTipoCuenta().getId());
			cst.setString(3, solicitud.getEstado());

			cst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
	}

	public ArrayList<SolicitudDeAlta> obtenerTodasLasSolicitudesActivas() {

		ArrayList<SolicitudDeAlta> solicitudes = new ArrayList<>();
		String query = "SELECT * FROM solicituddealta WHERE estado = 'A'";

		try (Connection connection = cn.Open();
				CallableStatement cs = connection.prepareCall(query);
				ResultSet rs = cs.executeQuery()) {
			while (rs.next()) {
				SolicitudDeAlta solicitud = new SolicitudDeAlta();
				solicitud.setId(rs.getInt("id"));

				// Crear una nueva instancia de Cliente para cada solicitud
				Cliente cliente = new Cliente();
				cliente.setDni(rs.getString("dniCliente"));
				solicitud.setCliente(cliente);

				// Crear una nueva instancia de TipoCuenta para cada solicitud
				TipoCuenta tipoCuenta = new TipoCuenta();
				tipoCuenta.setId(rs.getInt("tipoDeCuenta"));

				// Comparar y asignar la descripción del tipo de cuenta
				if (tipoCuenta.getId() == 1) {
					tipoCuenta.setDescripcion("Caja de ahorro");
				} else {
					tipoCuenta.setDescripcion("Cuenta corriente");
				}

				solicitud.setTipoCuenta(tipoCuenta);
				solicitud.setEstado(rs.getString("estado"));

				solicitudes.add(solicitud);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return solicitudes;
	}

	public SolicitudDeAlta obtenerSolicitudPorId(int id) {
		ArrayList<SolicitudDeAlta> lista = this.obtenerTodasLasSolicitudesActivas();

		for (SolicitudDeAlta solicitud : lista) {
			if (solicitud.getId() == id) {
				return solicitud;
			}
		}

		return new SolicitudDeAlta();
	}

	public ArrayList<SolicitudDeAlta> obtenerTodasLasSolicitudesActivasPorCliente(String dniCliente) {
		ArrayList<SolicitudDeAlta> lista = this.obtenerTodasLasSolicitudesActivas();
		ArrayList<SolicitudDeAlta> solicitudes = new ArrayList<>();

		for (SolicitudDeAlta solicitud : lista) {
			if (solicitud.getCliente().getDni().contentEquals(dniCliente)) {
				solicitudes.add(solicitud);
			}
		}

		return solicitudes;
	}

	public void eliminarSolicitud(int id) {
		String query = "UPDATE solicituddealta SET estado = 'I' WHERE id = ?";

		try (Connection connection = cn.Open(); PreparedStatement pst = connection.prepareStatement(query)) {
			pst.setInt(1, id);
			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			cn.close();
		}
	}
}
