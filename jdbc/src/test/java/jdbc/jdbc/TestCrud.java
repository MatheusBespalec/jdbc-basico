package jdbc.jdbc;

import org.junit.Test;

import br.com.jdbc.dao.UserDAO;
import br.com.jdbc.model.User;

public class TestCrud {
	@Test
	public void saveUser()
	{
		User user = new User("Paulo", "paulo@gmail.com");
		UserDAO userDao = new UserDAO();
		userDao.save(user);
	}
	
	@Test
	public void selectAllUsers()
	{
		UserDAO userDao = new UserDAO();
		userDao.findAll().forEach((user) -> System.out.println(user));
	}
	
	@Test
	public void selectUserById()
	{
		UserDAO userDao = new UserDAO();
		System.out.println(userDao.findById(4L));
	}
	
	@Test
	public void updateUser()
	{
		UserDAO userDao = new UserDAO();
		
		User user = userDao.findById(1L);
		user.setName("Rodrigo");
		user.setEmail("rodrigo@gmail.com");
		
		System.out.println(userDao.update(user));
	}
	
	@Test
	public void deleteUser()
	{
		UserDAO userDao = new UserDAO();
		
		User user = userDao.findById(5L);
		
		System.out.println(userDao.delete(user));
	}
}
