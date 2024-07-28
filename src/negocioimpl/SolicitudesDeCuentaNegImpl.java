package negocioimpl;

import java.util.List;

import datos.ISolicitudesDeCuentaDAO;
import datosimpl.SolicitudDeAltaDaoImpl;
import entidad.SolicitudDeAlta;
import negocio.ISolicitudDeAltaNeg;

public class SolicitudesDeCuentaNegImpl implements ISolicitudDeAltaNeg{
	private ISolicitudesDeCuentaDAO solicitudesDao = new SolicitudDeAltaDaoImpl();

	@Override
	public void agregarSolicitud(SolicitudDeAlta solicitud) {
		solicitudesDao.agregarSolicitud(solicitud);
	}

	@Override
	public List<SolicitudDeAlta> obtenerTodasLasSolicitudesActivas() {
		return solicitudesDao.obtenerTodasLasSolicitudesActivas();
	}

	@Override
	public SolicitudDeAlta obtenerSolicitudPorId(int id) {
		return solicitudesDao.obtenerSolicitudPorId(id);
	}

	@Override
	public List<SolicitudDeAlta> obtenerTodasLasSolicitudesActivasPorCliente(String dniCliente) {
		return solicitudesDao.obtenerTodasLasSolicitudesActivasPorCliente(dniCliente);
	}

	@Override
	public void eliminarSolicitud(int id) {
		solicitudesDao.eliminarSolicitud(id);
	}
}
