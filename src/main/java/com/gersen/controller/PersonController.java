package com.gersen.controller;

import com.gersen.bean.Person;
import com.gersen.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/personnes")
    public ResponseEntity<List<Person>> getAllPersons(@RequestParam(required = false) String nom) {
        try {
            List<Person> personList = new ArrayList<Person>();

            if (nom == null)
                personRepository.findAll().forEach(personList::add);
            else
                personRepository.findByTitleContaining(nom).forEach(personList::add);

            if (personList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(personList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/personnes/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") long id) {

        Optional<Person> personData = personRepository.findById(id);

        if (personData.isPresent()) {
            return new ResponseEntity<>(personData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/personnes")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        try {
            Person addperson = personRepository
                    .save(new Person(person.getId(), person.getNom(), person.getPrenom()));
            return new ResponseEntity<>(addperson, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/personnes/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") long id, @RequestBody Person person) {
        Optional<Person> personData = personRepository.findById(id);

        if (personData.isPresent()) {
            Person _person = personData.get();
            _person.setNom(person.getNom());
            _person.setPrenom(person.getPrenom());
            return new ResponseEntity<>(personRepository.save(_person), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/personnes/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable("id") long id) {
        try {
            personRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/personnes")
    public ResponseEntity<Person> deleteAllPerson() {
        try {
            personRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
/*
    @GetMapping("/personnes/published")
    public ResponseEntity<List<Person>> findByName() {
        try {
            List<Person> persons = personRepository.findByPublished(true);

            if (persons.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(persons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
}

