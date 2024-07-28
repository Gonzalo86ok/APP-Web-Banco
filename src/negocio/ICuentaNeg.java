package negocio;

import java.util.ArrayList;
import entidad.Cuenta;

public interface ICuentaNeg {

	public ArrayList<Cuenta> obtenerCuentasActivas();

	public ArrayList<Cuenta> obtenerCuentasInactivas();
	
	public Cuenta obtenerCuentaPorTipo(String dniCliente, int id_tipoCuenta);
	
	public String ObtenerUltimoNumCuenta();

	public void agregarCuenta(Cuenta c);

	public int modificarSaldoCuenta(Cuenta c);

	public void eliminarCuenta(int numCuenta);

	public void activarCuenta(String dni);

	public ArrayList<Cuenta> obtenerCuentasInactivasPorCliente(String dniCliente);
	
	public ArrayList<Cuenta> obtenerCuentasActivasPorCliente(String dniCliente);
	
	public Cuenta buscarPorCBU(int cbu);
	
	public int obtenerUltimoCBU();

	public float GetPromedioSaldos();

	public float GetPorcentajeCuentasActivas();

	public float GetPorcentajeCuentasInactivas();
	
	public ArrayList<Cuenta> obtenerCuentas(); 
}
