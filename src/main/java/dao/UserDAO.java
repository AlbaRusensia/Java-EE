package dao;

import exception.DAOException;
import model.UserAccount;

public interface UserDAO {
	public boolean add(UserAccount user) throws DAOException;

	public boolean find(String login, String password) throws DAOException;

	public UserAccount getUser(String login) throws DAOException;

}
