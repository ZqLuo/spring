package com.lxg.spring.entity;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "s_role")
public class SRole implements java.io.Serializable {

    @Id
    @GeneratedValue
    private Integer id;

   // private SUser SUser;

    private String name;



    public SRole() {

    }





    public Integer getId() {

       return this.id;

    }



    public void setId(Integer id) {

       this.id = id;

    }






    public String getName() {

       return this.name;

    }



    public void setName(String name) {

       this.name = name;

    }



}