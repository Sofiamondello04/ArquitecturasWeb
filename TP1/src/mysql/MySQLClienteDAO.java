package mysql;

import dao.ClienteDAO;
import entidades.ClienteDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.mysql.cj.xdevapi.Result;

import dao.ClienteDAO;
import entidades.Cliente;
import entidades.ClienteDTO;

public class MySQLClienteDAO implements ClienteDAO{

	private Connection conn;
	
	public MySQLClienteDAO(Connection conn) {
		this.conn = conn;
		
	}
	
	
	@Override
	
	public void insertar(Cliente c) {
		ResultSet res = null;
		
		try {
			String insertar = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(insertar);
			ps.setInt(1, c.getIdCliente());
			ps.setString(2, c.getNombre());
			ps.setString(3, c.getEmail());			
			res = ps.getGeneratedKeys();
			/*if (res.next()) {
				c.setIdCliente(res.getInt(1));
			}*/
			ps.executeUpdate();
			conn.commit(); 
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
			conn.commit(); 
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
			conn.commit(); 
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
			conn.commit(); 
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
			conn.commit(); 
			resultado.close();
			ps.close();
		}
		catch(SQLException ex){	
		}
		return clientes;
	}

	private Cliente convertir (ResultSet res) throws SQLException {
		
		String nombre = res.getString("nombre");
		String email = res.getString("email");
		int idCliente = res.getInt("idCliente");
		Cliente cliente = new Cliente(idCliente, nombre, email);
		System.out.println(cliente);
		return cliente;
	}


	@Override
	public void listadoClientesPorFacturacion() {		
		ResultSet resultado;		
		try {
			conn.setAutoCommit(false);
			String listadoClientes = "SELECT c.idCliente, c.nombre, SUM(fp.cantidad * p.valor) AS montofacturacion\r\n"
					+ "FROM Cliente c LEFT JOIN Factura f ON c.idCliente = f.idCliente LEFT JOIN Factura_Producto fp ON f.idFactura = fp.idFactura LEFT JOIN Producto p ON fp.idProducto = p.idProducto\r\n"
					+ "GROUP BY c.idCliente, c.nombre\r\n"
					+ "ORDER BY montofacturacion DESC\r\n";	
			
			PreparedStatement ps = conn.prepareStatement(listadoClientes);
			resultado = ps.executeQuery();			        
			List<ClienteDTO> clientes = new ArrayList<>();
            while (resultado.next()) {
                int idCliente = resultado.getInt("idCliente");
                String nombre = resultado.getString("nombre");
                int montoFacturacion = resultado.getInt("montofacturacion");
                ClienteDTO clienteDTO = new ClienteDTO(idCliente, nombre, montoFacturacion);
                clientes.add(clienteDTO);
            }
            for (ClienteDTO cliente : clientes) {
                System.out.println(cliente);
            }
            conn.commit(); 
            resultado.close();
            ps.close();      
		} catch (Exception e) {
            e.printStackTrace();
        }
		
	}

}
