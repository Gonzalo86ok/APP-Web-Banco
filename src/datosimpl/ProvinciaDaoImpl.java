package datosimpl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import datos.IProvinciaDao;
import entidad.Pais;
import entidad.Provincia;

public class ProvinciaDaoImpl implements IProvinciaDao{

	private Conexion conexion;
	
	public ProvinciaDaoImpl() 
	{
        this.conexion = new Conexion();
    }
	
	@Override
    public ArrayList<Provincia> obtenerProvincias() 
	{
        ArrayList<Provincia> provincias = new ArrayList<>();
        String query = "SELECT provincias.*, paises.* FROM provincias JOIN paises ON provincias.id_pais = paises.id;";
        ResultSet rs = null;
        PreparedStatement ps = null;

        try
        {
        	conexion.Open();
        	
        	ps = conexion.prepareCall(query);
            rs = ps.executeQuery();
             
            while (rs.next()) 
            {
            	Pais pais = new Pais();
            	pais.setId(rs.getInt("id_pais"));
            	pais.setNombre(rs.getString("nombre"));  
            	
                Provincia provincia = new Provincia();          
                provincia.setId(rs.getInt("id"));
                provincia.setNombre(rs.getString("nombre"));
                provincia.setPais(pais);               
                provincias.add(provincia);
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally 
        {
            conexion.close();
        }

        return provincias;
    }

	@Override
	public Provincia GetProvincia(int id) {
		ArrayList<Provincia> provincias = this.obtenerProvincias();
		
		for (Provincia provincia : provincias) {
	        if (provincia.getId() == id ) {
	            return provincia;
	        }
	    }
	    return null;
	}
}
