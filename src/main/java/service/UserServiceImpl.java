package service;

import dao.UserDAO;
import dao.UserDAOImplMySQL;
import exception.DAOException;
import exception.ServiceException;
import model.UserAccount;
import util.Checker;

public class UserServiceImpl implements UserService {

	private UserDAO userDAO = new UserDAOImplMySQL();

	@Override
	public void registration(String login, String password, String email) throws ServiceException {
		try {
			if (Checker.areNull(login, password, email)) {
				userDAO.add(new UserAccount(login, password, email));
			}
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("UserServiceImpl.registration()", e);
		}

	}

	@Override
	public boolean singIn(String login, String password) throws ServiceException {
		try {
			if (!Checker.areNull(login, password)) {
				return userDAO.find(login, password);
			}
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("UserServiceImpl.singIn()", e);
		}
		return false;
	}

	@Override
	public UserAccount getUser(String login) throws ServiceException {
		if (!Checker.isNull(login)) {
			try {
				return userDAO.getUser(login);
			} catch (DAOException e) {
				e.printStackTrace();
				throw new ServiceException("UserServiceImpl.getUser()", e);
			}
		}
		return null;
	}

}
