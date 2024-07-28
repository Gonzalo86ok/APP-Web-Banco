package datos;

import java.util.ArrayList;

import entidad.Cliente;
import entidad.Prestamo;

public interface IPrestamoDAO {
	public ArrayList<Prestamo> getPrestamosActivos();
	public ArrayList<Prestamo> getPrestamosInactivos();
	public void solicitarPrestamo(Prestamo prestamo);
	ArrayList<Prestamo> getPrestamos();
	public void activarPrestamo(int cuenta,int id, int cbu,double importe_pedido);
	public void rechazarPrestamo(int idInt);
	public void saldarPrestamo(int idInt);
	public ArrayList<Prestamo> getPrestamosXCliente(Cliente cliente);
	public Prestamo getPrestamos(int id,int p);
	public void pagarPrestamo(int idPrestamoInt, int numCuenta, int cbuInt, double importe);
}
