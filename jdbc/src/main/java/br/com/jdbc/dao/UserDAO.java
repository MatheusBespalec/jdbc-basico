package br.com.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.jdbc.connection.SingleConnection;
import br.com.jdbc.model.User;

public class UserDAO {
	private Connection connection;
	
	public UserDAO()
	{
		this.connection = SingleConnection.getConnection();
	}
	
	public void save(User user)
	{
		try {
			String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
			PreparedStatement insert = this.connection.prepareStatement(sql);
			insert.setString(1, user.getName());
			insert.setString(2, user.getEmail());
			insert.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<User> findAll()
	{
		List<User> list = new ArrayList<User>();
		try {
			
			String sql = "SELECT * FROM users";
			PreparedStatement select = this.connection.prepareStatement(sql);
			ResultSet result =  select.executeQuery();
			
			while (result.next()) {				
				User user = new User(
					result.getLong("id"),
					result.getString("name"),
					result.getString("email")		
				);
				
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<User> findAllWithPhones()
	{
		List<User> list = new ArrayList<User>();
		try {
			
			String sql = "SELECT * "
					+ "     FROM users "
				   + " LEFT JOIN user_phones AS phone ON phone.user_id = users.id";
			PreparedStatement select = this.connection.prepareStatement(sql);
			ResultSet result =  select.executeQuery();
			
			HashMap<Long, User> usersMap = new HashMap<Long, User>();
			while (result.next()) {
				if (usersMap.containsKey(result.getLong("id"))) {
					usersMap.get(result.getLong("id"))
						.addPhone(result.getString("phone"));
					continue;
				}
				
				User user = new User(
					result.getLong("id"),
					result.getString("name"),
					result.getString("email")		
				);
				
				if (result.getString("phone") != null) {
					user.addPhone(result.getString("phone"));
				}
				
				list.add(user);
				usersMap.put(result.getLong("id"), user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public User findById(Long id)
	{
		try {
			String sql = "SELECT * FROM users WHERE id = ? LIMIT 1";
			
			PreparedStatement select = this.connection.prepareStatement(sql);
			select.setLong(1, id);
			
			ResultSet result =  select.executeQuery();
			result.next();
			
			return new User(
				result.getLong("id"),
				result.getString("name"),
				result.getString("name")
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void loadPhones(User user)
	{
		try {
			String sql = "SELECT * FROM user_phones WHERE user_id = ?";
			
			PreparedStatement select = this.connection.prepareStatement(sql);
			select.setLong(1, user.getId());
			
			ResultSet result =  select.executeQuery();
			while (result.next()) {
				user.addPhone(result.getString("phone"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean update(User user)
	{
		try {
			String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
			
			PreparedStatement select = this.connection.prepareStatement(sql);
			select.setString(1, user.getName());
			select.setString(2, user.getEmail());
			select.setLong(3, user.getId());
			
			return select.execute() == false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public User addPhone(User user, String phone)
	{
		try {
			String sql = "INSERT INTO user_phones (user_id, phone) VALUES (?, ?)";
			PreparedStatement insert = this.connection.prepareStatement(sql);
			insert.setLong(1, user.getId());
			insert.setString(2, phone);
			insert.execute();
			
			user.addPhone(phone);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public boolean delete(User user)
	{
		try {
			String sql = "DELETE FROM users WHERE id = ?";
			
			PreparedStatement select = this.connection.prepareStatement(sql);
			select.setLong(1, user.getId());
			
			return select.execute() == false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
