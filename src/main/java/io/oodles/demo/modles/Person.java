package io.oodles.demo.modles;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

//@entity if data add in sqldb then add jpa dep.  starter
@Document(collection = "oodlesPerson") //woring with mongodb database use documents
public @Data class Person {
	@Id
	private String id;
	private String name;
	private String email;
	private String gender;
	private String phone;
	private double income;
	private String password;
	private int otp;
}