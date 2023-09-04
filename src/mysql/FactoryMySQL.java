package mysql;

import java.lang.reflect.InvocationTargetException;

//FACTORY

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import dao.ClienteDAO;

import dao.FacturaDAO;
import dao.FacturaProductoDAO;
import dao.ProductoDAO;
import entidades.Cliente;
import entidades.FacturaProducto;

public class FactoryMySQL {
	
	private Connection conn;
	private ClienteDAO cliente= null;
	private FacturaDAO factura= null;
	private ProductoDAO producto= null;
	private FacturaProductoDAO facturaProducto= null;
	
	//CREA CONECCION A MYSQL
	public static void main (String [] args) {
		String driver = "com.mysql.cj.jdbc.Driver";
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}	
		
	}
	
	public FactoryMySQL (String uri, String username, String password) throws SQLException {
		conn = DriverManager.getConnection(uri, username, password); 
		
	}
			
	
	//CREO LOS DAOS SI NO EXISTEN
	public ClienteDAO instanciarClienteDAO() {
		if (cliente == null) {
			cliente = new MySQLClienteDAO(conn);
		}
		return cliente;
	}
	
	public FacturaDAO instanciarFacturaDAO() {
		if (factura == null) {
			factura = new MySQLFacturaDAO(conn);
		}
		return factura;
	}
	
	public ProductoDAO instanciarProductoDAO() {
		if (producto == null) {
			producto = new MySQLProductoDAO(conn);
		}
		return producto;
	}
		
	public FacturaProductoDAO instanciarFacturaProductoDAO() {
		if (facturaProducto == null) {
			facturaProducto = new MySQLFacturaProductoDAO(conn);
		}
		return facturaProducto;
	}
}
