package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectionPool.BasicConnectionPool;
import exception.DAOException;
import model.UserAccount;

public class UserDAOImplMySQL implements UserDAO {

	private BasicConnectionPool connectionPool = BasicConnectionPool.getBasicConnectionPool();
	private final String ADD_USER_SQL = "insert into USER_ACCOUNT (USER_NAME, GENDER, PASSWORD) values ('?', '?', '?')";
	private final String GET_USER_SQL = "select * from USER_ACCOUNT where USER_NAME = ? and PASSWORD = ?";
	private final String GET_USER_BY_LOGIN_SQL = "select * from USER_ACCOUNT where USER_NAME = ?";

	@Override
	public boolean add(UserAccount user) throws DAOException {
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement statement = connection.prepareStatement(ADD_USER_SQL)) {
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getGender());
			if (statement.execute()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("UserDAOImplMySQL.add() : ", e);
		} finally {
			connectionPool.releaseConnection(connection);
		}
		return false;
	}

	@Override
	public boolean find(String login, String password) throws DAOException {
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement statement = connection.prepareStatement(GET_USER_SQL)) {
			statement.setString(1, login);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				rs.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("UserDAOImplMySQL.find() : ", e);

		} finally {
			connectionPool.releaseConnection(connection);
		}
		return false;
	}

	@Override
	public UserAccount getUser(String login) throws DAOException {
		Connection connection = connectionPool.getConnection();
		try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_LOGIN_SQL)) {
			statement.setString(1, login);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				UserAccount user = new UserAccount();
				user.setUserName(rs.getString(1));
				user.setGender(rs.getString(2));
				user.setPassword(rs.getString(3));
				rs.close();
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("UserDAOImplMySQL.find() : ", e);

		} finally {
			connectionPool.releaseConnection(connection);
		}
		return null;
	}

}
