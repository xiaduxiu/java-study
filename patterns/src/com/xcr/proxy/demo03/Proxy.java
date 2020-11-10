package com.xcr.proxy.demo03;

public class Proxy {

    private House house;

    public Proxy(House house) {
        this.house = house;
    }

    public void rent() {
        house.rent();
    }

}
