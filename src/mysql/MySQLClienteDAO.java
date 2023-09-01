package mysql;

import dao.ClienteDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	public void instertar(Cliente c) {
		ResultSet res = null;
		try {
			String insertar = "INSERT INTO Cliente (nombre, email) VALUES (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(insertar);
			ps.setString(2, c.getNombre());
			ps.setString(3, c.getEmail());
			
			res = ps.getGeneratedKeys();
			if (res.next()) {
				c.setIdCliente(res.getInt(1));
			}
			ps.executeUpdate();
			conn.commit(); //ver si corresponde dejar este cierre de coneccion aca.
			res.close();
			ps.close();
		}
		catch(SQLException ex){	
		}
	}
	
	
	@Override
	public void modificar(Cliente c) {
		try {
			String modificar = "UPDATE Cliente SET nombre= ?, email = ? WHERE idCliente= ?";
			PreparedStatement ps = conn.prepareStatement(modificar);
			ps.setInt(1, c.getIdCliente());
			ps.setString(2, c.getNombre());
			ps.setString(3, c.getEmail());
			ps.executeUpdate();
			conn.commit(); //ver si corresponde dejar este cierre de coneccion aca.
			ps.close();
		}
		catch(SQLException ex){	
		}
		
	}
	@Override
	public void eliminar(Cliente c) {
		try {
			String eliminar = "DELETE FROM Cliente WHERE idCliente= ?";
			PreparedStatement ps = conn.prepareStatement(eliminar);
			ps.setInt(1, c.getIdCliente());
			ps.executeUpdate();
			conn.commit(); //ver si corresponde dejar este cierre de coneccion aca.
			ps.close();
		}
		catch(SQLException ex){	
		}
		
	}
	
	
	@Override
	public Cliente obtenerUno(Integer idCliente) {
		ResultSet res = null;
		Cliente cliente = null;
		try {
			String obtenerUno = "SELECT idCliente, nombre, email FROM Cliente WHERE idCliente= ?";
			PreparedStatement ps = conn.prepareStatement(obtenerUno);
			ps.setInt(1, idCliente);
			res = ps.executeQuery();
			if (res.next()) {
				cliente = convertir(res);
			}
			conn.commit(); //ver si corresponde dejar este cierre de coneccion aca.
			res.close();
			ps.close();
		}
		catch(SQLException ex){	
		}
		return cliente;
	}
	
	@Override
	public List<Cliente> obtenerTodos() {
		ResultSet resultado = null;
		List <Cliente> clientes = new ArrayList<>();
		try {
			String obtenerTodos = "SELECT idCliente, nombre, email FROM Cliente";
			PreparedStatement ps = conn.prepareStatement(obtenerTodos);
			resultado = ps.executeQuery();
			
			while (resultado.next()) {
				clientes.add(convertir(resultado));
			}
			conn.commit(); //ver si corresponde dejar este cierre de coneccion aca.
			resultado.close();
			ps.close();
		}
		catch(SQLException ex){	
		}
		return clientes;
	}

	private Cliente convertir (ResultSet res) throws SQLException {
		
		/*int idCliente = 0;
		String nombre = res.getString("nombre");
		String email = res.getString("email");
		
		
		Cliente resultado = new Cliente(idCliente, nombre, email);
		
		resultado.setIdCliente(res.getInt(idCliente));
		return resultado;*/
		
		
		String nombre = res.getString("nombre");
		String email = res.getString("email");
		int idCliente = res.getInt("idCliente");
		Cliente cliente = new Cliente(idCliente, nombre, email);
		return cliente;
		
		
	}
	

	

	

	

}
