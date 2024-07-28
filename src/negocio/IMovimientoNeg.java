package negocio;

import java.sql.Date;
import java.util.List;

import entidad.Movimiento;

public interface IMovimientoNeg {
	
	public List<Movimiento> getMovimientos();
	
	public void agregarMovimiento(Movimiento movimiento);

	public double GetCantidadSemana();

	public double GetCantidadMovida(Date fechaDesdeDate, Date fechaHastaDate);
}
