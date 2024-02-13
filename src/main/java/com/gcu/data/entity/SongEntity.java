package com.gcu.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("songs")
public class SongEntity {
	
	@Id
	private int id;
	
	@Column("title")
	private String title;
	
	@Column("album")
	private String album;
	
	@Column("artist")
	private String artist;
	
	@Column("genre")
	private String genre;
	
	@Column("user")
	private String user;

	public SongEntity() {
		super();
		this.id = 0;
		this.title = "Unknown";
		this.album = "Unknown";
		this.artist = "Unknown";
		this.genre = "Unknown";
		this.user = "Unknown";
	}

	public SongEntity(int id, String title, String album, String artist, String genre, String user) {
		super();
		this.id = id;
		this.title = title;
		this.album = album;
		this.artist = artist;
		this.genre = genre;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
}
