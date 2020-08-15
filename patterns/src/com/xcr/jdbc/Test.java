package com.xcr.jdbc;

public class Test {


    public static void main(String[] args) {
        Services.registerDefaultProvider("mysql", new MySqlDriver());
        Services.registerDefaultProvider("Oracle", new OracleDriver());
        Service mysql = Services.getInstance();
        Service oracle = Services.getInstance("Oracle");
        mysql.connection();
        oracle.connection();
    }
}
