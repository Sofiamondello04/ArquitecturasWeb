import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import entidades.Cliente;
import dao.ClienteDAO;
import mysql.MySQLClienteDAO;

public class Main {

	public static void main(String[] args) {
		
		String driver = "com.mysql.cj.jdbc.Driver";
		
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
		} 
		
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		String uri = "jdbc:mysql://localhost:3306/tp1-ArqWeb";
		
		try {
			Connection conn = DriverManager.getConnection(uri, "root", "");
			conn.setAutoCommit(false);
			createTables(conn);
			/*insertarDatos(conn);*/
			ClienteDAO dao = new MySQLClienteDAO(conn);
			List<Cliente> clientes = dao.obtenerTodos();
			for (Cliente c: clientes) {
				System.out.println(c.toString());
				
			}
			
			conn.close();
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		

		
	}	
	

	private static void createTables(Connection conn) throws SQLException   {
			
			String table = "CREATE TABLE IF NOT EXISTS Cliente (" + 
					" idCliente INT NOT NULL AUTO_INCREMENT , " +
					" nombre VARCHAR(500)," + 
					" email VARCHAR(150)," +
					" PRIMARY KEY (idCliente))";
			conn.prepareStatement(table).execute();

			
			
			conn.commit();
		}
		
		
	
}
	
		
	
		
		
		
		

