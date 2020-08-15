package com.xcr.jdbc;

public class MySqlDriver implements ServiceProvider {

    @Override
    public Service newService() {
        System.out.println("获取到MySqlDriver");
        return new MySqlService();
    }
}
