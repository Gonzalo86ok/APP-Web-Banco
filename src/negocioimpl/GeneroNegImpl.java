package negocioimpl;

import java.util.ArrayList;

import datos.IGeneroDao;
import datosimpl.GenerosDaoImpl;
import entidad.Generos;
import negocio.IGeneroNeg;

public class GeneroNegImpl implements IGeneroNeg{
	private IGeneroDao generoDao = new GenerosDaoImpl();
	public GeneroNegImpl() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public ArrayList<Generos> cargarGeneros() {
		// TODO Auto-generated method stub
		return generoDao.cargarGeneros();
	}
	@Override
	public Generos GetGenero(int id) {
		// TODO Auto-generated method stub
		return generoDao.GetGenero(id);
	}

}
