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
import db.HelperBaseDeDatos;
import entidades.Cliente;
import entidades.FacturaProducto;

public class FactoryMySQL {
	
	private Connection conn;
	private ClienteDAO cliente= null;
	private FacturaDAO factura= null;
	private ProductoDAO producto= null;
	private FacturaProductoDAO facturaProducto= null;
	private static final String uri = "jdbc:mysql://localhost:3306/tp1-ArqWeb";
	private static final String username = "root";
	private static final String password ="";
	
	
	
	public FactoryMySQL () throws SQLException {
		String driver = "com.mysql.cj.jdbc.Driver";
		
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		conn = DriverManager.getConnection(uri, username, password);
	}	
	
	public void cerrarConeccion () throws SQLException {
		
		this.conn.close();
	}
	
	public void crearTablasConDatos() throws SQLException {
		HelperBaseDeDatos helper = new HelperBaseDeDatos();
		helper.createTables(conn);
		helper.insertarDatos(conn);
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
