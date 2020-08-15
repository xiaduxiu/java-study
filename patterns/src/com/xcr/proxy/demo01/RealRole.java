package com.xcr.proxy.demo01;

public class RealRole implements AbstractCommonInterface {
    @Override
    public void common() {
        System.out.println("真实角色的公共方法");
    }
}
