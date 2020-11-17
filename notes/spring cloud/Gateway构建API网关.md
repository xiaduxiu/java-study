#### Spring Cloud Gateway 

zuul本质上是对servlet的封装，通信方式使用的是阻塞式I/O。而gateway是spring cloud自研的，使用的通信方式是非阻塞式I/O，同时不仅提供服务路由功能，还提供请求限流等功能，还能和hystrix等框架良好集成。

1. 引入依赖

   ```java
   <dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-starter-gateway</artifactId>
   </dependency>
   ```

2. 增加注解 @EnableDiacoveryClient

3. 配置

   ```java
   spring:
     cloud:
   	gateway:
         routes:
         - id: testroute
           uri: lb://testservice
           predicates:
           - Path=/test/**
           filters:
           - PrefixPath=/prefix
   ```

   id: 路由信息编号

   uri: lb->负载均衡loadbalance

   path=/test/* 所有以/test开头的请求都会被路由到 /testservice,

   prefixoath=/prefix 表示增加前缀 prefix


#### gateway 基本架构

有两个核心的组件 Filter(过滤器) 和 predicate（谓词） 。filter跟zuul中的filter功能相同，都是用于处理http请求之前或者之后修改请求本身，以及对应响应结果。 predicate 谓词 其实是一种判断条件，用于将http请求和路由进行匹配。谓词可以通过 http请求的请求头、请求路径等路由媒介自动匹配决定路由结果。

可以使用全局过滤器（GlobalFilter）对所有 HTTP 请求进行拦截。

gateway提供两种过滤器， pre 和 post。 PostGatewayFilter 的实现： 继承AbstractGatewayFilterFactory类， 通过覆写 apply方法实现对servlethttpresponse对象的操作。

#### 请求限流

gateway 提供请求限流过滤器 RequestRateLimiter，实现请求限流。限流的实现方式一般是衡量请求处理的速率并对其控制。 RequestRateLimiter 提供两个参数，第一个参数 replenisRate ,指定每秒处理用户的请求数，相当于控制水流速度， 第二个参数 burstCapacity ，设置一秒内允许的最大请求数，相当于控制容器大小。

```
spring:
  cloud:
    gateway:
	   routes:
	      - id: requestratelimiterroute
            uri: lb://interventionservice
	        filters:
	           - name: RequestRateLimiter
	             args:
	                redis-rate-limiter.replenishRate: 50
	                redis-rate-limiter.burstCapacity: 100
```

请求限流过滤器依赖redis，所以需要引入 spring-boot-starter-data-redis-reactive 支持响应式redis的依赖。

