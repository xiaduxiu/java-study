#### 实现服务注册

maven中添加eureka客户端组件的依赖：

```java
<dependency>
     <groupId>org.springframework.cloud</groupId>
     <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

添加注解 @EnableEurekaClient

```java
@SpringBootApplication
@EnableEurekaClient
public class UserApplication {
	public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
```

该注解用于表明当前服务就是一个 Eureka 客户端，这样该服务就可以自动注册到 Eureka 服务器。

进行配置

```java
spring:
  application:
	name: userservice 
server:
  port: 8081
eureka:
  client:
    serviceUrl:
	  defaultZone: http://localhost:8761/eureka/
```

启动服务后，可以访问 http://localhost:8761/eureka 查看到已经有服务实例注册。 同时可以通过 访问 http://localhost:8761/eureka/apps/userservice 获取到 某个具体服务实例的详细信息。

#### 理解eureka客户端基本原理

eureka专门抽象出了一个客户端的接口 EurekaClient，此接口继承自 LookUpService 接口。在EurekaClient接口有一个实现类DiscoveryClient，此类包含服务提供者和服务消费者的核心处理逻辑，同时提供了服务端的register、renew等方法。

#####  服务提供者操作源码解析

服务提供者关注服务注册、服务续约、服务剔除等功能。此处解析注册操作，也就是register方法，核心代码：

```java

```

上面的register方法在InstanceInfoReplicator类的run方法种执行。其中的重点代码是 eurekaTransport.registrationClient.register(),DiscoveryClient 通过这行代码发起远程请求。

EurekaTransport类是DiscoveryClient类的一个内部类，定义了registrationClient变量用于实现服务注册。registrationClient的类型是EurekaHttpClient接口。 EurekaHttpClient接口的定义：

```java

```

从上面可以看出EurekaHttpClient接口定义了一些底层的REST API。

EurekaHttpClientDecorator 定义一个抽象方法execute(RequestExecutor requestExecutor) 来包装EurekaHttpClient。

##### 如何创建一个EurekaHttpClient

eureka中提供了EurekaHttpClientFactory工厂类来负责构建具体的EurekaHttpClient， 通过包装器对请求过程进行层层封装和代理。EurekaHttpClientFactory 的实现类 包括 RetryableEurekaHttpClient 和 MeticsCollectingEurekaHttpClient等，还存在一个EurekaHttpClients工具类，能创建通过 RedirectingEurekaHttpClient、ErtryableEurekaHttpClient 、SessionedEurekaHttpClient包装之后的EurekaHttpClient。

**在执行远程请求时，Eureka通过TransportClientFactory创建EurekaHttpClient，来完成真正的远程调用。**

TransportClientFactory 同样存在一批实现类，其中有些是实名类，有些是匿名类。以实名的实现类 JerseyEurekaHttpClientFactory ，通过 EurekaJerseyClient 获取 Jersey 客户端，而 EurekaJerseyClient 又会使用 ApacheHttpClient4 对象，从而完成 REST 调用。

####  服务消费者源码解析

作为服务消费者客户端是如何保证缓存的同步问题，在 DiscoveryHttpClient 中有一个方法 initSchduledTasks() 其中会生成一个调度任务，用于缓存数据的刷新。这个调度任务会通过CacheRefreshThread线程进行具体操作。线程中run方法调用refreshRegistry()方法，这个方法经过校验之后会调用fetchRegistry方法完成注册信息的更新。

fetchRegistry方法的流程如下：

```java

```

基本的调用流程：initSchduledTasks --> CacheRefreshThread -->  refreshRegistry -->  fetchRegistry
eureka服务端会保留服务注册列表的缓存，缓存存活时间是3分钟，而eureka客户端的定时调度每隔30秒会对本地的服务列表缓存进行更新。理想情况下，只要客户端一直去服务端获取最新数据就能保证客户端和服务端数据一致，但是如果客户端在3分钟内没有及时进行更新数据，就会导致自身和服务器的数据不一致。

为了解决上面的情况，采用一致性哈希值的方法。服务端每次返回的增量数据中都带有一个一致性哈希值，这个哈希值会跟本地的哈希值进行比对，如果不一致，说明服务端更新出现问题，需要执行一次全量更新。

![](C:\Users\xia\AppData\Roaming\Typora\typora-user-images\1605010834167.png)

