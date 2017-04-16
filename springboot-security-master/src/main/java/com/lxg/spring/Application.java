package com.lxg.spring;

import org.springframework.boot.SpringApplication;

/**
 * Created by lxg
 */
@org.springframework.boot.autoconfigure.SpringBootApplication
public class Application {

    public static void main(String[] args){
        new SpringApplication(Application.class).run(args);
    }
}
