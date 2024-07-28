package datosimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import datos.ILocalidadDao;
import entidad.Localidad;
import entidad.Pais;
import entidad.Provincia;
public class LocalidadDaoImpl implements ILocalidadDao{
	
	private Conexion conexion;
	
	public LocalidadDaoImpl() 
    {
        this.conexion = new Conexion();
    }
	
	@Override
	public ArrayList<Localidad> GetLocalidades() {
		
		ArrayList<Localidad> localidades = new ArrayList<>();
	    String query = "SELECT  Paises.id AS pais_id, Paises.nombre AS pais_nombre, Provincias.id AS provincia_id,Provincias.nombre AS provincia_nombre, Provincias.id_pais,Provincias.id_pais,Localidades.id AS localidad_id,Localidades.nombre AS localidad_nombre, Localidades.id_provincia FROM Localidades JOIN Provincias ON Localidades.id_provincia = Provincias.id JOIN Paises ON Provincias.id_pais = Paises.id";                                                                                                
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
	        	 pais.setId(rs.getInt("pais_id"));
	        	 pais.setNombre(rs.getString("pais_nombre"));
	        	 
	        	 Provincia provincia = new Provincia();
	        	 provincia.setId(rs.getInt("provincia_id"));
	        	 provincia.setNombre(rs.getString("provincia_nombre"));
	        	 provincia.setPais(pais);
	        	 
	        	 Localidad localidad = new Localidad();
	             
	             localidad.setId(rs.getInt("localidad_id"));
	             localidad.setNombre(rs.getString("localidad_nombre"));
	             localidad.setProvincia(provincia);	            
	             localidades.add(localidad);
	        }
	        
	        return localidades;
	    }
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	    }
	    finally
	    {
	        conexion.close();
	    }     
		
		return null;
	}
	
	@Override
	public Localidad GetLocalidad(int id) {
		ArrayList<Localidad> localidades = this.GetLocalidades();
		
		for (Localidad localidad : localidades) {
	        if (localidad.getId() == id ) {
	            return localidad;
	        }
	    }
	    return null;
	}
}
