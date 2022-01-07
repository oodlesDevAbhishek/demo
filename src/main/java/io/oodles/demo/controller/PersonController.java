package io.oodles.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.oodles.demo.modles.Person;
import io.oodles.demo.repo.PersonRepository;
@RestController
public class PersonController {
	@Autowired
	
	private PersonRepository personrepo;
	
	
	@GetMapping("/showpersoninfo")
	public ResponseEntity <?> getAllPerson(){
		
		List<Person> personList = personrepo.findAll();
		
		if(personList.size() > 0) {
			return new ResponseEntity<List<Person>>(personList ,HttpStatus.OK);
		}
		else
			return new ResponseEntity<>("NO RECORD FOUND" ,HttpStatus.NOT_FOUND);	
	}
	
	@PostMapping("/addpersoninfo")
	public ResponseEntity <?> addPersonInfo(@RequestBody Person p){
		try {
			personrepo.save(p);
			return new ResponseEntity<Person>(p,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
}
