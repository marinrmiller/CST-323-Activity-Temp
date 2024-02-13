package com.gcu.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SongModel 
{
	private int id;
	
	@NotNull(message="Song title is a required field")
	@Size(min=1, max=64, message="Song title must be between 1 and 64 characters")
	private String title;
	
	@NotNull(message="Album name is a required field")
	@Size(min=1, max=64, message="Album name must be between 1 and 64 characters")
	private String album;
	
	@NotNull(message="Artist name is a required field")
	@Size(min=1, max=64, message="Artist name must be between 1 and 64 characters")
	private String artist;
	
	@NotNull(message="Genre name is a required field")
	@Size(min=1, max=64, message="Genre name must be between 1 and 64 characters")
	private String genre;
	
	private String user;
	

	public SongModel() {
	}
	
	/**
	 * @param id
	 * @param title
	 * @param album
	 * @param artist
	 * @param genre
	 */
	public SongModel(int id, String title, String album, String artist, String genre, String user) {
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
	public void setId(int songID) {
		this.id = songID;
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
