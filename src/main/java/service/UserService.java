package service;

import exception.ServiceException;
import model.UserAccount;

public interface UserService {

	public void registration(String name, String password, String email) throws ServiceException;

	public boolean singIn(String login, String password) throws ServiceException;

	public UserAccount getUser(String login) throws ServiceException;

}
