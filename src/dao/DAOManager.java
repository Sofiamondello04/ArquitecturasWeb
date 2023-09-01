package dao;

public interface DAOManager {
	
	/*Me permite acceder a los distintos DAOs de mi aplicacion*/
	
	ClienteDAO obtenerClienteDAO();
	FacturaDAO obtenerFacturaDAO();
	ProductoDAO obtenerProductoDAO();
	

}
