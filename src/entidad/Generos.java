package entidad;

public class Generos {

	
	private int id;
	private String descripcion;
	
	
	@Override
	public String toString() {
		return "Generos [id=" + id + ", descripcion=" + descripcion + "]";
	}
	public Generos(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}
	public Generos() {
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
	public static Generos valueOf(String upperCase) {
		// TODO Auto-generated method stub
		return null;
	}

}
