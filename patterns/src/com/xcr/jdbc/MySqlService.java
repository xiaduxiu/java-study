package com.xcr.jdbc;

public class MySqlService implements Service{


    @Override
    public void connection() {
        System.out.println("这里是MySql连接");
    }
}
