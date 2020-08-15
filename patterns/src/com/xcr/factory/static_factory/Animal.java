package com.xcr.factory.static_factory;

public class Animal {
    protected String name;
    public Animal() {}
    public Animal (String name) {
        this.name = name;
    }

    public static Animal getAnimal(String type) {
        switch (type) {
            case "dog": return new Dog();
            default: return new Cat();
        }
    }
}
