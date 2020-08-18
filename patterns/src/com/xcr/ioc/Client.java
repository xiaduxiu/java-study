package com.xcr.ioc;

public class Client {

    public static void main(String[] args) throws Exception {

        System.out.println(SpringIOC.class.getClassLoader().getResource("com/xcr/ioc/ioc.xml").getFile());

        String location = SpringIOC.class.getClassLoader().getResource("com/xcr/ioc/ioc.xml").getFile();

        SpringIOC springIOC = new SpringIOC(location);

        Wheel wheel = (Wheel) springIOC.getBean("wheel");
        System.out.println(wheel);

        Car car = (Car) springIOC.getBean("car");
        System.out.println(car);

    }
}
