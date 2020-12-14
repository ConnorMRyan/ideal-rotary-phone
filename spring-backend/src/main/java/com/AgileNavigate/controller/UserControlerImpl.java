package com.AgileNavigate.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.AgileNavigate.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@Controller("userController")
public class UserControlerImpl implements UserController {

	@Autowired
	private UserService userService;

	@PostMapping("api/signup")
	 @ResponseBody
	public String signup(HttpServletRequest req){
        return userService.signup(req);
	}

	@PostMapping("api/signin")
	 @ResponseBody
	public String signin(HttpServletRequest req) {
		
		return userService.signin(req);
	}

	@GetMapping("api/signout")
	public String signout(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@PostMapping("api/forgotPass")
	@ResponseBody
	public String forgotPassword(HttpServletRequest req) {
		return userService.forgotPass(req);
	}

	@PostMapping("api/getUser")
	@ResponseBody
	public String getUser(HttpServletRequest req) {
		
		return userService.getUser(req);
		
	}
	
	@PutMapping("api/updateUser")
	 @ResponseBody
	public String updateUser(HttpServletRequest req) {
		
		return userService.updateUser(req);		
	}

	@GetMapping("api/getAllUsers")
	@ResponseBody
	public String getAllUsers() {
// 		return userService.getAllUsers();
        return null;
	}

	@PostMapping("api/searchUser")
	@ResponseBody
	public String getAllUsers(HttpServletRequest req) {

		// return userService.getAllUsers(req);
		return null;

	}
	
	
	

// 	@PostMapping("api/searchUser")
// 	public @ResponseBody String getAllUsers(HttpServletRequest req) {
// 		System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
// 		return userService.getAllUsers(req);
// 	}

}
