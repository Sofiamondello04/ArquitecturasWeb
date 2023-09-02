package mysql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import dao.ClienteDAO;

import dao.FacturaDAO;
import dao.ProductoDAO;
import entidades.Cliente;

public class MySQLDaoManager {
	
	//CREO CONECCION A MYSQL
	
	private Connection conn;
	/*private String driver = "com.mysql.cj.jdbc.Driver";
	
	// NO ESTOY USANDO EL DRIVER: VER
	
	private String uri = "jdbc:mysql://localhost:3306/tp1-ArqWeb";*/
	
	private ClienteDAO cliente= null;
	private FacturaDAO factura= null;
	private ProductoDAO producto= null;
	
	public MySQLDaoManager (String uri, String username, String password) throws SQLException {
		conn = DriverManager.getConnection(uri, username, password); 
		
	}
	
	
	//CREO LOS DAOS
	


	
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
		// TODO Auto-generated method stub
		return null;
	}
			
	
	//PARA PROBARLO
	
	

}
