package datos;
import java.util.ArrayList;

import entidad.Provincia;

public interface IProvinciaDao {
	ArrayList<Provincia> obtenerProvincias();
	public Provincia GetProvincia(int id);
}
