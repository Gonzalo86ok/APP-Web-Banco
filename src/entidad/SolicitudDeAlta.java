package entidad;

public class SolicitudDeAlta {
	
	private int id;
    private Cliente cliente;
    private String estado;
    private TipoCuenta tipoCuenta;
    
    public SolicitudDeAlta() {
		
	}

	public SolicitudDeAlta(int id, Cliente cliente, String estado, TipoCuenta tipoCuenta) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.estado = estado;
		this.tipoCuenta = tipoCuenta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	@Override
	public String toString() {
		return "SolicitudDeAlta [id=" + id + ", cliente=" + cliente + ", estado=" + estado + ", tipoCuenta="
				+ tipoCuenta + "]";
	}
   
}
