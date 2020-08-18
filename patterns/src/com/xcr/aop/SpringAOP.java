package com.xcr.aop;

import com.xcr.proxy.demo02.ProxyInvocationHandler;

import java.lang.reflect.Proxy;

public class SpringAOP {

    public static Object getProxy(Object bean, Advice advice) {
      return  Proxy.newProxyInstance(SpringAOP.class.getClassLoader(), bean.getClass().getInterfaces(), advice);
    }

}
