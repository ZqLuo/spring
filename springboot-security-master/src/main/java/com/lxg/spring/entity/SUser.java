package com.lxg.spring.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "s_user")//code11
public class SUser implements java.io.Serializable {


    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String email;

    private String password;

    private Date dob;
    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    private Set<SRole> SRoles = new HashSet<SRole>(0);// Code12



    public SUser() {

    }



    public SUser(String name, String email, String password, Date dob, Set<SRole> SRoles) {

       this.name = name;

       this.email = email;

       this.password = password;

       this.dob = dob;

       this.SRoles = SRoles;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Set<SRole> getSRoles() {
        return SRoles;
    }

    public void setSRoles(Set<SRole> SRoles) {
        this.SRoles = SRoles;
    }
}
