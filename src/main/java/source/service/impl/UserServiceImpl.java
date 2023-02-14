package source.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import source.DAO.UserDao;
import source.DAO.Impl.UserDaoImpl;
import source.domain.User;
import source.service.UserService;
import source.service.impl.UserServiceImpl;

public class UserServiceImpl implements UserService {
	private UserDao userDao;
	private static UserService userServiceImpl;

	private static Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

	private UserServiceImpl() {
		try {
			userDao = new UserDaoImpl();
		} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			LOGGER.error(e);
		}
	}

	public static UserService getUserService() {
		if (userServiceImpl == null) {
			userServiceImpl = new UserServiceImpl();
		}
		return userServiceImpl;
	}

	@Override
	public User create(User user) {
		return userDao.create(user);
	}

	@Override
	public User read(Integer id) {
		return userDao.read(id);
	}

	@Override
	public User update(User user) {
		return userDao.update(user);
	}

	@Override
	public void delete(Integer id) {
		userDao.delete(id);
	}

	@Override
	public List<User> readAll() {
		return userDao.readAll();
	}

	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}
}
