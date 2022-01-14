package io.oodles.demo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.oodles.demo.modles.Person;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {
//provides methods to work with database
}

