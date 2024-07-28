package negocioimpl;

import java.util.ArrayList;

import datos.IUsuarioDao;
import datosimpl.UsuarioDaoImpl;
import entidad.Usuario;
import negocio.IUsuarioNeg;

public class UsuarioNegImpl implements IUsuarioNeg{

	private IUsuarioDao UsuarioDao = new UsuarioDaoImpl();

	@Override
	public ArrayList<Usuario> obtenerUsuarioActivos() {
		// TODO Auto-generated method stub
		return UsuarioDao.obtenerUsuarioActivos();
	}

	@Override
	public Usuario loginUsuario(String nombre, String contraseña) {
		// TODO Auto-generated method stub
		return UsuarioDao.loginUsuario(nombre, contraseña);
	}

	@Override
	public ArrayList<Usuario> obtenerUsuarioInactivos() {
		// TODO Auto-generated method stub
		return UsuarioDao.obtenerUsuarioInactivos();
	}

	@Override
	public void darDeBajaUsuario(String dni) {
		// TODO Auto-generated method stub
		UsuarioDao.darDeBajaUsuario(dni);
	}

	@Override
	public void activarUsuario(String dni) {
		// TODO Auto-generated method stub
		UsuarioDao.activarUsuario(dni);
	}

	@Override
	public void agregarUsuario(Usuario nuevo_usuario) {
		// TODO Auto-generated method stub
		UsuarioDao.agregarUsuario(nuevo_usuario);
	}

	@Override
	public Usuario obtenerUsuario(String dni) {
		// TODO Auto-generated method stub
		return UsuarioDao.obtenerUsuario(dni);
	}
	
	
}
