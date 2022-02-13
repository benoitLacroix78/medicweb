package com.gersen.repository;

import com.gersen.bean.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Qualifier
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByTitleContaining(String nom);

}
