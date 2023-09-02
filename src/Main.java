
import java.sql.SQLException;
import java.util.List;

import entidades.Cliente;
import entidades.Factura;
import entidades.FacturaProducto;
import entidades.Producto;
import mysql.Factory;


public class Main {

	public static void main (String [] args) throws SQLException {
		
		
		
		Factory factory = new Factory ("jdbc:mysql://localhost:3306/tp1-ArqWeb", "root", "");
		
		/*3) Escriba un programa JDBC que retorne el producto que más recaudó. Se define
		“recaudación” como cantidad de productos vendidos multiplicado por su valor.*/
		
		
		factory.instanciarProductoDAO().productoMasRecaudado();
		
		/*4) Escriba un programa JDBC que imprima una lista de clientes, ordenada por a cuál se le
		facturó más.*/
		
		
		
		
		
		
		/*List<Factura> facturas = man.instanciarFacturaDAO().obtenerTodos();
		for (Factura f: facturas) {
			System.out.println(f.toString());
		}*/
		
		/*List<Producto> productos = man.instanciarProductoDAO().obtenerTodos();
		for (Producto p: productos) {
			System.out.println(p.toString());
		}*/
		
		/*List<FacturaProducto> facturasProductos = factory.instanciarFacturaProductoDAO().obtenerTodos();
		for (FacturaProducto fp: facturasProductos) {
			System.out.println(fp.toString());*/
			
		}
		
		
	}


