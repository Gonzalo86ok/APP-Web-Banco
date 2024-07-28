package datosimpl;

import datos.IPaisDao;
import entidad.Pais;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaisDaoImpl implements IPaisDao {
	
    private Conexion conexion;

    public PaisDaoImpl() 
    {
        this.conexion = new Conexion();
    }

    @Override
    public ArrayList<Pais> obtenerPaises() 
    {
        ArrayList<Pais> paises = new ArrayList<>();
        String query = "SELECT * FROM paises";
        ResultSet rs = null;
        PreparedStatement ps = null;

        try 
        {
            conexion.Open();

            ps = conexion.prepareCall(query);
            rs = ps.executeQuery();

            while (rs.next()) 
            {
                int idPais = rs.getInt("id");
                String nombre = rs.getString("nombre");
                Pais pais = new Pais(idPais, nombre);
                paises.add(pais);
            }
        }
        catch (SQLException e) 
        {
        	e.printStackTrace();
		}
        finally 
        {
			conexion.close();			
			
		}
        return paises;
    }

	@Override
	public Pais GetPais(int id) {
		ArrayList<Pais> paises = this.obtenerPaises();
		
		for (Pais pais : paises) {
	        if (pais.getId() == id ) {
	            return pais;
	        }
	    }
	    return null;
	}
}
