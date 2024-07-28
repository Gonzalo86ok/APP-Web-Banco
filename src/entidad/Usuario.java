package entidad;

public class Usuario {
	
	private Cliente cliente;
	private String usuario;
	private String contraseña;
	private String tipoDeUsuario;
	private String estado;
	
	public Usuario()
	{
		this.cliente = new Cliente();
		this.usuario = "";
		this.contraseña = "";
		this.tipoDeUsuario = "";
		this.estado = "I";
	}

	public Usuario(Cliente cliente, String usuario, String contraseña, String tipoDeUsuario, String estado) {
		super();
		this.cliente = cliente;
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.tipoDeUsuario = tipoDeUsuario;
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getTipoUsuario() {
		return tipoDeUsuario;
	}

	public void setTipoUsuario(String tipoDeUsuario) {
		this.tipoDeUsuario = tipoDeUsuario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Usuario [cliente=" + cliente.getDni() + ", usuario=" + usuario + ", contraseña=" + contraseña
				+ ", tipoDeUsuario=" + tipoDeUsuario + ", estado=" + estado + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((contraseña == null) ? 0 : contraseña.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((tipoDeUsuario == null) ? 0 : tipoDeUsuario.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (contraseña == null) {
			if (other.contraseña != null)
				return false;
		} else if (!contraseña.equals(other.contraseña))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (tipoDeUsuario == null) {
			if (other.tipoDeUsuario != null)
				return false;
		} else if (!tipoDeUsuario.equals(other.tipoDeUsuario))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
}