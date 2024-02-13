package com.gcu.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.gcu.data.entity.SongEntity;
import com.gcu.data.repository.SongRepository;

@Service
public class SongDataService implements DataAccessInterface<SongEntity> {
	
	@Autowired
	private SongRepository songRepository;
	
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public SongDataService(SongRepository songRepository, DataSource dataSource) {
		this.songRepository = songRepository;
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	
	/**
	 *Find all songs and return a list
	 */
	@Override
	public List<SongEntity> findAll() {
		List<SongEntity> entities = new ArrayList<SongEntity>();
		
		try {
			// Get all the entities
			Iterable<SongEntity> ordersIterable = songRepository.findAll();
		
			// Convert to a List and return
			entities = new ArrayList<SongEntity>();

			for (SongEntity entity : ordersIterable) {
				entities.add(entity);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// Return the List
		return entities;
	}

	
	/**
	 *Find a song by its unique id and return the entity
	 */
	@Override
	public SongEntity findById(int id) {
		try {
			Optional<SongEntity> song = songRepository.findById(id);
			if (song.isPresent()) return song.get();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	/**
	 * Create a song and return true of song was created
	 */
	@Override
	public boolean create(SongEntity t) {
		String sql = "INSERT INTO `songs` (`id`, `title`, `album`, `artist`, `genre`, `user`) VALUES (NULL, ?, ?, ?, ?, ?)";
		try {
			jdbcTemplateObject.update(sql,
									  t.getTitle(),
									  t.getAlbum(),
									  t.getArtist(),
									  t.getGenre(),
									  "testuser");
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Locate existing song and update, if updated return true
	 */
	
	@Override
	public boolean update(SongEntity t) {
		String sql = "UPDATE `songs` SET `title` = ?, `album` = ?, `artist` = ?, `genre` = ?, `user` = ? WHERE `id` = ?";
		int rows = jdbcTemplateObject.update(sql,
								  t.getTitle(),
								  t.getAlbum(),
								  t.getArtist(),
								  t.getGenre(),
								  "testuser",
								  t.getId());
		if (rows >= 1) return true;
		return false;
	}

	/**
	 *find matching id and delete the song
	 */
	@Override
	public boolean delete(int id) {
		String sql = "DELETE FROM songs WHERE ID = ?";
		try {
			int rows = jdbcTemplateObject.update(sql, id);
			if (rows >= 1) return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean findByObject(SongEntity t) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
