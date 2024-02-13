package com.gcu.data.repository;

import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import com.gcu.data.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
	@Override
	@Query(value="SELECT * FROM user")
	public List<UserEntity> findAll();
	public UserEntity findByUsername(String username);
}
