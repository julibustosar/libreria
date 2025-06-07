package com.trumpxmusk.libreriadelibros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trumpxmusk.libreriadelibros.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByNameContainingIgnoreCase(String name);
    
    Optional<Person> findByName(String name);
    
    List<Person> findByBirthYearLessThanAndDeathYearGreaterThanOrDeathYearIsNull(Integer year, Integer sameYear);
    
    List<Person> findByBirthYearBetween(Integer startYear, Integer endYear);
    
    List<Person> findByDeathYearGreaterThan(Integer year);
    
    List<Person> findByBirthYearLessThan(Integer year);
    
    List<Person> findByDeathYearIsNull();

} 