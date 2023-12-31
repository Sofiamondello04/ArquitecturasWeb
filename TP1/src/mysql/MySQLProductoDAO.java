package mysql;


import dao.ProductoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidades.Producto;

public class MySQLProductoDAO implements ProductoDAO{

	private Connection conn;
	
	public MySQLProductoDAO(Connection conn) {
		this.conn = conn;
	}
	
	
	@Override
	public void insertar(Producto p) {
		ResultSet res = null;
		try {
			String insertar = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(insertar);
			ps.setInt(1, p.getIdProducto());
			ps.setString(2, p.getNombre());
			ps.setFloat(3, p.getValor());
			
			res = ps.getGeneratedKeys();
			/*if (res.next()) {
				p.setIdProducto(res.getInt(1));
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
	public void modificar(Producto p) {
		try {
			String modificar = "UPDATE Producto SET idProducto = ?, nombre= ?, valor = ? WHERE idProducto= ?";
			PreparedStatement ps = conn.prepareStatement(modificar);
			ps.setInt(1, p.getIdProducto());
			ps.setString(2, p.getNombre());
			ps.setFloat(3, p.getValor());
			ps.executeUpdate();
			conn.commit(); 
			ps.close();
		}
		catch(SQLException ex){	
		}
		
	}
	@Override
	public void eliminar(Producto p) {
		try {
			String eliminar = "DELETE FROM Producto WHERE idProducto= ?";
			PreparedStatement ps = conn.prepareStatement(eliminar);
			ps.setInt(1, p.getIdProducto());
			ps.executeUpdate();
			conn.commit(); 
			ps.close();
		}
		catch(SQLException ex){	
		}
		
	}
	
	
	@Override
	public Producto obtenerUno(Integer idProducto) {
		ResultSet res = null;
		Producto producto = null;
		try {
			String obtenerUno = "SELECT idProducto, nombre, valor FROM Producto WHERE idProducto= ?";
			PreparedStatement ps = conn.prepareStatement(obtenerUno);
			ps.setInt(1, idProducto);
			res = ps.executeQuery();
			if (res.next()) {
				producto = convertir(res);
			}
			conn.commit(); 
			res.close();
			ps.close();
		}
		catch(SQLException ex){	
		}
		return producto;
	}
	
	@Override
	public List<Producto> obtenerTodos() {
		ResultSet resultado = null;
		List <Producto> productos = new ArrayList<>();
		try {
			String obtenerTodos = "SELECT idProducto, nombre, valor FROM Producto";
			PreparedStatement ps = conn.prepareStatement(obtenerTodos);
			resultado = ps.executeQuery();
			
			while (resultado.next()) {
				productos.add(convertir(resultado));
			}
			conn.commit(); 
			resultado.close();
			ps.close();
		}
		catch(SQLException ex){	
		}
		return productos;
	}

	private Producto convertir (ResultSet res) throws SQLException {
		
		int idProducto = res.getInt("idProducto");
		String nombre = res.getString("nombre");
		float valor = res.getFloat("valor");
		Producto producto = new Producto(idProducto, nombre, valor);
		return producto;
	}
	
	
	public void productoMasRecaudado() {
		
		ResultSet resultado;

		try {
			conn.setAutoCommit(false);
			String productoMasRecaudado = "SELECT p.idProducto, p.nombre, SUM(fp.cantidad) * p.valor AS recaudacion\r\n"
					+ "FROM `tp1-arqweb`.producto p INNER JOIN factura_producto fp ON p.idProducto = fp.idProducto\r\n"
					+ "GROUP BY idProducto\r\n"
					+ "ORDER BY recaudacion DESC\r\n"
					+ "LIMIT 1";
			
			PreparedStatement ps = conn.prepareStatement(productoMasRecaudado);
			resultado = ps.executeQuery();
            if (resultado.next()) {
                int idProducto = resultado.getInt("idProducto");
                String nombre = resultado.getString("nombre");
                int recaudacion = resultado.getInt("recaudacion");
                System.out.println("El producto con mayor recaudacion es '"+nombre + "' con $" + recaudacion);
            }
            conn.commit();
            resultado.close();
            ps.close();          
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	
	

	

	

}
