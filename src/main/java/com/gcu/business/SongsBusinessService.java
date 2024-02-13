package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gcu.data.SongDataService;
import com.gcu.data.entity.SongEntity;
import com.gcu.models.SongModel;

@Component
public class SongsBusinessService implements BusinessServiceInterface {

	@Autowired
	SongDataService service;
	
	//returns a list of all songs
	public List<SongModel> getSongs() {
		List<SongEntity> songsEntity = service.findAll();
		
		List<SongModel> songsDomain = new ArrayList<SongModel>();
		
		for(SongEntity entity : songsEntity)
		{
			//required so technology dependencies from the OrderEntity do not appear to the presentation layer
			songsDomain.add(new SongModel(entity.getId(), entity.getTitle(), entity.getAlbum(), entity.getArtist(), entity.getGenre(), entity.getUser()));
		}
		
		return songsDomain;
	}
	//Return songModel with the given id
	public SongModel getSongById(int id) {
		SongEntity entity = service.findById(id);
		SongModel song = new SongModel(entity.getId(), entity.getTitle(), entity.getAlbum(), entity.getArtist(), entity.getGenre(), entity.getUser());
		return song;
	}
	
	@PostConstruct
	public void init() {
		System.out.println(">>>> SongsBusinessService init Method. ");
	}
	
	@PreDestroy
	public void destroy(){
		System.out.println(">>>> SongsBusinessService destroy method. ");
	}

	@Override
	public boolean authenticate(String username, String Password) {
		return false;
	}

}
