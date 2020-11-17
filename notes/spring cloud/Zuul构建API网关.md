#### API网关

服务网关就是在服务端和所有客户端中间添加一个网关层，所有的客户端通过统一的网关请求服务，实现服务端和客户端的隔离作用。同时可以在网关层做一些非业务功能性需求，如**请求监控、安全管理、路由规则、日志记录、访问控制、服务适配**。

#### Zuul构建API网关

1. 引入依赖

   ```java
   <dependency>
         <groupId>org.springframework.cloud</groupId>
         <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
   </dependency>
   ```

2. 增加注解 @EnableZuulProxy

3. 配置

   ```
   server:
     port: 5555
   eureka:
     instance:
       preferIpAddress: true
     client:
       registerWithEureka: true
       fetchRegistry: true
       serviceUrl:
   	   defaultZone: http://localhost:8761/eureka/
   ```

#### Zuul如何实现服务路由

服务路由时API网关最重要的功能，通过Zuul访问的请求路由并转发到对应的后端服务。通过Zuul进行服务访问的URL通用格式： http://zuulservice:5555/service 。 zuulservice代表 zuul服务器的地址，service对应的确切的后端服务需要依赖服务路由信息。

服务路由信息的设置做法：

1. 基于服务发现映射服务路由（最常见、最推荐使用）

   此做饭是基于注册中心的服务发现机制实现的自动化服务路由。因为eureka保存所有服务实例的信息，里面就包括的服务名，zuul只需要把服务名和目标服务进行自动匹配，通过映射将目标服务映射到服务名称上。

   在zuul启动的时候，从eureka中获取到所有的已注册的服务信息，自动生成服务名称和目标服务之间的映射关系。通过访问zuul提供的服务路由端点 http://localhost:5555/actuator/routes 可以看到配置的路由信息。

2. 基于动态配置映射服务路由

   基于服务发现的自动映射服务路由存在局限性，有时需要对映射关系进行定制化，所以提供了这种配置实现服务路由。

   可以在 zuul-server 工程下的 application.yml 配置文件中为 user-service 配置特定的服务名称与请求地址之间的映射关系

   ```java
   zuul:
      prefix:  /springhealth
      routes:
        ignored-services: 'userservice'
        userservice: /user/**
   ```

   上面配置了 服务路由的前缀 springhealth， 忽略自动映射路由的服务地址userservice,使得user-service服务的路由为 user。

3. 基于静态配置映射服务路由

   不依赖eureka的服务路由方式，使用自定义的路由规则和其他第三方系统集成。如果有一个第三方服务无法注册到注册中心，还会暴露了一个 HTTP 端点供 SpringHealth 系统进行调用。理论上都是通过http进行通信，只需要知道地址和端口号是能够进行远程调用的，所以在配置文件中添加静态路由：

   ```java
   zuul:
   	prefix:  /springhealth
       routes:
         ignored-services: 'userservice'
          userservice: /user/**
   ```

   当访问 “/thirdpartyservice/**” 时 ，请求转发到 外部第三方服务http://thirdparty.com/thirdpartyservice 中。

   当然 Zuul 能够与 Ribbon 进行整合，此时的配置为：

   ```java
   zuul:
     routes:
       thirdpartyservice:
         path: /thirdpartyservice/**
         serviceId: thirdpartyservice
   ribbon:
     eureka:
       enabled: false
    
   thirdpartyservice:
     ribbon:
   	listOfServers: http://thirdpartyservice1:8080,http://thirdpartyservice2:8080
   ```

   zuul 配置 thirdpartyservice 路由信息， 通过 “/thirdpartyservice/**”映射到 thirdpartyservice服务中，此时希望自定义ribbon来实现客户端的负载均衡，需要关闭 eureka 和 ribbon的关联， 配置 "ribbon.eureka.enabled: false" ，之后手动指定ribbon的服务列表。

#### ZuulFilter组件架构

zuul响应http请求的过程是 通过内部的zuulFilter组件 来进行过滤。

zuul有一个IZuulFilter接口，里面有方法 shouldFilter() 、run()。其中的shouldFilter方法决定是否需要执行过滤器，run代表该filter具体要实现的业务逻辑。IZuulFilter 直接实现类为ZuulFilter，这个类有两个方法: filterType 代表过滤器类型，内置过滤器分为：PRE、ROUTING、POST、ERROR ； filterOrder 方法设置过滤器的执行顺序，数字越小越先执行。

存在一个管理过滤器的组件，称之为过滤器链， FilterRegistery 。使用单例模式创建 FilterRegistry 实例，内部使用线程安全的 ConcurrentHashMap 缓存 ZuulFilter。

##### ZuulFilter的加载 FilterLoader

FilterLoader中定义了各种映射关系的变量， filter文件名 与 修改时间的映射、filter名称 和 代码的 映射、filter 名称和 名称的映射。FilterLoader 分别提供了 getFilter、putFilter 和 getFiltersByType 这三个工具方法，其中实际涉及加载和存储 Filter 的方法只有 putFilter。Zuul 通过文件来存储各种 ZuulFilter 的定义和实现逻辑，然后启动一个守护线程定时轮询这些文件，确保变更之后的文件能够动态加载到 FilterRegistry 中。

##### RequestContext 与 上下文

RequestContext 对象可以存放业务信息，通过这个对象能在各个ZuulFilter中进行传递。RequestContext对象应该是线程私有的，使用线程安全的 ThreadLocal来存放所有的RequestContext。