package com.xcr.proxy.demo04;

public class Test {
    public static void main(String[] args) {
        House house = new House();

        ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler();
        proxyInvocationHandler.setTarget(house);
        Rent proxy = (Rent)proxyInvocationHandler.getProxy();
        proxy.rent();

    }
}
