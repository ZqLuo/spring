package com.example.service;

import com.example.Person;

public interface DemoService {

    public Person savePersonWithRollBack(Person person);

    public Person savePersonWithoutRollBack(Person person);
}
