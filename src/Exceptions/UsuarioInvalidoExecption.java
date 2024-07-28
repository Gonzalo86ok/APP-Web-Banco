package Exceptions;

public class UsuarioInvalidoExecption extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioInvalidoExecption() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Usuario invalido, por favor introduzca datos validos";
	}
	

}
