package entidad;

public class TipoCuenta {
	private int id;
	private String descripcion;
	
	@Override
	public String toString() {
		return "TipoCuenta [id=" + id + ", descripcion=" + descripcion + "]";
	}
	public TipoCuenta(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}
	public TipoCuenta() {
		// TODO Auto-generated constructor stub
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
