package com.xcr.factory.simple_factory;

public class Client {
    /**
     * 适用于多个工厂生产不同的东西。这个东西是一个整体，而不是部分。
     * @param args
     */
    public static void main(String[] args) {
        ChineseFoodFactory chineseFoodFactory = new ChineseFoodFactory();
        Food a = chineseFoodFactory.makeFood("A");

    }
}
