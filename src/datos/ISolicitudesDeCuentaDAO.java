package datos;

import java.util.List;

import entidad.SolicitudDeAlta;

public interface ISolicitudesDeCuentaDAO {
	public void agregarSolicitud(SolicitudDeAlta solicitud);
	public List<SolicitudDeAlta> obtenerTodasLasSolicitudesActivas();
	public SolicitudDeAlta obtenerSolicitudPorId(int id);
	public List<SolicitudDeAlta> obtenerTodasLasSolicitudesActivasPorCliente(String dniCliente);
	public void eliminarSolicitud(int id);
}
