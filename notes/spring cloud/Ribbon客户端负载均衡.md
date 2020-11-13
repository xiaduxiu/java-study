#### Ribbon 和 DiscoveryClient

ribbon是一个实现**客户端负载均衡**的组件，嵌入到服务消费者内部进行使用。与此同时还提供了一些辅助的机制来保证服务调用过程的可靠性和容错性，连接超时和重试机制等。ribbon通过注解简单实现面向服务的接口调用，自动集成负载均衡的功能。使用方式有两种：

1. @LoadBalcanced .此注解用于修饰发起http请求的RestTemplate工具类，并在该工具类中自动嵌入客户端负载均衡功能，不需要任何特殊的开发和配置。
2. @RibbonClient. 此注解可以完全控制客户端负载均衡行为。可以使用此注解实现特定场景的负载均衡算法，也可以实现更细粒度的负载均衡配置。

##### 使用DiscoveryClient 获取服务实例信息

通过discoveryClient.getServices(); 获取到所有服务名称列表。

再通过方法discoveryClient.getInstances(serviceName); 获取到服务实例信息，其结果数据类型是List< ServiceInstance >。 ServiceInstance对象包含了服务实例的唯一性 Id、主机、端口、URL、元数据等详细信息。通过这个ServiceInstance列表就可以实现客户端的负载均衡。