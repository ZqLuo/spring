package com.example;

import com.example.entity.Person;
import com.example.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zqLuo
 */
@RestController
public class CachaController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/put")
    public Person put(Person person){
        return demoService.save(person);
    }

    @RequestMapping("/able")
    public Person able(Person person){
        return demoService.findOne(person);
    }

    @RequestMapping("/evid")
    public String evid(Long id){
        demoService.remove(id);
        return "OK";
    }
}
