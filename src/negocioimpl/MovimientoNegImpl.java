package negocioimpl;

import java.sql.Date;
import java.util.List;

import datos.IMovimientoDAO;
import datosimpl.MovimientoDaoImpl;
import entidad.Movimiento;
import negocio.IMovimientoNeg;

public class MovimientoNegImpl implements IMovimientoNeg{
	
	private IMovimientoDAO movimientoDao = new MovimientoDaoImpl();

	@Override
	public List<Movimiento> getMovimientos() {
		return movimientoDao.getMovimientos();
	}
	
	public void agregarMovimiento(Movimiento movimiento)
	{
		movimientoDao.agregarMovimiento(movimiento);
	}

	@Override
	public double GetCantidadSemana() {
		return movimientoDao.GetCantidadSemana();
	}

	@Override
	public double GetCantidadMovida(Date fechaDesdeDate, Date fechaHastaDate) {
		return movimientoDao.GetCantidadMovida(fechaDesdeDate, fechaHastaDate);
	}
}
