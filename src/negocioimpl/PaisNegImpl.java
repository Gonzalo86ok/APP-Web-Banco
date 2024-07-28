package negocioimpl;
import java.util.ArrayList;

import datos.IPaisDao;
import datosimpl.PaisDaoImpl;
import entidad.Pais;
import negocio.IPaisNeg;

public class PaisNegImpl implements IPaisNeg{
	
	private IPaisDao paisDao = new PaisDaoImpl();
	
	@Override
	 public ArrayList<Pais> obtenerPaises()
	{
		return paisDao.obtenerPaises();
	}

	@Override
	public Pais GetPais(int id) {
		// TODO Auto-generated method stub
		return paisDao.GetPais(id);
	}
	

}
