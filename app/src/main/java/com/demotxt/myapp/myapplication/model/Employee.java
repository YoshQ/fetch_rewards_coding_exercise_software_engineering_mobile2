package com.demotxt.myapp.myapplication.model;

/**
 * Created by JS on 3/20/2023.
 */

public class Employee {

    private String name;
    //private int id;
    private String id;
    private String listId;

    public Employee() {
    }

    //public Employee(String name, int id) {
    //public Employee(String name, String id) {
    public Employee(String name, String id, String listId) {
        this.name = name;
        this.id = id;
        this.listId = listId;
    }


    public String getName() {
        return name;
    }
    //public int getId() {
    public String getId() {
        return id;
    }
    public String getListId() {
        return listId;
    }

    public void setName(String name) {
        this.name = name;
    }
    //public void setId(int id) {
    public void setId(String id) {
        this.id = id;
    }
    public void setListId(String listId) {
        this.listId = listId;
    }
}
