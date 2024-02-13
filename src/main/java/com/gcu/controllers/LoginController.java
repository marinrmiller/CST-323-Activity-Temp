package com.gcu.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {

	/**
	 * Simply invalidates the session if login page is accessed and return the view of login
	 * @param model Model to bind to the view
	 * @return View name login
	 */
	@GetMapping("login")
	public String load(HttpSession session, Model model) {
		//invalidates the session if login fails and returns to login page
		session.invalidate();
		return "login";
	}
}
