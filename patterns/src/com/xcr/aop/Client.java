package com.xcr.aop;

public class Client {

    public static void main(String[] args) {
        MethodInvocation logStart = () -> System.out.println("log task start");
        MethodInvocation logEnd = () -> System.out.println("log task end");

        HelloServiceImpl helloService = new HelloServiceImpl();

        BeforeAdvice beforeAdvice = new BeforeAdvice(helloService, logStart);
        AfterAdvice afterAdvice = new AfterAdvice(helloService, logEnd);

        HelloService beforeproxy = (HelloService) SpringAOP.getProxy(helloService, beforeAdvice);
        HelloService afterproxy = (HelloService) SpringAOP.getProxy(helloService, afterAdvice);

        beforeproxy.sayHello();
        afterproxy.sayHello();


    }


}
