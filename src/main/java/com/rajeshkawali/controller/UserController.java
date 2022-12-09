package com.rajeshkawali.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajeshkawali.model.UserDetails;
import com.rajeshkawali.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rajesh_Kawali
 *
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

	public static final String CLASS_NAME = UserController.class.getName();

	@Autowired
	private UserService userService;
	
	@PostMapping("/user")
	public void create(@RequestBody UserDetails userDetails) {
		String _function = ".create()";
		log.info(CLASS_NAME + _function + "::ENTER");
		log.debug(CLASS_NAME + _function + "::userDetails: {}", userDetails.toString());
		userService.publish(userDetails);
		log.info(CLASS_NAME + _function + "::EXIT");
	}
}
