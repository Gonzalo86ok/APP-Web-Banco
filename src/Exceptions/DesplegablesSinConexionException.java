package Exceptions;

public class DesplegablesSinConexionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DesplegablesSinConexionException() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "No se pudo cargar los desplegables";
	}

}
