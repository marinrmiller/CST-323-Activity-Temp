package com.gcu.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.gcu.data.entity.SongEntity;

public class SongRowMapper implements RowMapper<SongEntity> {

	/**
	 *Converts a row from a database query result into a entity object
	 */
	@Override
	public SongEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new SongEntity(rs.getInt("id"),
							  rs.getString("title"),
							  rs.getString("album"),
							  rs.getString("artist"),
							  rs.getString("genre"),
							  rs.getString("user"));
	}
	
}
