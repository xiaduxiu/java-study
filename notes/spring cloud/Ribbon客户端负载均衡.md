Ribbon 和 DiscoveryClient

ribbon是一个实现**客户端负载均衡**的组件，嵌入到服务消费者内部进行使用。与此同时还提供了一些辅助的机制来保证服务调用过程的可靠性和容错性，连接超时和重试机制等。ribbon通过注解简单实现面向服务的接口调用，自动集成负载均衡的功能。使用方式有两种：

1. @LoadBalcanced .此注解用于修饰发起http请求的RestTemplate工具类，并在该工具类中自动嵌入客户端负载均衡功能，不需要任何特殊的开发和配置。
2. @RibbonClient. 此注解可以完全控制客户端负载均衡行为。可以使用此注解实现特定场景的负载均衡算法，也可以实现更细粒度的负载均衡配置。

##### 使用DiscoveryClient 获取服务实例信息

通过discoveryClient.getServices(); 获取到所有服务名称列表。

再通过方法discoveryClient.getInstances(serviceName); 获取到服务实例信息，其结果数据类型是List< ServiceInstance >。 ServiceInstance对象包含了服务实例的唯一性 Id、主机、端口、URL、元数据等详细信息。通过这个ServiceInstance列表就可以实现客户端的负载均衡。

#### Ribbon的基本架构和实现原理

客户端负载均衡工具，要做的事情有两件： 1. 获取服务列表 2. 决定调用哪一个服务。针对这两件事，抽象出多个核心类。

ILoadBalancer接口有方法：

1. 获取可用服务列表 getReachableServers
2. 获取所有后端服务列表 getAllServers
3. 增加后端服务列表 addServers
4. 选择调用服务 chooseServer
5. 标记服务不可用 markServerDown

ILoadBalancer接口的基本实现类是BaseLoadBalancer.

还有三个接口 ：

1. IRule: 负载均衡的抽象，通过实现这个接口提供自定义的负载均衡算法。

   ```java
   public interface IRule{
       public Server choose(Object key);
       public void setLoadBalancer(ILoadBalancer lb);
       public ILoadBalancer getLoadBalancer();
   }
   ```

2. IPing: 判断目标服务是否可用

   ```java
   
   ```

3. LoadBalancerStats： 记录负载均衡的实时运行信息,用来作为负载均衡策略的运行时输入。

   BaseLoadBalancer内部维护 allServerList 和 upServerList 两个线程的安全列表。	



   针对负载均衡，我们重点应该关注的是 ILoadBalancer 接口中 chooseServer 方法的实现，该方法肯定通过前面介绍的 IRule 接口集成了具体负载均衡策略的实现。

#### 负载均衡策略

负载均衡算法可以分成两大类，即静态负载均衡算法和动态负载均衡算法.

静态负载均衡算法包括：随机、轮询 和 加权轮询等算法。

动态负载均衡算法包括：IP哈希算法、最少连接数算法、服务调用时延算法。

![1605526004219](C:\Users\xia\AppData\Roaming\Typora\typora-user-images\1605526004219.png)

重点关注动态策略：

1. BestAvailableRule 策略： 选择一个并发请求量最小的服务器，逐个考察服务器，然后选择其中活跃请求数最小的服务器。
2. WeightedResponseTimeRule策略： 根据响应时间来计算服务的权重。响应时间越长，权重越低。响应时间通过ILoadBalancer接口中的LoadBalancerStats。定时从LoadBalancerStats读取平均响应时间，响应时间减去每个服务原来的响应时间，更新权重。
3. AvaliablityFilteringRule策略： 通过检查LoadBalancerStats 中记录的各个服务器运行状态，过滤处于一直连接失败或者高并发状态的服务器。

#### 为什么通过@LoadBalanced注解创建RestTemplate就自动具备客户端负载均衡的能力？

1. 通过自动配置类 LoadBalancerAutoConfiguration，里面存在被@LoadBalanced修饰的RestTemplate对象的列表。

2. 在初始化过程中，所有被@LoadBalanced修饰的RestTemplate，调用RestTemplateCustomizer的customize方法进行定制化,定制化过程会对目标RestTemplte增加拦截器LoadBalancerInterceptor:

3. LoadBalancerInterceptor拦截器调用LoadBalancerClient 的 execute 方法实现负载均衡。

4. LoadBalancerClient 接口 有 两个 execute 重载方法，用于根据负载均衡器所确定的服务实例来执行服务调用，reconstructURI 方法用服务实例的 host 和 port 再加上服务的端点路径来构造一个真正可供访问的服务。

5. LoadBalancerClient  接口继承自 ServiceInstanceChooser 接口。 ServiceInstanceChooser 接口有choose方法，这个方法的具体实现在 

   RibbonLoadBalancerClient中，而在choose方法中最终调用的是 loadBalancer.chooseServer()方法。
