package com.xcr.serializable.demo01;

import java.io.Serializable;

/**
 * @author xia
 */
public class Student implements Serializable {


    private String name;
    private Integer age;

    public Student() {}

    public Student(String name, Integer age) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
