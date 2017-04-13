package com.example.service.impl;

import com.example.Person;
import com.example.PersonRepository;
import com.example.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    PersonRepository personRepository;

    @Override
    @Transactional(rollbackFor = {IllegalArgumentException.class})
    public Person savePersonWithRollBack(Person person) {
        Person p = personRepository.save(person);
        if("XX".equals(p.getName())){
            throw new IllegalArgumentException("XX,已存在，数据回滚");
        }
        return p;
    }

    @Override
    @Transactional(noRollbackFor = {IllegalArgumentException.class})
    public Person savePersonWithoutRollBack(Person person) {
        Person p = personRepository.save(person);
        if("XX".equals(p.getName())){
            throw new IllegalArgumentException("XX,已存在，数据回滚");
        }
        return p;
    }
}
