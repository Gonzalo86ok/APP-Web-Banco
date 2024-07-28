package negocio;

import java.util.ArrayList;

import entidad.Generos;

public interface IGeneroNeg {
	public ArrayList<Generos> cargarGeneros();
	public Generos GetGenero(int id);
}
