package io.oodles.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.oodles.demo.modles.Person;
import io.oodles.demo.repo.PersonRepository;
@RestController//Restcontroller =Contorller+ResponseBody
@RequestMapping("/api/personinfo")

public class PersonController {
	
	@Value("${appName : default value}")
	private String str ;
	
	@Autowired
	private PersonRepository personrepo ;
	
	
	//Read Handler 
	@GetMapping("/showpersoninfo")//is the combination of @ResquestMapping(value="/showpersoninfo" ,method=RequestMethod.GET)
	public ResponseEntity <?> getAllPerson(){
		
		List<Person> personList = personrepo.findAll();
		
		if(personList.size() > 0) {
			return new ResponseEntity<List<Person>>(personList ,HttpStatus.OK);
		}
		else
			return new ResponseEntity<>("NO RECORD FOUND" ,HttpStatus.NOT_FOUND);	
	}
	
	
	//Read Handler By Id
	@GetMapping("/showpersoninfo/{id}")
	public ResponseEntity<?> getPersonById(@PathVariable("id") String id){
		Optional<Person> p =  personrepo.findById(id);
		if(p.isPresent()) {
			return new ResponseEntity<>(p.get(),HttpStatus.OK);	
		}
		else
			return new ResponseEntity<>("Record Not Found "+id+"...pls try again !",HttpStatus.NOT_FOUND);
		
	}
	
	//Create Handler 
	@PostMapping("/addpersoninfo")
	public ResponseEntity <?> addPersonInfo(@RequestBody Person p){
		try {
			personrepo.save(p);
			return new ResponseEntity<Person>(p , HttpStatus.CREATED);
		}
		catch(Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	//Update Handler
	@PutMapping("/updatepersoninfo/{id}")
	public ResponseEntity <?> updatePersonInfo(@RequestBody Person person ,@PathVariable("id") String id){
		Optional<Person> p = personrepo.findById(id);
		if(p.isPresent()) {
			Person updatePerson = p.get();
			updatePerson.setName(person.getName() != null ? person.getName() : updatePerson.getName());
			updatePerson.setEmail(person.getEmail() != null ? person.getEmail() :updatePerson.getEmail());
			updatePerson.setGender(person.getGender() != null ? person.getGender() :updatePerson.getEmail());
			updatePerson.setPhone(person.getPhone() != null ? person.getPhone() :updatePerson.getPhone());
			updatePerson.setIncome(person.getIncome() > 0 ? person.getIncome(): updatePerson.getIncome());
		    personrepo.save(updatePerson);
		    return new ResponseEntity<Person>(updatePerson ,HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<>("Person not found with id "+id ,HttpStatus.NOT_FOUND);
	    }
		
	}
	
	
	//Delete Handler by Id
	@DeleteMapping("/deletepersoninfo/{id}")
	public ResponseEntity<?> deletePersonById(@PathVariable("id") String id){
		try {
			Optional<Person> p =personrepo.findById(id);
			if(p.isPresent()) {
			personrepo.deleteById(id);
			return new ResponseEntity<>("Successfully Deleted with Id"+id , HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("Record not present with Id"+id , HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception ex) {
			return new ResponseEntity<>(ex.getMessage() , HttpStatus.NOT_FOUND);
	}
	
	}	
	
	
}
