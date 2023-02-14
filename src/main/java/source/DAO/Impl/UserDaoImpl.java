package source.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import source.DAO.UserDao;
import source.domain.User;
import source.domain.UserRole;
import source.utils.ConnectionUtils;

public class UserDaoImpl implements UserDao {
	private static String READ_ALL = "SELECT * FROM user";
	private static String CREATE = "insert into user (email, firstname, lastname, role, password) values (?,?,?,?,?)";
	private static String READ_BY_ID = "select * from user where id = ?";
	private static String READ_BY_EMAIL = "select * from user where email = ?";
	private static String UPDATE_BY_ID = "update user set email = ?, firstname = ?, lastname = ?, role = ?, password = ? where id = ?";
	private static String DELETE_BY_ID = "delete from user where id = ?";

	private static Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

	private Connection connection;
	private PreparedStatement preparedStatement;

	public UserDaoImpl() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		connection = ConnectionUtils.openConnection();
	}

	@Override
	public User create(User user) {
		try {
			preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getFirstname());
			preparedStatement.setString(3, user.getLastname());
			preparedStatement.setString(4, UserRole.USER.toString());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.executeUpdate();

			ResultSet result = preparedStatement.getGeneratedKeys();
			result.next();
			user.setId(result.getInt(1));
		} catch (SQLException e) {
			LOGGER.error(e);
		}

		return user;
	}

	@Override
	public User read(Integer id) {
		User user = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			result.next();

			Integer userId = result.getInt("id");
			String email = result.getString("email");
			String firstname = result.getString("firstname");
			String lastname = result.getString("lastname");
			String role = result.getString("role");
			String password = result.getString("password");

			user = new User(userId, email, firstname, lastname, role, password);

		} catch (SQLException e) {
			LOGGER.error(e);
		}

		return user;
	}

	@Override
	public User update(User user) {
		try {
			preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getFirstname());
			preparedStatement.setString(3, user.getLastname());
			preparedStatement.setString(4, user.getRole());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setInt(6, user.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
		}

		return user;
	}

	@Override
	public void delete(Integer id) {
		try {
			preparedStatement = connection.prepareStatement(DELETE_BY_ID);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}

	@Override
	public List<User> readAll() {
		List<User> users = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(READ_ALL);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				Integer userId = result.getInt("id");
				String email = result.getString("email");
				String firstname = result.getString("firstname");
				String lastname = result.getString("lastname");
				String role = result.getString("role");
				String password = result.getString("password");
				users.add(new User(userId, email, firstname, lastname, role, password));
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}

		return users;
	}

	@Override
	public User getUserByEmail(String email) {
		User user = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_EMAIL);
			preparedStatement.setString(1, email);
			ResultSet result = preparedStatement.executeQuery();
			result.next();

			Integer userId = result.getInt("id");
			String firstName = result.getString("firstname");
			String lastName = result.getString("lastname");
			String role = result.getString("role");
			String password = result.getString("password");
			user = new User(userId, email, firstName, lastName, role, password);

		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return user;
	}

}
