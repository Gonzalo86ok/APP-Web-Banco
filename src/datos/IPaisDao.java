package datos;
import java.util.ArrayList;
import entidad.Pais;

public interface IPaisDao {

	public ArrayList<Pais> obtenerPaises();
	public Pais GetPais(int id);
}
