package com.xcr.jdbc;

public class OracleDriver implements ServiceProvider {

    @Override
    public Service newService() {
        System.out.println("获取到OracleDriver");
        return new OracleService();
    }
}
