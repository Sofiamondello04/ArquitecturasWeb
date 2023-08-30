package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.cj.xdevapi.Result;

import dao.ClienteDAO;
import entidades.Cliente;

public class MySQLClienteDAO implements ClienteDAO{

	private Connection conn;
	
	
	public MySQLClienteDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void instertar(Cliente a) {
		try {
			String insert = "INSERT INTO Cliente (nombre, email) VALUES (?,?)";
			PreparedStatement ps = conn.prepareStatement(insert);
			ps.setString(1, a.getNombre());
			ps.setString(2, a.getEmail());
			ps.executeUpdate();
			conn.commit();
			ps.close();
		}
		catch(SQLException ex){	
		}
	}

	private Cliente convertir(ResultSet rs) {
		String nombre = rs.getString("nombre");
		String email = rs.getString("email");
		Cliente cliente = new Cliente(nombre, email);
		cliente.setIdCliente(rs.getInt(idCliente));
		return cliente;
		
	}
	@Override
	public void modificar(Cliente a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Cliente a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cliente obtener(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
