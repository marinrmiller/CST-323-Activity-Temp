package com.gcu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	/**
	 * Simply return the view of the home page
	 * @param model
	 * @return View name home
	 */
	@GetMapping("/")
	public String load(Model model) {
		model.addAttribute("welcome", "The music streaming & management service for you!"); //Header
		model.addAttribute("result", "Welcome to Sound Found, your ultimate destination for personalized music playlist management. At Sound Found, we believe that music is not just a soundtrack but a journey that resonates with your unique tastes and moods. Our mission is to empower music enthusiasts like you to curate and manage your playlists the way you want, creating a harmonious symphony of your life's soundtrack.");
		return "home";
	}
}
