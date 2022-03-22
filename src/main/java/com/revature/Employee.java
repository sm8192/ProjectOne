package com.revature;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employee {

    @Id
    int id;
    String username;
    String password;
    String name;
    String email;
    boolean administrator;

    Employee(){}

    Employee(String username,String password, String name, String email, boolean administrator){
        this.username=username;
        this.name=name;
        this.password=password;
        this.email=email;
        this.administrator=administrator;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", administrator=" + administrator +
                '}';
    }
}
