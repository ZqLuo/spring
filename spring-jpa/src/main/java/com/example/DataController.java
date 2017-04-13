package com.example;

import com.example.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    private DemoService demoService;

    @RequestMapping("/save")
    public Person save(String name,String address,Integer age){
        Person p = personRepository.save(new Person(null,name,age,address));
        return p;
    }

    /**
     * 测试分页
     * @return
     */
    @RequestMapping("/page")
    public Page<Person> page(){
        Page<Person> pagePeople = personRepository.findAll(new PageRequest(1,2));
        return pagePeople;
    }

    /**
     * 排序
     * @return
     */
    @RequestMapping("/order")
    public List<Person> order(){
        List<Person> people = personRepository.findAll(new Sort(Sort.Direction.ASC,"age"));
        return people;
    }

    @RequestMapping("/rollBack")
    public Person rollBack(Person person){
        return demoService.savePersonWithRollBack(person);
    }

    @RequestMapping("/noRollBack")
    public Person noRollBack(Person person){
        return demoService.savePersonWithoutRollBack(person);
    }
}
