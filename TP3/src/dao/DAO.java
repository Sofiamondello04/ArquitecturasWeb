package dao;

import java.util.List;

/* Interfaz DAO con los metodos comunes de CRUD para poder implementar tantos DAOs como entidades tenga,
 * donde C es la clase y V la variable.
 */


public interface DAO<C, V> {
	
	public void insertar(C c);
	
	public void modificar(C c);
	
	public void eliminar(C c);
	
	public List<C> obtenerTodos();
	
	public C obtenerUno(V id);
}
