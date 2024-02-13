package com.gcu.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.data.UserDataService;
import com.gcu.data.entity.UserEntity;
import com.gcu.models.*;

@Controller
@RequestMapping("/")
public class RegistrationController {
	
	@Autowired
	private UserDataService data;
	
	/**
	 * Simply add a UserModel and return the view of register page
	 * @param model Model to bind to the view
	 * @return View name register
	 */
	@GetMapping("register")
	public String load(Model model) {
		model.addAttribute("userModel", new UserModel());
		return "register";
	}
	
	/**
	 * When user submits form, bind input to UserModel, check for errors, add attribute, 
	 * register the user and then return the appropriate view
	 * @param userModel UserModel to bind to the view
	 * @param bindingResult Result of model/view binding, used to determine whether there were errors
	 * @param model Model to bind to the view
	 * @return View name register or home, depending on result
	 */
	@PostMapping("/doRegister")
	public String doRegister(@Valid UserModel userModel, BindingResult bindingResult, Model model) {

		// Check for validation errors
		if (bindingResult.hasErrors()) {
			return "register";
		}
		
		//see if user already exists if not create one
		if(data.findByUsername(userModel.getUsername())== null) {
			//Encoded the password and creates a user entity
			// BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encryptedPassword = userModel.getPassword();
			
			System.out.println("New user logged.");
			
			UserEntity newUser = new UserEntity(
					userModel.getUsername(),
					userModel.getFirstName(), 
					userModel.getLastName(),
					userModel.getEmail(),
					encryptedPassword,
					userModel.getPhoneNumber());
			
			//checks if user was successfully created and returns the view
			if (data.create(newUser)) {
				model.addAttribute("result", "Thanks for registering, " + userModel.getUsername() + "!" + " Login to gain access to your new account!");
				return "login";
			}
		}else {
			model.addAttribute("result", "The username entered has been taken, please update and try again.");
			
		}
		return "register";
	}
}
