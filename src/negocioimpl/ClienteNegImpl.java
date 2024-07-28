package negocioimpl;

import java.util.ArrayList;

import datos.IClienteDao;
import datosimpl.ClienteDaoImpl;
import entidad.Cliente;
import entidad.Usuario;
import negocio.IClienteNeg;

public class ClienteNegImpl implements IClienteNeg{

	private IClienteDao CliDao = new ClienteDaoImpl();

	@Override
	public void agregarCliente(Cliente c,String usuario,String contraseña) {
		CliDao.agregarCliente(c,usuario,contraseña);
	}

	@Override
	public int modificarCliente(Cliente c, Usuario u) {
		return CliDao.modificarCliente(c,u);
	}

	@Override
	public Cliente obtenerCliente(String DNI) {
		return CliDao.obtenerCliente(DNI);
	}

	@Override
	public ArrayList<Cliente> obtenerClientesActivos() {
		// TODO Auto-generated method stub
		return CliDao.obtenerClientesActivos();
	}

	@Override
	public ArrayList<Cliente> obtenerClientesInactivos() {
		// TODO Auto-generated method stub
		return CliDao.obtenerClientesInactivos();
	}

	@Override
	public void activarCliente(String dni) {
		// TODO Auto-generated method stub
		CliDao.activarCliente(dni);
	}

	@Override
	public void desactivarCliente(String dni) {
		// TODO Auto-generated method stub
		CliDao.desactivarCliente(dni);
	}

	@Override
	public ArrayList<Cliente> buscarClientes(String buscarCliente) {
		// TODO Auto-generated method stub
		return CliDao.buscarClientes(buscarCliente);
	}
	
}
