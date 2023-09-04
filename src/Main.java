
import java.sql.SQLException;
import java.util.List;

import entidades.Cliente;
import entidades.Factura;
import entidades.FacturaProducto;
import entidades.Producto;
import mysql.FactoryMySQL;


public class Main {

	public static void main (String [] args) throws SQLException {
		
		/*1) Cree un programa utilizando JDBC que cree el esquema de la base de datos.
		 * 2) Considere los CSV dados y escriba un programa JDBC que cargue los datos a la base de
		 *datos. Considere utilizar la biblioteca Apache Commons CSV, disponible en Maven central,
		 *para leer los archivos.
		 *EJECUTAR CLASE HELPER PARA PODER CREAR LAS TABLAS E INSERTAR LOS DATOS*/
	
		FactoryMySQL factory = new FactoryMySQL ("jdbc:mysql://localhost:3306/tp1-ArqWeb", "root", "");
		
		/*3) Escriba un programa JDBC que retorne el producto que más recaudó. Se define
		 *recaudación como cantidad de productos vendidos multiplicado por su valor.*/
		
		
		//factory.instanciarProductoDAO().productoMasRecaudado();
		


		/*4) Escriba un programa JDBC que imprima una lista de clientes, ordenada por a cuál se le
		facturó más.*/
		
		//factory.instanciarClienteDAO().listadoClientesPorFacturacion();
			
	}
	
	
}


