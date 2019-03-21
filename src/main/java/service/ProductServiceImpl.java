package service;

import java.util.List;

import dao.ProductDAO;
import dao.ProductDAOImplMySQL;
import exception.DAOException;
import exception.ServiceException;
import model.Product;
import util.Checker;

public class ProductServiceImpl implements ProductService {

	private ProductDAO productDAO = new ProductDAOImplMySQL();

	@Override
	public boolean save(Product product) throws ServiceException {
		try {
			if (!Checker.isNull(product)) {
				if (product.getId() == 0) {
					return productDAO.add(product);
				} else {
					return productDAO.update(product);
				}
			}
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("ProductServiceImpl.save() : ", e);
		}
		return false;
	}

	@Override
	public List<Product> find(String parametr) throws ServiceException {
		try {
			if (!Checker.isNull(parametr)) {
				return productDAO.find(parametr);
			}
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("ProductServiceImpl.find()", e);
		}
		return null;
	}

	@Override
	public List<Product> getAll() throws ServiceException {
		try {
			return productDAO.getAll();
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("ProductServiceImpl.getAll()", e);
		}
	}

	@Override
	public boolean remove(Integer id) throws ServiceException {
		try {
			return productDAO.remove(id);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("ProductServiceImpl.remove() : ", e);
		}
	}

}
