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

public class HelperBaseDeDatos {

//CREA LAS TABLAS SI NO EXISTEN

@SuppressWarnings("deprecation")
public void createTables(Connection conn) throws SQLException   {
	conn.setAutoCommit(false);
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

//INSERTA LOS DATOS EN LAS TABLAS

public void insertarDatos(Connection conn) throws SQLException {
	conn.setAutoCommit(false);
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
	
	} catch (IOException e) {
		e.printStackTrace();
	}	
	
	try {
		parser = CSVFormat.DEFAULT.withHeader().parse(new
				FileReader("./Recursos/productos.csv"));
		
		for(CSVRecord row: parser) {
			int idProducto = Integer.parseInt(row.get("idProducto"));
			String nombre = row.get("nombre");
			Float valor = Float.parseFloat(row.get("valor"));
			
		String insert = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?,?,?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, idProducto);
		ps.setString(2, nombre);
		ps.setFloat(3, valor);
		ps.executeUpdate();
		conn.commit();
		ps.close();
		
		}
		
	} catch (IOException e) {
		e.printStackTrace();
	}		
	
	try {
		parser = CSVFormat.DEFAULT.withHeader().parse(new
				FileReader("./Recursos/facturas.csv"));
		
		for(CSVRecord row: parser) {
			int idFactura = Integer.parseInt(row.get("idFactura"));
			int idCliente = Integer.parseInt(row.get("idCliente"));

		String insert = "INSERT INTO Factura (idFactura, idCliente) VALUES (?,?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, idFactura);
		ps.setInt(2, idCliente);
		ps.executeUpdate();
		conn.commit();
		ps.close();
		
		}

	} catch (IOException e) {
		e.printStackTrace();
	}		
	
	try {
		parser = CSVFormat.DEFAULT.withHeader().parse(new
				FileReader("./Recursos/facturas-productos.csv"));
		
		for(CSVRecord row: parser) {
			int idFactura = Integer.parseInt(row.get("idFactura"));
			int idProducto = Integer.parseInt(row.get("idProducto"));
			int cantidad = Integer.parseInt(row.get("cantidad"));

		String insert = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(insert);
			ps.setInt(1, idFactura);
			ps.setInt(2, idProducto);
			ps.setInt(3, cantidad);
			ps.executeUpdate();
			conn.commit();
			ps.close();
			
			}
		
	
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
