package negocioimpl;

import java.util.ArrayList;

import datos.ICuentaDAO;
import datosimpl.CuentaDaoImpl;
import entidad.Cuenta;
import negocio.ICuentaNeg;

public class CuentaNegImpl implements ICuentaNeg{

		private ICuentaDAO CuentaDao = new CuentaDaoImpl();

		@Override
		public ArrayList<Cuenta> obtenerCuentasActivasPorCliente(String dniCliente) {
			return CuentaDao.obtenerCuentasActivasPorCliente(dniCliente);
		}

		@Override
		public void agregarCuenta(Cuenta c) {
			// TODO Auto-generated method stub
			CuentaDao.agregarCuenta(c);
		}

		@Override
		public String ObtenerUltimoNumCuenta() {
			// TODO Auto-generated method stub
			return CuentaDao.ObtenerUltimoNumCuenta();
		}

		@Override
		public int modificarSaldoCuenta(Cuenta c) {
			// TODO Auto-generated method stub
			return CuentaDao.modificarSaldoCuenta(c);
		}

		@Override
		public void eliminarCuenta(int numCuenta) {
			// TODO Auto-generated method stub
			CuentaDao.eliminarCuenta(numCuenta);
		}

		@Override
		public void activarCuenta(String dni) {
			// TODO Auto-generated method stub
			CuentaDao.activarCuenta(dni);
		}

		@Override
		public ArrayList<Cuenta> obtenerCuentasActivas() {
			// TODO Auto-generated method stub
			return CuentaDao.obtenerCuentasActivas();
		}

		@Override
		public ArrayList<Cuenta> obtenerCuentasInactivas() {
			// TODO Auto-generated method stub
			return CuentaDao.obtenerCuentasInactivas();
		}

		@Override
		public ArrayList<Cuenta> obtenerCuentasInactivasPorCliente(String dniCliente) {
			// TODO Auto-generated method stub
			return CuentaDao.obtenerCuentasInactivasPorCliente(dniCliente);
		}

		@Override
		public Cuenta obtenerCuentaPorTipo(String dniCliente, int id_tipoCuenta) {
			// TODO Auto-generated method stub
			return CuentaDao.obtenerCuentaPorTipo(dniCliente, id_tipoCuenta);
		}

		@Override
		public Cuenta buscarPorCBU(int cbu) {
			// TODO Auto-generated method stub
			return CuentaDao.buscarPorCBU(cbu);
		}
		public int obtenerUltimoCBU()
		{
			return CuentaDao.obtenerUltimoCBU();
		}

		@Override
		public float GetPromedioSaldos() {
			// TODO Auto-generated method stub
			return CuentaDao.GetPromedioSaldos();
		}

		@Override
		public float GetPorcentajeCuentasActivas() {
			// TODO Auto-generated method stub
			return CuentaDao.GetPorcentajeCuentasActivas();
		}

		@Override
		public float GetPorcentajeCuentasInactivas() {
			// TODO Auto-generated method stub
			return CuentaDao.GetPorcentajeCuentasInactivas();
		}

		@Override
		public ArrayList<Cuenta> obtenerCuentas() {
			// TODO Auto-generated method stub
			return CuentaDao.obtenerCuentas();
		}
}
