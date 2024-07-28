package entidad;

import java.sql.Date;
import java.util.ArrayList;

public class Cliente  {
	
	private String dni;
    private String cuil;
    private String nombre;
    private String apellido;
    private Generos sexo;
    private String nacionalidad;
	private Date fechaNacimiento;
    private String direccion;
    private Localidad localidad;
    private String email;
    private String telefono;
    private String estado;
    private ArrayList<Cuenta> cuentas;

    
	public Cliente(String dni, String cuil, String nombre, String apellido, Generos sexo, String nacionalidad,
			Date fechaNacimiento, String direccion, Localidad localidad, String email, String telefono) {
		super();
		this.dni = dni;
		this.cuil = cuil;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.localidad = localidad;
		this.email = email;
		this.telefono = telefono;
		this.estado= "A";
	}
	
	public Cliente()
	{
		this.dni = "";
		this.cuil = "";
		this.nombre = "";
		this.apellido = "";
		this.sexo = new Generos();
		this.nacionalidad = "";
		this.fechaNacimiento = new Date(0);
		this.direccion = "";
		this.localidad = new Localidad();
		this.email = "";
		this.telefono = "";
		this.estado= "A";
		this.setCuentas(new ArrayList<Cuenta>());
	}

	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public String getCuil() {
		return cuil;
	}
	
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public Generos getSexo() {
		return sexo;
	}
	
	public void setSexo(Generos sexo) {
		this.sexo = sexo;
	}
	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public Localidad getLocalidad() {
		return localidad;
	}
	
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	

	public ArrayList<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(ArrayList<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	   @Override
		public String toString() {
			return "Cliente [dni=" + dni + ", cuil=" + cuil + ", nombre=" + nombre + ", apellido=" + apellido + ", sexo="
					+ sexo + ", nacionalidad=" + nacionalidad + ", fechaNacimiento=" + fechaNacimiento + ", direccion="
					+ direccion + ", localidad=" + localidad + ", email=" + email + ", telefono=" + telefono + ", estado="
					+ estado + ", cuentas=" + cuentas + "]";
		}
	
}