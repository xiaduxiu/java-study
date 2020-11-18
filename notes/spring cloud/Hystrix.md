#### Hystrix

1. pom引入

   ```java
   <dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
   </dependency>
   ```

2. 增加注解 @SpringCloudApplication

   @SpringCloudApplication 注解是一个组合注解，整合了 								@SpringBootApplication 

   @EnableDiscoveryClient 

   @EnableCircuitBreaker 

3. 配置

#### Hystrix 实现服务隔离

HystrixCommand抽象类是核心类，拥有run方法和getfallback方法。run方法实现服务容错的业务逻辑，getfallback方法用于在HystrixCommand子类中设置服务回退函数的具体实现。

可以通过实现HystrixCommand子类来是西安线程池隔离，这种方法过于繁琐，推荐使用注解 @HstrixCommmand来实现。只需要在调用的方法上加注解就行，设置分组、服务与线程池名称相关的 groupKey、commandKey 和 threadPoolKey方法，线程池属性相关的 threadPoolProperties 对象都包含在其中。

#### Hystrix实现服务回退

只需要在fallback方法上加 注解@HystrixCommand(fallbackMethod = "getInterventionsFallback") 配置好fallback要调用的方法名称就能进行服务回退。同时fallback方法的参数和返回值必须和真实的方法一致。



