package dao;

import java.util.List;

/* creo una interfaz de Dao con los metodos comunes de CRUD para poder implementar tantos daos como entidades tenga,
 * donde O es la clase y v la variable.
 */


public interface DAO<C, V> {
	
	public void instertar(C c);
	
	public void modificar(C c);
	
	public void eliminar(C c);
	
	public List<C> obtenerTodos();
	
	public C obtenerUno(V id);
}
