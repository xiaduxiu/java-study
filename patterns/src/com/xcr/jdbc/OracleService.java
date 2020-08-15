package com.xcr.jdbc;

public class OracleService implements Service{


    @Override
    public void connection() {
        System.out.println("这里是Oracle连接");
    }
}
