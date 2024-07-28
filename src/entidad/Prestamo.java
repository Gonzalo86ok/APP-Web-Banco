package entidad;
import java.sql.Date;


public class Prestamo {
    private int id;
    private Cliente cliente;
    private Cuenta cuenta;
    private Date fecha;
    private double importeConIntereses;
    private double importePedido;
    private int plazoDePagoEnMeses;
    private double montoMensual;
    private int cuotasPagadas;
    private String estado;
    private String prestamoSaldado;

    // Constructor vacío
    public Prestamo() {
    }

	public Prestamo(int id, Cliente cliente, Date fecha, double importeConIntereses, double importePedido,
			int plazoDePagoEnMeses, double montoMensual, int cuotasPagadas, String estado, String prestamoSaldado) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.fecha = fecha;
		this.importeConIntereses = importeConIntereses;
		this.importePedido = importePedido;
		this.plazoDePagoEnMeses = plazoDePagoEnMeses;
		this.montoMensual = montoMensual;
		this.cuotasPagadas = cuotasPagadas;
		this.estado = estado;
		this.prestamoSaldado = prestamoSaldado;
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

	public double getImporteConIntereses() {
		return importeConIntereses;
	}

	public void setImporteConIntereses(double importeConIntereses) {
		this.importeConIntereses = importeConIntereses;
	}

	public double getImportePedido() {
		return importePedido;
	}

	public void setImportePedido(double importePedido) {
		this.importePedido = importePedido;
	}

	public int getPlazoDePagoEnMeses() {
		return plazoDePagoEnMeses;
	}

	public void setPlazoDePagoEnMeses(int plazoDePagoEnMeses) {
		this.plazoDePagoEnMeses = plazoDePagoEnMeses;
	}

	public double getMontoMensual() {
		return montoMensual;
	}

	public void setMontoMensual(double montoMensual) {
		this.montoMensual = montoMensual;
	}

	public int getCuotasPagadas() {
		return cuotasPagadas;
	}

	public void setCuotasPagadas(int cuotasPagadas) {
		this.cuotasPagadas = cuotasPagadas;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPrestamoSaldado() {
		return prestamoSaldado;
	}

	public void setPrestamoSaldado(String prestamoSaldado) {
		this.prestamoSaldado = prestamoSaldado;
	}

	@Override
	public String toString() {
		return "Prestamo [id=" + id + ", cliente=" + cliente + ", cuenta=" + cuenta + ", fecha=" + fecha
				+ ", importeConIntereses=" + importeConIntereses + ", importePedido=" + importePedido
				+ ", plazoDePagoEnMeses=" + plazoDePagoEnMeses + ", montoMensual=" + montoMensual + ", cuotasPagadas="
				+ cuotasPagadas + ", estado=" + estado + ", prestamoSaldado=" + prestamoSaldado + "]";
	}
}
