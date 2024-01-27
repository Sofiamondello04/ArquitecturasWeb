package dao;

import entidades.Cliente;


public interface ClienteDAO extends DAO <Cliente , Integer> {

	void listadoClientesPorFacturacion();

}
