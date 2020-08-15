package com.xcr.proxy.demo01;

public class ProxyRole {

    private AbstractCommonInterface abstractCommonInterface;

    public void setAbstractCommonInterface(AbstractCommonInterface abstractCommonInterface) {
        this.abstractCommonInterface = abstractCommonInterface;
    }

    public void common() {
        abstractCommonInterface.common();
    }

}
