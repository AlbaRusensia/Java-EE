package service;

import java.util.List;

import exception.ServiceException;
import model.Product;

public interface ProductService {

	public boolean save(Product product) throws ServiceException;

	public List<Product> find(String parametr) throws ServiceException;

	public List<Product> getAll() throws ServiceException;

	public boolean remove(Integer id) throws ServiceException;

}
