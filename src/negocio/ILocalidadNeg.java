package negocio;

import java.util.ArrayList;

import entidad.Localidad;

public interface ILocalidadNeg {
	public ArrayList<Localidad> GetLocalidades();
	public Localidad GetLocalidad(int id);
}
