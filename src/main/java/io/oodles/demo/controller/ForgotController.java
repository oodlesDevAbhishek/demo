package io.oodles.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.oodles.demo.modles.Person;
import io.oodles.demo.services.ForgotServices;

@RestController
public class ForgotController{
	
	
	
	@Autowired
	 ForgotServices forgotservices;

	//Handler for verify email  and generate 6digit otp
	@GetMapping("/forgot/{emailId}")
	public ResponseEntity<?> forgotPassword(@PathVariable("emailId")String emailId){
		
		  boolean result = forgotservices.forgotPassword(emailId);
		  if(result) {
			  return new ResponseEntity<>(result,HttpStatus.OK);
		  }
		  else {
			  return new ResponseEntity<>("Email Not Found in DataBase !",HttpStatus.NOT_FOUND);
		  }
	}
	
	//update new password handler 
	@PutMapping("/forgot/{emailId}")
	public ResponseEntity<?> updatePassword(@RequestBody Person person ,@PathVariable("emailId")String emailId ){
		
		  boolean result = forgotservices.updatePassword(person , emailId);
		  if(result) {
			  return new ResponseEntity<>(result,HttpStatus.OK);
		  }
		  else {
			  return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
		  }
	}
	
}