package com.example.validation.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.validation.passtvalidator.PasswordConstraintValidator;


@Service
@RestController
public class PassController {
	
	
	
	@Autowired
	PasswordConstraintValidator passwordConstraintValidator;
	
	    @PostMapping("/login")
	    public Map<String,Object> a(@RequestParam String passwordData){
	    	
	    	Map<String, Object> map = new LinkedHashMap<String, Object>();
	    	map.put("Response", passwordConstraintValidator.passay(passwordData));
			return map;
	    	
	    
		
}
}