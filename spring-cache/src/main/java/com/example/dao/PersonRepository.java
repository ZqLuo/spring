package com.example.dao;


import com.example.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface PersonRepository extends JpaRepository<Person,Long>{

}
