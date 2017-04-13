package com.example.service;

import com.example.entity.Person;

public interface DemoService {

    Person save(Person p);

    void remove(Long id);

    Person findOne(Person person);

}
