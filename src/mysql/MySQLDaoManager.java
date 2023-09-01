package mysql;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import dao.ClienteDAO;
import dao.DAOManager;
import dao.FacturaDAO;
import dao.ProductoDAO;
import entidades.Cliente;

public class MySQLDaoManager implements DAOManager {
	
	private Connection conn;
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String uri = "jdbc:mysql://localhost:3306/tp1-ArqWeb";
	
	private ClienteDAO cliente= null;
	private FacturaDAO factura= null;
	private ProductoDAO producto= null;
	
	public MySQLDaoManager (String uri, String username, String password) throws SQLException {
		conn = DriverManager.getConnection(uri, username, password); 
		
	}
	
	


	@Override
	public ClienteDAO obtenerClienteDAO() {
		if (cliente == null) {
			cliente = new MySQLClienteDAO(conn);
		}
		return cliente;
	}
	@Override
	public FacturaDAO obtenerFacturaDAO() {
		// TODO Auto-generated method stub
				return null;
	}
	@Override
	public ProductoDAO obtenerProductoDAO() {
		// TODO Auto-generated method stub
		return null;
	}
			
	
	//PARA PROBARLO
	
	public static void main (String [] args) throws SQLException {
		MySQLDaoManager man = new MySQLDaoManager ("jdbc:mysql://localhost:3306/tp1-ArqWeb", "root", "");
		List<Cliente> clientes = man.obtenerClienteDAO().obtenerTodos();
		for (Cliente c: clientes) {
			System.out.println(c.toString());
		}
		
		
	}

}
