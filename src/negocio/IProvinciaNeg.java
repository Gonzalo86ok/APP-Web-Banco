package negocio;
import java.util.ArrayList;
import entidad.Provincia;

public interface IProvinciaNeg {
   
	ArrayList<Provincia> obtenerProvincias();
	public Provincia GetProvincia(int id);
}
