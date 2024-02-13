package com.gcu.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.SongsBusinessService;
import com.gcu.data.SongDataService;
import com.gcu.data.entity.SongEntity;
import com.gcu.data.entity.UserEntity;
import com.gcu.models.SongModel;

@Controller
@RequestMapping("/song")
public class SongController 
{
	@Autowired
	private SongDataService data;
	
	@Autowired
	private SongsBusinessService service;
	/**
	 * Simply add a SongModel and return the view of the song page
	 * @param model Model to bind to the view
	 * @return View name song
	 */
	@GetMapping("")
	public String load(Model model) {
		model.addAttribute("songModel", new SongModel());
		return "song";
	}
	
	/**
	 * When user submits form, bind input to SongModel, check for errors, add attribute, 
	 * create a new song and return the appropriate view
	 * @param songModel SongModel to bind to the view
	 * @param bindingResult Result of model/view binding, used to determine whether there were errors
	 * @param model Model to bind to the view
	 * @return View name song or home, depending on result
	 */
	@PostMapping("/addSong")
	public String addSong(@Valid SongModel songModel, BindingResult bindingResult, Model model) {
		System.out.println(String.format("song/addSong - Form with title of %s, artist of %s, album of %s, and genre of %s", songModel.getTitle(), songModel.getArtist(), songModel.getAlbum(), songModel.getGenre()));
		
		// Check for validation errors
		if (bindingResult.hasErrors()) {
			return "song";
		}

		SongEntity newSong = new SongEntity(songModel.getId(), songModel.getTitle(), songModel.getAlbum(), songModel.getArtist(), songModel.getGenre(), songModel.getUser());
		
		if(data.create(newSong)) {
			model.addAttribute("result", "The song '" + songModel.getTitle() + "' by " + songModel.getArtist() + " was successfully added!");
			model.addAttribute("songs", service.getSongs());
			model.addAttribute("songModel", new SongModel());
			return "library";
		} 
		else {
			model.addAttribute("result", "Error - No song was created.");
			return "song";
		}
	}
	
	/**
	 * When user submits form, bind input to SongModel, check for errors, add attribute,
	 *  update existing song and return the appropriate view
	 * @param songModel SongModel to bind to the view
	 * @param bindingResult Result of model/view binding, used to determine whether there were errors
	 * @param model Model to bind to the view
	 * @return View name updatesong or home, depending on result
	 */
	@PostMapping("/updateSong")
	public String updateSong(@Valid SongModel songModel, BindingResult bindingResult, Model model) {

		// Check for validation errors
		if (bindingResult.hasErrors()) {
			return "song";
		}
		
		SongEntity newSong = new SongEntity(
				songModel.getId(), 
				songModel.getTitle(), 
				songModel.getAlbum(), 
				songModel.getArtist(), 
				songModel.getGenre(), 
				songModel.getUser());
		
		//If song is updated then update view
		if (data.update(newSong)) {
			model.addAttribute("result", "The song '" + newSong.getTitle() + "' by " + newSong.getArtist() + " was successfully updated!");
			model.addAttribute("songs", service.getSongs());
			model.addAttribute("songModel", new SongModel());
			model.addAttribute("isUpdate", false);
			
			return "library";
		}
		else {
			model.addAttribute("result", "The ID entered has been taken, please update ID and try again.");
			model.addAttribute("songs", service.getSongs());
			
			return "library";
		}
	}
}

