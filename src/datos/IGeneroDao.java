package datos;

import java.util.ArrayList;

import entidad.Generos;

public interface IGeneroDao {
	public ArrayList<Generos> cargarGeneros();
	public Generos GetGenero(int id);
}
