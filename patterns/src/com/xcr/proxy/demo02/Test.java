package com.xcr.proxy.demo02;

public class Test {

    public static void main(String[] args) {
        RealRole realRole = new RealRole();

        ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler();
        proxyInvocationHandler.setTarget(realRole);
        AbstractCommonInterface proxy = (AbstractCommonInterface) proxyInvocationHandler.getProxy();
        proxy.common();

    }
}
