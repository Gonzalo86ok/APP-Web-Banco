package negocioimpl;

import java.util.ArrayList;

import datos.ILocalidadDao;
import datosimpl.LocalidadDaoImpl;
import entidad.Localidad;
import negocio.ILocalidadNeg;

public class LocalidadNegImpl implements ILocalidadNeg{

	private ILocalidadDao localidadDao = new LocalidadDaoImpl();
	
	@Override
	public ArrayList<Localidad> GetLocalidades() {
		// TODO Auto-generated method stub
		return localidadDao.GetLocalidades();
	}

	@Override
	public Localidad GetLocalidad(int id) {
		// TODO Auto-generated method stub
		return localidadDao.GetLocalidad(id);
	}


}
