package com.xcr.serializable.demo01;

import java.io.*;

/**
 * @author xia
 */
public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student student = new Student("123", 10);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("student"));
        objectOutputStream.writeObject(student);
        objectOutputStream.close();

        ObjectInputStream studet = new ObjectInputStream(new FileInputStream("student"));
        Student object = (Student)studet.readObject();
        System.out.println(object);
        assert "123".equals(object.getName());
    }
}
