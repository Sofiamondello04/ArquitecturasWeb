import java.sql.SQLException;
import java.util.List;

import entidades.Cliente;
import entidades.Factura;
import mysql.MySQLDaoManager;

public class Main {

	public static void main (String [] args) throws SQLException {
		
		
		
		MySQLDaoManager man = new MySQLDaoManager ("jdbc:mysql://localhost:3306/tp1-ArqWeb", "root", "");
		
		
		
		/*List<Cliente> clientes = man.instanciarClienteDAO().obtenerTodos();
		for (Cliente c: clientes) {
			System.out.println(c.toString());
		}*/
		
		List<Factura> facturas = man.instanciarFacturaDAO().obtenerTodos();
		for (Factura f: facturas) {
			System.out.println(f.toString());
		}
		
		
	}
}
