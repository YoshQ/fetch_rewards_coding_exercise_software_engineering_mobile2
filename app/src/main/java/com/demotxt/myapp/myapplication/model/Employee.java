package com.demotxt.myapp.myapplication.model;

/**
 * Created by JS on 3/20/2023.
 */

public class Employee {

    private String name;
    //private int id;
    private String id;

    public Employee() {
    }

    //public Employee(String name, int id) {
    public Employee(String name, String id) {
        this.name = name;
        this.id = id;
    }


    public String getName() {
        return name;
    }
    //public int getId() {
    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    //public void setId(int id) {
    public void setId(String id) {
        this.id = id;
    }
}
