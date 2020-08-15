package com.xcr.proxy.demo01;

public class Test {
    public static void main(String[] args) {
        RealRole realRole = new RealRole();

        ProxyRole proxyRole = new ProxyRole();
        proxyRole.setAbstractCommonInterface(realRole);
        proxyRole.common();

    }
}
