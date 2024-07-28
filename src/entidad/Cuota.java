package entidad;

import java.sql.Date;

public class Cuota {

	private int id;
	private Prestamo prestamo;
	private int numeroCuota;
	private Date fechaPago; 
	private double monto;
	private String estado;
	
	public Cuota()
	{
		
	}

	public Cuota(int id, Prestamo prestamo, int numeroCuota, Date fechaPago, double monto, String estado) {
		super();
		this.id = id;
		this.prestamo = prestamo;
		this.numeroCuota = numeroCuota;
		this.fechaPago = fechaPago;
		this.monto = monto;
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	public int getNumeroCuota() {
		return numeroCuota;
	}

	public void setNumeroCuota(int numeroCuota) {
		this.numeroCuota = numeroCuota;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cuota [id=" + id + ", prestamo=" + prestamo + ", numeroCuota=" + numeroCuota + ", fechaPago="
				+ fechaPago + ", monto=" + monto + ", estado=" + estado + "]";
	}
	
}
