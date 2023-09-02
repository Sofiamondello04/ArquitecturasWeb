package dao;

import entidades.Producto;


public interface ProductoDAO extends DAO<Producto, Integer>{

	public void productoMasRecaudado();
	
}
