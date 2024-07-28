package negocio;
import java.util.ArrayList;

import entidad.Pais;

public interface IPaisNeg {

	ArrayList<Pais> obtenerPaises();
	public Pais GetPais(int id);
}
