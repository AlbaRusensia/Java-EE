package dao;

import java.util.List;

import exception.DAOException;
import model.Product;

public interface ProductDAO {// обработать ошибки
	public boolean add(Product product) throws DAOException;

	public List<Product> find(String parametr) throws DAOException;

	public List<Product> getAll() throws DAOException;

	public boolean remove(Integer id)  throws DAOException;
	
	public boolean update(Product product) throws DAOException;

}
