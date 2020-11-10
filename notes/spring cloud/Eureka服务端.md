#### 单个Eureka服务

1. maven导入依赖

   ```java
   <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
   </dependency>
   ```

2. 添加**@EurekaServerApplication**注解

   ```java
   @SpringBootApplication
   @EnableEurekaServer
   public class EurekaServerApplication {
   	public static void main(String[] args) {
   	       SpringApplication.run(EurekaServerApplication.class, args);
       }
   }
   ```

   @EnableEurekaServer 注解 表示此服务是一个Eureka服务器组件。

3. 进行配置

   ```java
   
   ```

   根据上图配置可知，以 eureka.client 开头的客户端配置项，registerWithEureka 用于指定是否把当前的客户端实例注册到 Eureka 服务器中，而 fetchRegistry 则用于指定是否从 Eureka 服务器上拉取已注册的服务信息。这两个配置项默认都是 true。这里设置为false，是因为一般不会把自己Eureka服务本身进行注册。

在配置文件中，关于eureka有三大类，一类控制Eureka服务端行为，以eureka.server开头，一类控制Eureka服务客户端，以 eureka.client 开头；最后一类是注册到 Eureka 的服务实例本身，以 eureka.instance 开头。



#### Eureka服务器集群

考虑单个Eureka存在单点失效问题，所以一般注册中心会搭建集群保证可用性。Eureka服务集群的搭建是把Eureka本身作为一个服务注册到另一个注册中心中去，从而构成集群。

准备两个 Eureka 服务实例 eureka1 和 eureka2。分别的配置文件为

```java
server:
  port: 8761
eureka:
  instance:
    hostname: eureka1
  client
    serviceUrl
	   defaultZone: http:// eureka2:8762/eureka/
```

```java
server:
  port: 8762
eureka:
  instance:
    hostname: eureka2
  client
    serviceUrl
	   defaultZone: http://eureka1:8761/eureka/
```

配置项 eureka.instance.hostname，用于指定当前 Eureka 服务的主机名称。构建 Eureka 集群模式的关键点在于使用客户端配置项 eureka.client.serviceUrl.defaultZone 用于指向集群中的其他 Eureka 服务器。所以 Eureka 集群的构建方式实际上就是将自己作为服务并向其他注册中心注册自己。

#### Eureka服务器实现原理

![](C:\Users\xia\AppData\Roaming\Typora\typora-user-images\1604663381184.png)

##### 上图细化Eureka的架构图。其中需要注意的是服务注册、服务续约、服务取消，以及获取注册信息。

服务注册时服务提供者内嵌了Eureka客户端，主动向Eureka服务端提供IP地址、端点等信息完成注册。

服务续约是每隔一段时间eureka客户端都要向服务端发送自己的状态，从而进行续约。

服务取消是 eureke客户端主动告知eureka服务端自己不在注册。当Eureka客户端连续一段时间没有向 Eureka 服务器发送服务续约信息时，Eureka 服务器就会认为该服务实例已经不再运行，从而将其从服务列表中进行剔除（Evict）

##### Eureka服务存储源码解析

存储数据的方法是InstanceRegistry接口和它的实现类 AbstractInstanceRegistry。保存注册信息的数据结构为：

```java

```

采用双层map进行存储。第一层使用的是线程安全的concurrentHashMap，key为spring.application.name,也就是服务名，value是一个ConcurrentHashMap。第二层的ConcurrentHashMap的key是instanceId,服务的唯一ID，value是Lease对象。Eureka使用Lease(租约)这个词来表示对服务注册信息的抽象，对象中包括了服务注册实例的信息，以及注册时间、最新续约时间。

InstanceRegistry 继承两个接口：LeaseManager 和 LookupService。LeaseManager接口中定义了 服务注册方法registry , 服务续约 cancel, 服务取消 renew, 服务剔除 evict. LookupService接口定义了 应用程序和服务实例的管理， 获取应用程序 getApplication， 获取服务实例 getInstancesById()，获取下一个服务来源 getNextServerFromEureka。

对于注册register方法进行流程分析：

```java
public void register(InstanceInfo registrant, int leaseDuration, boolean isReplication) {
    try { 
        //从已存储的 registry 获取一个服务定义
        Map<String, Lease<InstanceInfo>> gMap = registry.get(registrant.getAppName());
        REGISTER.increment(isReplication);
        if (gMap == null) {
            //初始化一个 Map<String, Lease<InstanceInfo>> ，并放入 registry 中
        }
        //根据当前注册的 ID 找到对应的 Lease
        Lease<InstanceInfo> existingLease = gMap.get(registrant.getId());
        if (existingLease != null && (existingLease.getHolder() != null)) {
            //如果 Lease 能找到，根据当前节点的最新更新时间和注册节点的最新更新时间比较
            //如果前者的时间晚于后者的时间，那么注册实例就以已存在的实例为准
        } else {
              //如果找不到，代表是一个新注册，则更新其每分钟期望的续约数量及其阈值
        }
        //创建一个新 Lease 并放入 Map 中
        Lease<InstanceInfo> lease = new Lease<InstanceInfo>(registrant, leaseDuration);
        gMap.put(registrant.getId(), lease);
        //处理服务的 InstanceStatus
        registrant.setActionType(ActionType.ADDED);
        //更新服务最新更新时间
        registrant.setLastUpdatedTimestamp();
        //刷选缓存
        invalidateCache(registrant.getAppName(), registrant.getVIPAddress(), registrant.getSecureVipAddress())；
    } 
}
```

#### Eureka服务缓存源码解析

服务缓存是为了保证服务响应快速以及保证可用性，通常都会在服务器缓存一份已注册的服务列表，通过一定的定时机制对缓存进行更新。

获取注册到Eureka服务器上具体某一个服务实例的详细信息可以访问地址：

```html

```

服务缓存主要是通过ResponseCache接口实现，他有一个实现类ResponseCacheImpl。其中获取Value的策略如下：

```java

```

其中使用了两种缓存 ReadOnlyCacheMap 和 readWriteCacheMap。 readOnlyCacheMap 就是一个 JDK 中的 ConcurrentMap，而 readWriteCacheMap使用的则是 Google Guava Cache 库中的 LoadingCache类型。在创建LoadingCache的过程中，缓存数据的来源会调用 generatePayload方法，而generatePayload方法会调用AbstranceRegistry 中的 getApplications方法获取应用信息并放到缓存中，从而实现注册信息和缓存信息的关联。

因为使用了两个缓存数据 ReadOnlyCacheMap 和readWriteCacheMap,更好的职责分离，而这两个其实是同一份数据，所以需要同步数据，此时需要不断的更新readWriteCacheMap, 使用一个 CacheUpdateTask的定时任务将readWriteCacheMap中的数据更新到ReadOnlyCacheMap 中。