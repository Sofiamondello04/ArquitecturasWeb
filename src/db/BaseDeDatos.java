package db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseDeDatos {

	public static void main(String[] args) {
		String driver = "com.mysql.cj.jdbc.Driver";
		
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		String uri = "jdbc:mysql://localhost:3306/TP1-ArqWeb";
		
		try {
			Connection conn = DriverManager.getConnection(uri, "root", "");
			conn.setAutoCommit(false);
			createTables(conn);
			//addPerson(conn, 1, "Bernardo", 32);
			//addPerson(conn, 2, "Juan", 30);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
/*
	private static void addPerson(Connection conn, int id, String name, int years) throws SQLException {
		String insert = "INSERT INTO persona (id, nombre, edad) VALUES (?,?,?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, id);
		ps.setString(2, name);
		ps.setInt(3, years);
		ps.executeUpdate();
		ps.close();
		conn.commit();
	}
*/
	private static void createTables(Connection conn) throws SQLException   {
		String table = "CREATE TABLE Clientes (" + 
				" id INT NOT NULL AUTO_INCREMENT , " +
				" nombre VARCHAR(500)," + 
				" email VARCHAR(150)," +
				" PRIMARY KEY (id))";
		conn.prepareStatement(table).execute();

		
		String table2 = "CREATE TABLE Facturas (" + 
				" idFactura INT NOT NULL AUTO_INCREMENT , " +
				" idCliente INT NOT NULL," + 
				" PRIMARY KEY (idFactura))";
				// falta clave foranea
		
		conn.prepareStatement(table2).execute();
		
		String table3 = "CREATE TABLE Factura_Productos (" + 
				" idFactura INT NOT NULL, " +
				" idProducto INT," +
				" cantidad INT)";
				// faltan claves foraneas
		
		conn.prepareStatement(table3).execute();
		
		String table4 = "CREATE TABLE Productos (" + 
				" id INT NOT NULL," +
				" nombre VARCHAR(45)," +
				" valor FLOAT," +
				" PRIMARY KEY (id))";
				// faltan claves foraneas
		
		conn.prepareStatement(table4).execute();
		conn.commit();

	}

}
