package com.xcr.factory.simple_factory;


public class ForeignFoodFactory implements FoodFactory {
    @Override
    public Food makeFood(String name) {
        if (name.equals("A")) {
            return new ForeignFoodA();
        } else if (name.equals("B")) {
            return new ForeignFoodB();
        } else {
            return null;
        }
    }
}
