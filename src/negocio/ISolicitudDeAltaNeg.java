package negocio;

import java.util.List;

import entidad.SolicitudDeAlta;

public interface ISolicitudDeAltaNeg {
	public void agregarSolicitud(SolicitudDeAlta solicitud);
	public List<SolicitudDeAlta> obtenerTodasLasSolicitudesActivas();
	public SolicitudDeAlta obtenerSolicitudPorId(int id);
	public List<SolicitudDeAlta> obtenerTodasLasSolicitudesActivasPorCliente(String dniCliente);
	public void eliminarSolicitud(int id);
}
