package entidad;

import java.sql.Date;

public class Cuenta {
    private int numeroDeCuenta;
    private String dni;
    private Date fechaDeCreacion;
    private  TipoCuenta tipoCuenta;
    private int cbu;
    private double saldo;
    private String estado;
    
	public Cuenta(String dni)
    {
		this.dni = dni;
    }

	public Cuenta(int numeroDeCuenta, String dni, Date fechaDeCreacion, TipoCuenta tipoCuenta, int cbu, double saldo,
			String estado) {
		super();
		this.numeroDeCuenta = numeroDeCuenta;
		this.dni = dni;
		this.fechaDeCreacion = fechaDeCreacion;
		this.tipoCuenta = tipoCuenta;
		this.cbu = cbu;
		this.saldo = saldo;
		this.estado = estado;
	}
	
	public Cuenta() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Cuenta [numeroDeCuenta=" + numeroDeCuenta + ", dni=" + dni + ", fechaDeCreacion=" + fechaDeCreacion
				+ ", tipoCuenta=" + tipoCuenta + ", cbu=" + cbu + ", saldo=" + saldo + ", estado=" + estado + "]";
	}

	public int getNumeroDeCuenta() {
		return numeroDeCuenta;
	}

	public void setNumeroDeCuenta(int numeroDeCuenta) {
		this.numeroDeCuenta = numeroDeCuenta;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getFechaDeCreacion() {
		return fechaDeCreacion;
	}

	public void setFechaDeCreacion(Date fechaDeCreacion) {
		this.fechaDeCreacion = fechaDeCreacion;
	}

	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public int getCbu() {
		return cbu;
	}

	public void setCbu(int cbu) {
		this.cbu = cbu;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}