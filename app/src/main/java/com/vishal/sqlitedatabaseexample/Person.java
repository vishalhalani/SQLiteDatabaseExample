package com.vishal.sqlitedatabaseexample;

/**
 * Created by vishal.halani on 15-Mar-18.
 *
 */

public class Person {
    private int id;
    private String name;
    private int age;
    private String mobileNumber;
    private String gender;


    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Person(int id, String name, int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Person(String name, int age, String mobileNumber, String gender) {
        this.name = name;
        this.age = age;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
    }

    public Person(int id, String name, int age, String mobileNumber, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
