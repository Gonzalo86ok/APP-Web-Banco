package datosimpl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import datos.IGeneroDao;
import entidad.Generos;

public class GenerosDaoImpl implements IGeneroDao {
	Conexion cn = new Conexion();

	@Override
	public ArrayList<Generos> cargarGeneros() {
		ArrayList<Generos> generos = new ArrayList<>();
		String query = "SELECT * FROM generos";
		cn.Open();

		try (CallableStatement stmt = cn.prepareCall(query)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Generos genero = new Generos();
				genero.setId(rs.getInt("id"));
				genero.setDescripcion(rs.getString("descripcion"));
				generos.add(genero);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return generos;
	}

	@Override
	public Generos GetGenero(int id) {
		ArrayList<Generos> generos = this.cargarGeneros();
		
		for (Generos genero : generos) {
	        if (genero.getId() == id ) {
	            return genero;
	        }
	    }
	    return null;
	}

}
