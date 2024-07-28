package datos;

import java.util.ArrayList;

import entidad.Cliente;
import entidad.Usuario;

public interface IClienteDao {

	public void agregarCliente(Cliente c,String usuario,String contraseña);

	public int modificarCliente(Cliente c,Usuario u);

	public Cliente obtenerCliente(String DNI);

	public ArrayList<Cliente> obtenerClientesActivos();

	public ArrayList<Cliente> obtenerClientesInactivos();

	public void activarCliente(String dni);

	public void desactivarCliente(String dni);
	
	public ArrayList<Cliente> buscarClientes(String buscarCliente);

}
