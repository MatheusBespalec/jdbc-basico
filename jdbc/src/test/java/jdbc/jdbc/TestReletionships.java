package jdbc.jdbc;

import org.junit.Test;

import br.com.jdbc.dao.UserDAO;
import br.com.jdbc.model.User;

public class TestReletionships {
	@Test
	public void addPhonesToUser()
	{
		UserDAO dao = new UserDAO();
		User user = dao.findById(4L);
		dao.addPhone(user, "+55 (11) 96898-7406");
		
		System.out.println(user);
	}
	
	@Test
	public void selectAllUsersWithPhones()
	{
		UserDAO userDao = new UserDAO();
		userDao.findAllWithPhones().forEach((user) -> System.out.println(user));
	}
	
	@Test
	public void selectUserWithPhones()
	{
		UserDAO userDao = new UserDAO();
		User user = userDao.findById(4L);
		System.out.println(user);
		userDao.loadPhones(user);
		System.out.println(user);
	}
}
