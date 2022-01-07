package io.oodles.demo.modles;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Document(collection = "oodlesPerson") //woring with mongodb database use documents
public @Data class Person {
	@Id
	private String id;
	private String name;
	private String email;
	private String gender;
	private String phone;
	private double income;
}