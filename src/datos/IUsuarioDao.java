package datos;

import java.util.ArrayList;

import entidad.Usuario;

public interface IUsuarioDao {

	public ArrayList<Usuario> obtenerUsuarioActivos();
	public ArrayList<Usuario> obtenerUsuarioInactivos();
	public Usuario loginUsuario(String nombre, String contraseña);
	public void darDeBajaUsuario(String dni);
	public void activarUsuario(String dni);
	public void agregarUsuario(Usuario nuevo_usuario);
	public Usuario obtenerUsuario(String dni);
}
