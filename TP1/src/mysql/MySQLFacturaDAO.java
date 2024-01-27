package mysql;

import dao.FacturaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Result;

import dao.ClienteDAO;
import entidades.Cliente;
import entidades.Factura;

public class MySQLFacturaDAO implements FacturaDAO{

	private Connection conn;
	
	public MySQLFacturaDAO(Connection conn) {
		this.conn = conn;
	}
	
	
	@Override
	public void insertar(Factura f) {
		ResultSet res = null;
		try {
			String insertar = "INSERT INTO Factura (idFactura, idCliente) VALUES (?,?)";
			PreparedStatement ps = conn.prepareStatement(insertar);
			ps.setInt(1, f.getIdFactura());
			ps.setInt(2, f.getIdCliente());
			
			res = ps.getGeneratedKeys();
			if (res.next()) {
				f.setIdFactura(res.getInt(1));
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
	public void modificar(Factura f) {
		try {
			String modificar = "UPDATE Factura SET idFactura= ?, idCliente = ? WHERE idFactura= ?";
			PreparedStatement ps = conn.prepareStatement(modificar);
			ps.setInt(1, f.getIdFactura());
			ps.setInt(2, f.getIdCliente());
			ps.executeUpdate();
			conn.commit(); //ver si corresponde dejar este cierre de coneccion aca.
			ps.close();
		}
		catch(SQLException ex){	
		}
		
	}
	@Override
	public void eliminar(Factura f) {
		try {
			String eliminar = "DELETE FROM Factura WHERE idFactura= ?";
			PreparedStatement ps = conn.prepareStatement(eliminar);
			ps.setInt(1, f.getIdFactura());
			ps.executeUpdate();
			conn.commit(); //ver si corresponde dejar este cierre de coneccion aca.
			ps.close();
		}
		catch(SQLException ex){	
		}
		
	}
	
	
	@Override
	public Factura obtenerUno(Integer idFactura) {
		ResultSet res = null;
		Factura factura = null;
		try {
			String obtenerUno = "SELECT idFactura, idCliente, FROM Factura WHERE idFactura= ?";
			PreparedStatement ps = conn.prepareStatement(obtenerUno);
			ps.setInt(1, idFactura);
			res = ps.executeQuery();
			if (res.next()) {
				factura = convertir(res);
			}
			conn.commit(); //ver si corresponde dejar este cierre de coneccion aca.
			res.close();
			ps.close();
		}
		catch(SQLException ex){	
		}
		return factura;
	}
	
	@Override
	public List<Factura> obtenerTodos() {
		ResultSet resultado = null;
		List <Factura> facturas = new ArrayList<>();
		try {
			String obtenerTodos = "SELECT idFactura, idCliente FROM Factura";
			PreparedStatement ps = conn.prepareStatement(obtenerTodos);
			resultado = ps.executeQuery();
			
			while (resultado.next()) {
				facturas.add(convertir(resultado));
			}
			conn.commit(); //ver si corresponde dejar este cierre de coneccion aca.
			resultado.close();
			ps.close();
		}
		catch(SQLException ex){	
		}
		return facturas;
	}

	private Factura convertir (ResultSet res) throws SQLException {
		
		int idFactura = res.getInt("idFactura");
		int idCliente = res.getInt("idCliente");
		Factura factura = new Factura(idFactura, idCliente);
		return factura;
		
		
	}
	

	

	

	

}
