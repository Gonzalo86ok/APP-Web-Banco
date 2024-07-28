package negocioimpl;
import java.util.ArrayList;
import datos.IProvinciaDao;
import datosimpl.ProvinciaDaoImpl;
import entidad.Provincia;
import negocio.IProvinciaNeg;

public class ProvinciaNegImpl implements IProvinciaNeg {

    private IProvinciaDao provinciaDao = new ProvinciaDaoImpl();

    @Override
    public ArrayList<Provincia> obtenerProvincias() 
    {
        return provinciaDao.obtenerProvincias();
    }
    @Override
    
	public Provincia GetProvincia(int id) {
		// TODO Auto-generated method stub
		return provinciaDao.GetProvincia(id);
	}
}
