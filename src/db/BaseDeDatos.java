package db;


import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

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
		
		String uri = "jdbc:mysql://localhost:3306/tp1";
		
		try {
			Connection conn = DriverManager.getConnection(uri, "root", "password2023!");
			conn.setAutoCommit(false);
			createTables(conn);
			insertarDatos(conn);
			//addPerson(conn, 1, "Bernardo", 32);
			//addPerson(conn, 2, "Juan", 30);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
private static void insertarDatos(Connection conn) throws SQLException {
	CSVParser parser;
	try {
		parser = CSVFormat.DEFAULT.withHeader().parse(new
				FileReader("./Recursos/clientes.csv"));
		
		for(CSVRecord row: parser) {
			int idCliente = Integer.parseInt(row.get("idCliente"));
			String nombre = row.get("nombre");
			String email = row.get("email");
		
		String insert = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?,?,?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, idCliente);
		ps.setString(2, nombre);
		ps.setString(3, email);
		ps.executeUpdate();
		conn.commit();
		ps.close();
		
		}
	conn.close();

	
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
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
	@SuppressWarnings("deprecation")
	private static void createTables(Connection conn) throws SQLException   {
		String table = "CREATE TABLE IF NOT EXISTS Cliente (" + 
				" idCliente INT NOT NULL AUTO_INCREMENT , " +
				" nombre VARCHAR(500)," + 
				" email VARCHAR(150)," +
				" PRIMARY KEY (idCliente))";
		conn.prepareStatement(table).execute();

		
		String table2 = "CREATE TABLE IF NOT EXISTS Factura (" + 
				" idFactura INT NOT NULL AUTO_INCREMENT , " +
				" idCliente INT," + 
				" PRIMARY KEY (idFactura)," +
				" CONSTRAINT FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente))";
				
		conn.prepareStatement(table2).execute();
		
		String table3 = "CREATE TABLE IF NOT EXISTS Producto (" + 
				" idProducto INT NOT NULL," +
				" nombre VARCHAR(45)," +
				" valor FLOAT," +
				" PRIMARY KEY (idProducto))";
				
		
		conn.prepareStatement(table3).execute();
		
		String table4 = "CREATE TABLE IF NOT EXISTS Factura_Producto (" + 
				" idFactura INT, " +
				" idProducto INT," +
				" cantidad INT," +
				" CONSTRAINT FOREIGN KEY (idFactura) REFERENCES Factura(idFactura)," +
				" CONSTRAINT FOREIGN KEY (idProducto) REFERENCES Producto(idProducto))";
				
		
		conn.prepareStatement(table4).execute();
		
		conn.commit();
		
		
				
		
		
		

	}

}
