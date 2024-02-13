package com.gcu.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gcu.data.entity.SongEntity;
import com.gcu.data.entity.UserEntity;
import com.gcu.data.repository.UserRepository;

/**
 * @author Ginav
 *
 */
@Service
public class UserDataService implements UserDataAccessInterface<UserEntity>, DataAccessInterface<UserEntity> {
	
	@Autowired
	private UserRepository userRepository;
	
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public UserDataService(UserRepository userRepository, DataSource dataSource) {
		this.userRepository = userRepository;
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	/**
	 *find all users and return a list of them
	 */
	@Override
	public List<UserEntity> findAll() {
		List<UserEntity> entities = new ArrayList<UserEntity>();
		
		try {
			// Get all the entities
			Iterable<UserEntity> ordersIterable = userRepository.findAll();
			
			// Convert to a List and return
			entities = new ArrayList<UserEntity>();
			ordersIterable.forEach(entities::add);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// Return the List
		return entities;
	}
	
	/**
	 *find user with the same id and return the entity
	 */
	@Override
	public UserEntity findById(int id) {
		String sql = "SELECT * FROM `users` WHERE `userID` = ?";
		try {
			jdbcTemplateObject.update(sql, id);
			
			Optional<UserEntity> user = userRepository.findById(id);
			return user.get();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *Create a user and return true if successful
	 */
	@Override
	public boolean create(UserEntity t) {
		
		try {
			if(t != null) {
				String sql = "INSERT INTO `users` "
						+ "(`userID`, `username`, `password`, `firstName`, `lastName`, `email`, `phoneNumber`) "
						+ "VALUES (NULL, ?, ?, ?, ?, ?, ?)";
				
				jdbcTemplateObject.update(sql,
										t.getUsername(),
										t.getPassword(),
										t.getFirstName(), 
										t.getLastName(),
										t.getEmail(),
										t.getPhoneNumber());
				//created user
				return true;
			} 
		}
		catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}

	/**
	 * Update a user information and return true if successful
	 */
	@Override
	public boolean update(UserEntity t) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 *Delete a user and return true if deleted
	 */
	@Override
	public boolean delete(int id) {
		String sql = "DELETE FROM users WHERE userID = ?";
		try {
			jdbcTemplateObject.update(sql, id);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	/**
	 *Locate user with a entity object and if found return true
	 */
	@Override
	public boolean findByObject(UserEntity t) {
		String sql = "SELECT username, password FROM `users` WHERE `username` = ? AND `password`= ?";
		try {
			List<Map<String, Object>> results = jdbcTemplateObject.queryForList(sql, t.getUsername(), t.getPassword());
			if (results.size() > 0) return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Locate a user by their username and return true if successful
	 */
	@Override
	public UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
}
