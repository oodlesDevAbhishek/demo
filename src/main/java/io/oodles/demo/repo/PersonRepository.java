package io.oodles.demo.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.oodles.demo.modles.Person;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {

	Optional<Person> findByEmail(String email);
	//provides methods to work with database
}

