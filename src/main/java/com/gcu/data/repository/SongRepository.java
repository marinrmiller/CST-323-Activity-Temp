package com.gcu.data.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcu.data.entity.SongEntity;

public interface SongRepository extends CrudRepository<SongEntity, Integer> {
	@Override
	@Query(value="SELECT * FROM songs")
	public List<SongEntity> findAll();
}
