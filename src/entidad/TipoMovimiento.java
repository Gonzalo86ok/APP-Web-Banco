package entidad;

public class TipoMovimiento {

	private int id;
	private String descripcion;
	
	public TipoMovimiento() 
	{
		
	}	

	public TipoMovimiento(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		return "TipoMovimiento [id=" + id + ", descripcion=" + descripcion + "]";
	}

}
