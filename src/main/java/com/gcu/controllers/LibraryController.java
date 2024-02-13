package com.gcu.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.gcu.business.SongsBusinessService;
import com.gcu.data.SongDataService;
import com.gcu.models.SongModel;

@Controller
public class LibraryController {
	
	@Autowired
	private SongsBusinessService service;
	
	@Autowired
	private SongDataService data;

	
	/**
	 * Simply return the view of the library
	 * @param model
	 * @return View name library
	 */
	@GetMapping("/library")
	public String load(Model model) {
		
		model.addAttribute("title", "My Songs");
		model.addAttribute("songs", service.getSongs());
		model.addAttribute("songModel", new SongModel());
		
		return "library";
	}
	
	/**
	 * When user submits form, bind input to SongModel, check for errors, add attribute, and tries to delete the song and returns the appropriate view
	 * @param songModel SongModel to bind to the view
	 * @param bindingResult Result of model/view binding, used to determine whether there were errors
	 * @param model Model to bind to the view
	 * @return View name library
	 */
	@PostMapping("/deleteSong")
	public String deleteSong(@Valid SongModel songModel, BindingResult bindingResult, Model model) {
		
		// Check for validation errors
		if (bindingResult.hasFieldErrors("id")) {
			model.addAttribute("songs", service.getSongs());
			return "library";
		}
		try {
			//checks if the data was deleted
			if (data.delete(songModel.getId())) {
				
				model.addAttribute("result", "The song with ID '" + songModel.getId() + "' was successfully deleted!");
				model.addAttribute("songs", service.getSongs());
				model.addAttribute("songModel", new SongModel());
				
				return "library";
			} else {
				model.addAttribute("error", "No song with that ID was found. Nothing has been deleted.");
			}
		}catch(Exception e) {
			model.addAttribute("error", "No song with that ID was found. Nothing has been deleted.");
		}
		
		model.addAttribute("songs", service.getSongs());
		return "library";
	}
	
	/**
	 * When user submits form, bind input to SongModel, check for errors, add attribute, and tries
	 *  to get song the user wants to update and then returns the appropriate view
	 * @param songModel SongModel to bind to the view
	 * @param bindingResult Result of model/view binding, used to determine whether there were errors
	 * @param model Model to bind to the view
	 * @return View name updatesong or home, depending on result
	 */
	@PostMapping("/updateSongForm")
	public String updateSong(@Valid SongModel songModel, BindingResult bindingResult, Model model) {
		
		// Check for validation errors
		if (bindingResult.hasFieldErrors("id")) {
			model.addAttribute("error", "Error - unable to find song ID. ");
			model.addAttribute("songs", service.getSongs());
			model.addAttribute("isUpdate", false);
			return "library";
		} 

		try{
			//gets song with provided id
			SongModel newSong = service.getSongById(songModel.getId());

			if(newSong != null) {
				if(newSong.getId()>0) {
					//gets song with provided id
					newSong = service.getSongById(songModel.getId());
					
					model.addAttribute("songModel", newSong);
					model.addAttribute("title", "Update song:");
					
					//lets html know which link to redirect to when submit is pressed
					model.addAttribute("isUpdate", true);
					return "song";
				}
			}else {
				model.addAttribute("error", "Error - unable to find song ID. ");
				model.addAttribute("songs", service.getSongs());
				return "library";
			}

		}catch(Exception e) {
			model.addAttribute("error", "Error - unable to find song ID. ");
		}
		
		return "library";
	}
	
}
