package com.xcr.proxy.demo03;

public class Test {
    public static void main(String[] args) {
        House house = new House();
        Proxy proxy = new Proxy(house);
        proxy.rent();
    }
}
