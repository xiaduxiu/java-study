package com.xcr.jdbc;

// 服务管理者

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Services {
    // 私有构造器，不允许实例化

    private Services() {}

    //服务提供者集合

    private static final Map<String, ServiceProvider> providers = new ConcurrentHashMap<>();

    // 默认的服务提供者名称

    private static final String DEFAULT_PROVIDER_NAME = "mysql";

    // 提供者注册API

    public static void registerDefaultProvider(ServiceProvider provider) {
        registerDefaultProvider(DEFAULT_PROVIDER_NAME, provider);
    }

    public static void registerDefaultProvider(String name, ServiceProvider provider) {
        providers.put(name, provider);
    }

    // 服务访问api

    public static Service getInstance() {
        return newInstance(DEFAULT_PROVIDER_NAME);
    }

    public static Service getInstance(String name) {
        return newInstance(name);
    }

    private static Service newInstance(String name) {
        ServiceProvider provider = providers.get(name);
        if (provider == null) {
            throw new IllegalArgumentException("没有名称：" + name + "的服务");
        }
        return provider.newService();
    }

}
