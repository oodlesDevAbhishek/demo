package io.oodles.demo.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.oodles.demo.modles.Person;
import io.oodles.demo.repo.PersonRepository;
@Service
public class ForgotServices {
	
	@Autowired
	private PersonRepository personrepo;
	
	@Autowired
	private EmailService emailservice;
	

	

	public boolean forgotPassword(String emailId) {
		Optional<Person> ps = personrepo.findByEmail(emailId);// cheack email is present in DB
		if(ps.isPresent())
		{
			//save otp in db and send otp email
			Person updatePerson = ps.get();
			int otp = emailservice.optGen();
			updatePerson.setOtp(otp);
			personrepo.save(updatePerson);
			
			return emailservice.sendEmail(emailId,otp);	
		}
	
		return  false;
	}


	public boolean updatePassword(Person person, String emailId) {
		Optional<Person> ps = personrepo.findByEmail(emailId);// cheack email is present in DB
		if(ps.isPresent()) {
			Person updatePerson = ps.get();
			
			//check user otp  if otp is correct then update new password and remove genotp key and send confirmation mail. 
			
			if(updatePerson.getOtp()==person.getOtp()) {
				updatePerson.setPassword(person.getPassword());
				updatePerson.setOtp(0);
				personrepo.save(updatePerson);
				return emailservice.sendEmail(emailId);
				
			}
			
			else
			{
				System.out.println("pls enter correct password ..!");
				return false;
			}
		}
		return false;
	}
	
}
