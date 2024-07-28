package datos;

import java.util.ArrayList;

import entidad.Localidad;

public interface ILocalidadDao {
	public ArrayList<Localidad> GetLocalidades();
	public Localidad GetLocalidad(int id);
}
