package entidad;

import java.util.Date;

public class Movimiento {
    private int id;
    private Cuenta cuenta;
    private Date fecha;
    private String detalle;
    private double importe;
    private TipoMovimiento tipoMovimiento;
    private int cuentaOrigen;
    private int cuentaDestino;

    // Constructor vacío
    public Movimiento() 
    {
    	
    }
    
    public Movimiento(int id, Cuenta cuenta, Date fecha, String detalle, double importe, TipoMovimiento tipoMovimiento,
			int cuentaOrigen, int cuentaDestino) {
		super();
		this.id = id;
		this.cuenta = cuenta;
		this.fecha = fecha;
		this.detalle = detalle;
		this.importe = importe;
		this.tipoMovimiento = tipoMovimiento;
		this.cuentaOrigen = cuentaOrigen;
		this.cuentaDestino = cuentaDestino;
	}
	
    public int getId() {
		return id;
    }
	
    public void setId(int id) {
		this.id = id;
	}
	
    public Cuenta getCuenta() {
		return cuenta;
	}

    public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
    public Date getFecha() {
		return fecha;
	}
	
    public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
    public String getDetalle() {
		return detalle;
	}

    public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
    public double getImporte() {
		return importe;
	}
	
    public void setImporte(double importe) {
		this.importe = importe;
	}

    public TipoMovimiento getTipoMovimiento() {
		return tipoMovimiento;
	}
	
    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	
    public int getCuentaOrigen() {
		return cuentaOrigen;
	}
	
    public void setCuentaOrigen(int cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

    public int getCuentaDestino() {
		return cuentaDestino;
	}

    public void setCuentaDestino(int cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

    @Override
	public String toString() {
		return "Movimiento [id=" + id + ", cuenta=" + cuenta + ", fecha=" + fecha + ", detalle=" + detalle
				+ ", importe=" + importe + ", tipoMovimiento=" + tipoMovimiento + ", cuentaOrigen=" + cuentaOrigen
				+ ", cuentaDestino=" + cuentaDestino + "]";
	}
    
}