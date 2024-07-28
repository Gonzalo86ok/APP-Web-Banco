package entidad;

public class Localidad {

	private int id;
	private String nombre;
	private Provincia provincia;
	
	public Localidad()
	{
		
	}
	
	public Localidad(int id, String nombre, Provincia provincia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.provincia = provincia;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Provincia getProvincia() {
		return provincia;
	}
	
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
	@Override
	public String toString() {
		return "Localidad [id=" + id + ", nombre=" + nombre + ", provincia=" + provincia + "]";
	}
	
}
