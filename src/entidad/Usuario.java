package entidad;

public class Usuario {
	
	private Cliente cliente;
	private String usuario;
	private String contrase�a;
	private String tipoDeUsuario;
	private String estado;
	
	public Usuario()
	{
		this.cliente = new Cliente();
		this.usuario = "";
		this.contrase�a = "";
		this.tipoDeUsuario = "";
		this.estado = "I";
	}

	public Usuario(Cliente cliente, String usuario, String contrase�a, String tipoDeUsuario, String estado) {
		super();
		this.cliente = cliente;
		this.usuario = usuario;
		this.contrase�a = contrase�a;
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

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
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
		return "Usuario [cliente=" + cliente.getDni() + ", usuario=" + usuario + ", contrase�a=" + contrase�a
				+ ", tipoDeUsuario=" + tipoDeUsuario + ", estado=" + estado + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((contrase�a == null) ? 0 : contrase�a.hashCode());
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
		if (contrase�a == null) {
			if (other.contrase�a != null)
				return false;
		} else if (!contrase�a.equals(other.contrase�a))
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