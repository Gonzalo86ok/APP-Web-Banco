package datosimpl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import datos.IUsuarioDao;
import entidad.Cliente;
import entidad.Usuario;

public class UsuarioDaoImpl implements IUsuarioDao {

	Conexion cn = new Conexion();

	@Override
	public ArrayList<Usuario> obtenerUsuarioActivos() {
		ArrayList<Usuario> listUsuarios = new ArrayList<Usuario>();
		String query = "SELECT * FROM usuarios where estado = 'A'";
		cn.Open();
		
		try (CallableStatement stmt = cn.prepareCall(query)) {

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Usuario u = new Usuario();
				u.getCliente().setDni(rs.getString("dni_cliente"));
				u.setUsuario(rs.getString("usuario"));
				u.setContraseña(rs.getString("contraseña"));
				u.setTipoUsuario(rs.getString("tipo_usuario"));
				u.setEstado(rs.getString("estado"));
				
				listUsuarios.add(u);
			}
			
			return listUsuarios;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return new ArrayList<Usuario>();
	}

	@Override
	public ArrayList<Usuario> obtenerUsuarioInactivos() {
		ArrayList<Usuario> listUsuarios = new ArrayList<Usuario>();
		String query = "SELECT * FROM usuarios where estado = 'I'";
		cn.Open();
		
		try (CallableStatement stmt = cn.prepareCall(query)) {

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Usuario u = new Usuario();
				u.getCliente().setDni(rs.getString("dni_cliente"));
				u.setUsuario(rs.getString("usuario"));
				u.setContraseña(rs.getString("contraseña"));
				u.setTipoUsuario(rs.getString("tipo_usuario"));
				u.setEstado(rs.getString("estado"));
				
				listUsuarios.add(u);
			}
			
			return listUsuarios;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return new ArrayList<Usuario>();
	}
	
	@Override
	public Usuario loginUsuario(String nombre, String contraseña) {
		ArrayList<Usuario> Usuario = this.obtenerUsuarioActivos();

		for (Usuario usuario : Usuario) {

			if (nombre.equals(usuario.getUsuario()) && contraseña.equals(usuario.getContraseña())) {
				return usuario;
			}
		}
		return new Usuario();
	}
	
	@Override
	public void darDeBajaUsuario(String dni) {
	    String query = "{CALL BajaLogicaUsuario(?)}";
	    
	    try (CallableStatement cst = cn.prepareCall(query)) {
	        cst.setString(1, dni);
	        cst.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	}
	
	@Override
	public void activarUsuario(String dni) {
	    String query = "{CALL ActivarUsuario(?)}";
	    cn.Open();
	    try (CallableStatement cst = cn.prepareCall(query)) {
	        
	        cst.setString(1, dni);
	        cst.executeUpdate();
	        	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	}

	@Override
	public void agregarUsuario(Usuario nuevo_usuario) {
		String query = "{CALL agregarUsuario(?,?,?)}";
		
		cn.Open();
		try(CallableStatement cst = cn.prepareCall(query))
		{
			
			cst.setString(1, nuevo_usuario.getCliente().getDni());
			cst.setString(2, nuevo_usuario.getUsuario());
			cst.setString(3, nuevo_usuario.getContraseña());
	        cst.executeUpdate();
			
		}catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
		
	}

	
	@Override
	public Usuario obtenerUsuario(String dni) {
		ArrayList<Usuario> listUsuarios = this.obtenerUsuarioActivos();
		for (Usuario usuario : listUsuarios) {
			if (usuario.getCliente().getDni().contentEquals(dni)) {
				return usuario;
			}

		}
		return new Usuario();
	}
	
	
}
