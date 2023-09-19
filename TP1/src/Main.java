
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import db.HelperBaseDeDatos;
import entidades.Cliente;
import entidades.Factura;
import entidades.FacturaProducto;
import entidades.Producto;
import mysql.FactoryMySQL;


public class Main {
	
	

	public static void main (String [] args) throws SQLException {
		
		
		FactoryMySQL factory = new FactoryMySQL ();
		
		/*factory.crearTablasConDatos();*/

		factory.instanciarProductoDAO().productoMasRecaudado();
		System.out.println("\n");

		System.out.println("Lista de clientes ordenada de mayor a menor facturacion:");
		factory.instanciarClienteDAO().listadoClientesPorFacturacion();
		
		Cliente c = new Cliente(101, "Sofia", "pepito");
		
		Producto p = new Producto(200, "aa", 2);
		
		factory.instanciarProductoDAO().insertar(p);
		
		
		factory.instanciarClienteDAO().insertar(c);
		
		
		factory.cerrarConeccion();
			
	}
	
	
}


