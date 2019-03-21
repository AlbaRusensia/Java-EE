package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectionPool.BasicConnectionPool;
import exception.DAOException;
import model.Product;
import util.Checker;

public class ProductDAOImplMySQL implements ProductDAO {
	private BasicConnectionPool connectionPool = BasicConnectionPool.getBasicConnectionPool();
	private final String ADD_PRODUCT_SQL = "insert into PRODUCT (CODE, NAME, PRICE) values (?, ?, ?)";
	private final String GET_PRODUCTS_SQL = "select * from PRODUCT";
	private final String GET_PRODUCTS_BY_ID_SQL = "select * from PRODUCT where id = ?";
	private final String GET_PRODUCTS_BY_NAME_SQL = "select * from PRODUCT where name = ?";
	private final String GET_PRODUCTS_BY_PRICE_SQL = "select * from PRODUCT where PRICE = ?";
	private final String REMOVE_PRODUCT_SQL = "delete from PRODUCT where id = ?";
	private final String UPDATE_PRODUCT_SQL = "update PRODUCT set CODE =?, NAME =?, PRICE =? where id = ? ";

	@Override
	public boolean add(Product product) throws DAOException {
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT_SQL)) {
			statement.setString(1, product.getCode());
			statement.setString(2, product.getName());
			statement.setFloat(3, product.getPrice());
			if (statement.execute()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("BasicConnectionPool.find()", e);
		} finally {
			connectionPool.releaseConnection(connection);
		}
		return false;
	}

	@Override
	public List<Product> find(String parametr) throws DAOException {
		try {
			if (Checker.isInt(parametr)) {
				Integer id = Integer.parseInt(parametr);
				return findOfId(id);
			}
			if (Checker.isNumber(parametr)) {
				float price = Float.parseFloat(parametr);
				return findOfPrice(price);
			} else {
				return findOfName(parametr);
			}
		} catch (DAOException e) {
			e.printStackTrace();
			throw new DAOException("BasicConnectionPool.find()", e);
		}
	}

	private List<Product> findOfId(Integer id) throws DAOException {
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement statement = connection.prepareStatement(GET_PRODUCTS_BY_ID_SQL)) {
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			return createCollection(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("ProductDAOImplMySQL.createCollection() : ", e);
		} finally {
			connectionPool.releaseConnection(connection);
		}
	}

	private List<Product> findOfName(String parametr) throws DAOException {
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement statement = connection.prepareStatement(GET_PRODUCTS_BY_NAME_SQL)) {
			statement.setString(1, parametr + "%");
			ResultSet rs = statement.executeQuery();
			return createCollection(rs);
		} catch (SQLException | DAOException e) {
			e.printStackTrace();
			throw new DAOException("ProductDAOImplMySQL.findOfName() : ", e);
		} finally {
			connectionPool.releaseConnection(connection);
		}
	}

	private List<Product> findOfPrice(float price) throws DAOException {
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement statement = connection.prepareStatement(GET_PRODUCTS_BY_PRICE_SQL)) {
			statement.setFloat(1, price);
			ResultSet rs = statement.executeQuery();
			return createCollection(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("ProductDAOImplMySQL.createCollection() : ", e);
		} finally {
			connectionPool.releaseConnection(connection);
		}

	}

	private List<Product> createCollection(ResultSet reSu) throws DAOException {
		List<Product> foundProducts = new ArrayList<>();
		try (ResultSet rs = reSu) {
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setCode(rs.getString(2));
				product.setName(rs.getString(3));
				product.setPrice(rs.getFloat(4));
				foundProducts.add(product);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("ProductDAOImplMySQL.createCollection() : ", e);
		}
		return foundProducts;
	}

	@Override
	public List<Product> getAll() throws DAOException {
		Connection connection = connectionPool.getConnection();
		try (Statement statement = connection.createStatement()) {
			ResultSet rs = statement.executeQuery(GET_PRODUCTS_SQL);
			return createCollection(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("ProductDAOImplMySQL.getAll() : ", e);
		} finally {
			connectionPool.releaseConnection(connection);
		}
	}

	@Override
	public boolean remove(Integer id) throws DAOException {
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement pStatement = connection.prepareStatement(REMOVE_PRODUCT_SQL)) {
			pStatement.setInt(1, id);
			if (pStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("ProductDAOImplMySQL.remove() : ", e);
		} finally {
			connectionPool.releaseConnection(connection);
		}
		return false;
	}

	@Override
	public boolean update(Product product) throws DAOException {
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement pStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL)) {
			pStatement.setString(1, product.getCode());
			pStatement.setString(2, product.getName());
			pStatement.setFloat(3, product.getPrice());
			pStatement.setInt(4, product.getId());
			if (pStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("ProductDAOImplMySQL.update() : ", e);
		} finally {
			connectionPool.releaseConnection(connection);
		}
		return false;
	}

}
