package negocioimpl;

import java.util.ArrayList;

import datos.IPrestamoDAO;
import datosimpl.PrestamoDaoImpl;
import entidad.Cliente;
import entidad.Prestamo;
import negocio.IPrestamoNeg;

public class PrestamoNegImpl implements IPrestamoNeg{
	private IPrestamoDAO prestamoDao = new PrestamoDaoImpl();

	@Override
	public ArrayList<Prestamo> getPrestamosActivos() {
		// TODO Auto-generated method stub
		return prestamoDao.getPrestamosActivos();
	}

	@Override
	public ArrayList<Prestamo> getPrestamosInactivos() {
		// TODO Auto-generated method stub
		return prestamoDao.getPrestamosInactivos();
	}

	@Override
	public void solicitarPrestamo(Prestamo prestamo) {
		// TODO Auto-generated method stub
		prestamoDao.solicitarPrestamo(prestamo);
	}

	@Override
	public ArrayList<Prestamo> getPrestamos() {
		// TODO Auto-generated method stub
		return prestamoDao.getPrestamos();
	}

	@Override
	public void activarPrestamo(int cuenta,int id, int cbu,double importe_pedido) {
		// TODO Auto-generated method stub
		prestamoDao.activarPrestamo(cuenta,id,cbu,importe_pedido);
		
	}

	@Override
	public void rechazarPrestamo(int idInt) {
		// TODO Auto-generated method stub
		prestamoDao.rechazarPrestamo(idInt);
	}

	@Override
	public void saldarPrestamo(int idInt) {
		// TODO Auto-generated method stub
		prestamoDao.saldarPrestamo(idInt);
	}

	@Override
	public ArrayList<Prestamo> getPrestamosXCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return prestamoDao.getPrestamosXCliente(cliente);
	}

	@Override
	public Prestamo getPrestamos(int id,int p) {
		// TODO Auto-generated method stub
		return prestamoDao.getPrestamos(id,p);
	}

	@Override
	public void pagarPrestamo(int idPrestamoInt, int numCuenta, int cbuInt, double importe) {
		// TODO Auto-generated method stub
		prestamoDao.pagarPrestamo(idPrestamoInt,numCuenta,cbuInt,importe);
	}
	
	
}
