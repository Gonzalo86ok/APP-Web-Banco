package entidad;

import java.sql.Date;

public class PagoDeCuota {
	
	private int id;
    private int idPrestamo;
    private Date fechaDePago;
    private int NumeroDeCuotas;
    
    
	public PagoDeCuota(int id, int idPrestamo, Date fechaDePago, int numeroDeCuotas) {
		super();
		this.id = id;
		this.idPrestamo = idPrestamo;
		this.fechaDePago = fechaDePago;
		NumeroDeCuotas = numeroDeCuotas;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdPrestamo() {
		return idPrestamo;
	}
	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	public Date getFechaDePago() {
		return fechaDePago;
	}
	public void setFechaDePago(Date fechaDePago) {
		this.fechaDePago = fechaDePago;
	}
	public int getNumeroDeCuotas() {
		return NumeroDeCuotas;
	}
	public void setNumeroDeCuotas(int numeroDeCuotas) {
		NumeroDeCuotas = numeroDeCuotas;
	}

	@Override
	public String toString() {
		return "PagoDeCuota [id=" + id + ", idPrestamo=" + idPrestamo + ", fechaDePago=" + fechaDePago
				+ ", NumeroDeCuotas=" + NumeroDeCuotas + "]";
	}
    
}
