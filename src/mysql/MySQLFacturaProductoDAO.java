package mysql;


import dao.FacturaProductoDAO;
import dao.ProductoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Result;

import entidades.Factura;
import entidades.FacturaProducto;
import entidades.Producto;

public class MySQLFacturaProductoDAO implements FacturaProductoDAO{

	private Connection conn;
	
	public MySQLFacturaProductoDAO(Connection conn) {
		this.conn = conn;
	}
	//VEEEEER CREO QUE NO SE HACEN LOS ISNERT Y UPDATE EN ESTOS CASOS
	
	@Override
	public void insertar(FacturaProducto fp) {
		ResultSet res = null;
		try {
			String insertar = "INSERT INTO FacturaProducto (idFactura, idProducto, cantidad) VALUES (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(insertar);
			ps.setInt(1, fp.getIdFactura());
			ps.setInt(2, fp.getIdProducto());
			ps.setInt(3, fp.getCantidad());
			
			res = ps.getGeneratedKeys();
			if (res.next()) {
				fp.setIdProducto(res.getInt(1));
			}
			ps.executeUpdate();
			conn.commit(); //ver si corresponde dejar este cierre de coneccion aca.
			res.close();
			ps.close();
		}
		catch(SQLException ex){	
		}
	}
	
	//VEEEEER CREO QUE NO SE HACEN LOS ISNERT Y UPDATE EN ESTOS CASOS
	
	@Override
	public void modificar(FacturaProducto fp) {
		try {
			String modificar = "UPDATE FacturaProducto SET idFactura = ?, idProducto= ?, cantidad = ? WHERE idFactura= ?";
			PreparedStatement ps = conn.prepareStatement(modificar);
			ps.setInt(1, fp.getIdFactura());
			ps.setInt(2, fp.getIdProducto());
			ps.setInt(3, fp.getCantidad());
			ps.executeUpdate();
			conn.commit(); //ver si corresponde dejar este cierre de coneccion aca.
			ps.close();
		}
		catch(SQLException ex){	
		}
		
	}
	@Override
	public void eliminar(FacturaProducto fp) {
		try {
			String eliminar = "DELETE FROM FacturaProducto WHERE idFactura= ?";
			PreparedStatement ps = conn.prepareStatement(eliminar);
			ps.setInt(1, fp.getIdFactura());
			ps.executeUpdate();
			conn.commit(); //ver si corresponde dejar este cierre de coneccion aca.
			ps.close();
		}
		catch(SQLException ex){	
		}
		
	}
	
	
	@Override
	public FacturaProducto obtenerUno(Integer idFactura) {
		ResultSet res = null;
		FacturaProducto facturaProducto = null;
		try {
			String obtenerUno = "SELECT idFactura, idProducto, cantidad FROM Factura_Producto WHERE idFactura= ?";
			PreparedStatement ps = conn.prepareStatement(obtenerUno);
			ps.setInt(1, idFactura);
			res = ps.executeQuery();
			if (res.next()) {
				facturaProducto = convertir(res);
			}
			conn.commit(); //ver si corresponde dejar este cierre de coneccion aca.
			res.close();
			ps.close();
		}
		catch(SQLException ex){	
		}
		return facturaProducto;
	}
	
	@Override
	public List<FacturaProducto> obtenerTodos() {
		ResultSet resultado = null;
		List <FacturaProducto> facturasProductos = new ArrayList<>();
		try {
			String obtenerTodos = "SELECT idFactura, idProducto, cantidad FROM Factura_Producto";
			PreparedStatement ps = conn.prepareStatement(obtenerTodos);
			resultado = ps.executeQuery();
			
			while (resultado.next()) {
				facturasProductos.add(convertir(resultado));
			}
			conn.commit(); //ver si corresponde dejar este cierre de coneccion aca.
			resultado.close();
			ps.close();
		}
		catch(SQLException ex){	
		}
		return facturasProductos;
	}

	private FacturaProducto convertir (ResultSet res) throws SQLException {
		int idFactura = res.getInt("idFactura");
		int idProducto = res.getInt("idProducto");
		int cantidad = res.getInt("cantidad");
		FacturaProducto facturaProducto = new FacturaProducto(idFactura, idProducto, cantidad);
		return facturaProducto;
		
		
	}


	

	

	

	

}
