package datosimpl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import datos.IClienteDao;
import entidad.Cliente;
import entidad.Generos;
import entidad.Localidad;
import entidad.Usuario;

public class ClienteDaoImpl implements IClienteDao {

	Conexion cn = new Conexion();

	// MODIFICADO
	@Override
	public Cliente obtenerCliente(String dni) {
		ArrayList<Cliente> listClientes = this.obtenerClientesActivos();
		for (Cliente cliente : listClientes) {
			if (cliente.getDni().contentEquals(dni)) {
				return cliente;
			}

		}
		return new Cliente();
	}

	@Override
	public void agregarCliente(Cliente c, String usuario, String contraseña) {

		String query = "{CALL crearCliente(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		cn.Open();

		try (CallableStatement cst = cn.prepareCall(query)) {

			cst.setString(1, c.getDni());
			cst.setString(2, c.getCuil());
			cst.setString(3, c.getNombre());
			cst.setString(4, c.getApellido());
			cst.setInt(5, c.getSexo().getId());
			cst.setString(6, c.getNacionalidad());
			cst.setDate(7, new java.sql.Date(c.getFechaNacimiento().getTime()));
			cst.setString(8, c.getDireccion());
			cst.setInt(9, c.getLocalidad().getId());
			cst.setString(10, c.getEmail());
			cst.setString(11, c.getTelefono());
			// DATOS PARA EL NUEVO USUARIO
			cst.setString(12, usuario);
			cst.setString(13, contraseña);

			cst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
	}

	@Override
	public ArrayList<Cliente> obtenerClientesActivos() {

		ArrayList<Cliente> listaClientes = new ArrayList<>();

		String query = "SELECT * FROM clientes where estado = 'A'";

		cn.Open();
		try (CallableStatement cs = cn.prepareCall(query); ResultSet rs = cs.executeQuery()) {
			GenerosDaoImpl generoDaoImpl = new GenerosDaoImpl();
			ArrayList<Generos> listaGeneros = generoDaoImpl.cargarGeneros();
			LocalidadDaoImpl localidadDaoImpl = new LocalidadDaoImpl();
			ArrayList<Localidad> listaLocalidades = localidadDaoImpl.GetLocalidades();

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setDni(rs.getString("dni"));
				cliente.setCuil(rs.getString("cuil"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellido(rs.getString("apellido"));

				for (Generos genero : listaGeneros) {
					if (genero.getId() == rs.getInt("sexo_id")) {
						cliente.setSexo(genero);
					}
				}

				cliente.setNacionalidad(rs.getString("nacionalidad"));

				for (Localidad localidad : listaLocalidades) {
					if (localidad.getId() == rs.getInt("localidad_id")) {
						cliente.setLocalidad(localidad);
					}
				}

				cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
				cliente.setDireccion(rs.getString("direccion"));

				cliente.setEmail(rs.getString("mail"));
				cliente.setTelefono(rs.getString("telefono"));

				listaClientes.add(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return listaClientes;
	}

	@Override
	public ArrayList<Cliente> obtenerClientesInactivos() {
		ArrayList<Cliente> listaClientes = new ArrayList<>();

		String query = "SELECT * FROM clientes where estado = 'I'";
		cn.Open();
		try (CallableStatement cs = cn.prepareCall(query); ResultSet rs = cs.executeQuery()) {
			GenerosDaoImpl generoDaoImpl = new GenerosDaoImpl();
			ArrayList<Generos> listaGeneros = generoDaoImpl.cargarGeneros();
			LocalidadDaoImpl localidadDaoImpl = new LocalidadDaoImpl();
			ArrayList<Localidad> listaLocalidades = localidadDaoImpl.GetLocalidades();

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setDni(rs.getString("dni"));
				cliente.setCuil(rs.getString("cuil"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellido(rs.getString("apellido"));

				for (Generos genero : listaGeneros) {
					if (genero.getId() == rs.getInt("sexo_id")) {
						cliente.setSexo(genero);
					}
				}

				cliente.setNacionalidad(rs.getString("nacionalidad"));

				for (Localidad localidad : listaLocalidades) {
					if (localidad.getId() == rs.getInt("localidad_id")) {
						cliente.setLocalidad(localidad);
					}
				}

				cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
				cliente.setDireccion(rs.getString("direccion"));

				cliente.setEmail(rs.getString("mail"));
				cliente.setTelefono(rs.getString("telefono"));

				listaClientes.add(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return listaClientes;
	}

	@Override
	public int modificarCliente(Cliente c,Usuario u) {
		int filas = 0;
		String query = "{CALL ModificarCliente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

		cn.Open();
		try (CallableStatement cst = cn.prepareCall(query)) {

			cst.setString(1, c.getDni());
			cst.setString(2, c.getCuil());
			cst.setString(3, c.getNombre());
			cst.setString(4, c.getApellido());
			cst.setInt(5, c.getSexo().getId());
			cst.setString(6, c.getNacionalidad());
			cst.setDate(7, c.getFechaNacimiento());
			cst.setString(8, c.getDireccion());
			cst.setInt(9, c.getLocalidad().getId());
			cst.setString(10, c.getEmail());
			cst.setString(11, c.getTelefono());
			cst.setString(12, u.getContraseña());
			

			filas = cst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return filas;
	}

	@Override
	public void activarCliente(String dni) {
		cn.Open();

		try {
			String query = "{CALL ModificarEstadoCliente(?,?)}";
			CallableStatement cs = cn.prepareCall(query);

			cs.setString(1, dni);
			cs.setString(2, "A");

			cs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
	}

	@Override
	public void desactivarCliente(String dni) {
		cn.Open();

		try {
			String query = "{CALL ModificarEstadoCliente(?,?)}";
			CallableStatement cs = cn.prepareCall(query);

			cs.setString(1, dni);
			cs.setString(2, "I");

			cs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
	}

	@Override
    public ArrayList<Cliente> buscarClientes(String buscarCliente) {
		
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        GenerosDaoImpl generoDaoImpl = new GenerosDaoImpl();
        LocalidadDaoImpl localidadDaoImpl = new LocalidadDaoImpl();

        // Cargar los generos y localidades
        ArrayList<Generos> listaGeneros = generoDaoImpl.cargarGeneros();
        ArrayList<Localidad> listaLocalidades = localidadDaoImpl.GetLocalidades();
    
        String query = "SELECT * FROM Clientes WHERE nombre LIKE ? OR apellido LIKE ? OR dni LIKE ?";

        try 
        {
            cn.Open(); // Asegúrate de que `cn` es una instancia válida y está conectada
            try (PreparedStatement ps = (PreparedStatement) cn.prepareStatement(query)) 
            {
                String likeQuery = "%" + buscarCliente + "%";
                ps.setString(1, likeQuery);
                ps.setString(2, likeQuery);
                ps.setString(3, likeQuery);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Cliente cliente = new Cliente();
                        cliente.setDni(rs.getString("dni"));
                        cliente.setCuil(rs.getString("cuil"));
                        cliente.setNombre(rs.getString("nombre"));
                        cliente.setApellido(rs.getString("apellido"));

                        // Buscar el genero correspondiente
                        Generos genero = null;
                        int sexoId = rs.getInt("sexo_id");
                        for (Generos g : listaGeneros) {
                            if (g.getId() == sexoId) {
                                genero = g;
                                break;
                            }
                        }
                        cliente.setSexo(genero);

                        cliente.setNacionalidad(rs.getString("nacionalidad"));

                        // Buscar la localidad correspondiente
                        Localidad localidad = null;
                        int localidadId = rs.getInt("localidad_id");
                        for (Localidad l : listaLocalidades) {
                            if (l.getId() == localidadId) {
                                localidad = l;
                                break;
                            }
                        }
                        cliente.setLocalidad(localidad);

                        cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                        cliente.setDireccion(rs.getString("direccion"));
                        cliente.setEmail(rs.getString("mail"));
                        cliente.setTelefono(rs.getString("telefono"));
                        cliente.setEstado(rs.getString("estado"));

                        listaClientes.add(cliente);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Manejo de errores adicionales si es necesario
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de errores adicionales si es necesario
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return listaClientes;
    }






	public ArrayList<Cliente> obtenerTodosLosClientes() {
		ArrayList<Cliente> listaClientes = new ArrayList<>();
		GenerosDaoImpl generoDaoImpl = new GenerosDaoImpl();
		ArrayList<Generos> listaGeneros = generoDaoImpl.cargarGeneros();
		LocalidadDaoImpl localidadDaoImpl = new LocalidadDaoImpl();
		ArrayList<Localidad> listaLocalidades = localidadDaoImpl.GetLocalidades();

		String sql = "SELECT * FROM clientes";

		cn.Open();
		try {

			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setDni(rs.getString("dni"));
				cliente.setCuil(rs.getString("cuil"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellido(rs.getString("apellido"));

				for (Generos genero : listaGeneros) {
					if (genero.getId() == rs.getInt("sexo_id")) {
						cliente.setSexo(genero);
					}
				}

				cliente.setNacionalidad(rs.getString("nacionalidad"));

				for (Localidad localidad : listaLocalidades) {
					if (localidad.getId() == rs.getInt("localidad_id")) {
						cliente.setLocalidad(localidad);
					}
				}

				cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
				cliente.setDireccion(rs.getString("direccion"));

				cliente.setEmail(rs.getString("mail"));
				cliente.setTelefono(rs.getString("telefono"));
				cliente.setEstado(rs.getString("estado"));
				listaClientes.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}

		return listaClientes;
	}
}