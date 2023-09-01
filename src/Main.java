import java.sql.SQLException;
import java.util.List;

import entidades.Cliente;
import mysql.MySQLDaoManager;

public class Main {

	public static void main (String [] args) throws SQLException {
		
		MySQLDaoManager man = new MySQLDaoManager ("jdbc:mysql://localhost:3306/tp1-ArqWeb", "root", "");
		List<Cliente> clientes = man.obtenerClienteDAO().obtenerTodos();
		for (Cliente c: clientes) {
			System.out.println(c.toString());
		}
		
		
	}
}
