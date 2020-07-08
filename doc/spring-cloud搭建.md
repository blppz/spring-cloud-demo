# Eureka Server 注册中心搭建

## 单节点搭建

1. File -> new -> project 新建项目，然后选择镜像：https://start.aliyun.com

![1](./img/1.png)

2. 写 maven 配置

![1](./img/2.png)

3. 选择Eureka Server，然后 Next 选择文件路径，确定，等待项目依赖加载完成

![1](./img/3.png)

4. 在启动类加上 @EnableEurekaServer 注解

```java
package com.park.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author BarryLee
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
```

5. 将 application.properties 配置文件改为 application.yml

```
#这个和service-url一致，否则会发现unavailable
server.port=7900
#是否将自己注册到Eureka Server,默认为true，由于当前就是server，故而设置成false，表明该服务不会向eureka注册自己的信息
eureka.client.register-with-eureka=false
#是否从eureka server获取注册信息，由于单节点，不需要同步其他节点数据，用false
eureka.client.fetch-registry=false
#设置服务注册中心的URL，用于client和server端交流
eureka.client.service-url.defaultZone=http://localhost:7900/eureka/
```

6. 启动，然后打开  http://localhost:7900/  

## 高可用集群搭建

1. 修改 hosts 文件，win10 位置为：C:\Windows\System32\drivers\etc

   修改 hosts 失败参考文章：https://blog.csdn.net/Zandysjtu/article/details/68542104

```
host文件末尾加上
127.0.0.1 eureka-7900
127.0.0.1 eureka-7901
127.0.0.1 eureka-7902
```

2. 在上述操作的基础上，添加一个文件 application-eureka-7900.yml

```
server:
  port: 7900
eureka:
  client:
    #是否将自己注册到Eureka Server,默认为true，由于当前就是server，故而设置成false，表明该服务不会向eureka注册自己的信息
    register-with-eureka: true
    #是否从eureka server获取注册信息，由于单节点，不需要同步其他节点数据，用false
    fetch-registry: true
    service-url:
      #设置服务注册中心的URL，用于client和server端交流
      defaultZone: http://eureka-7901:7901/eureka/,http://eureka-7902:7902/eureka/
  instance:
    #主机名，必填
    hostname: eureka-7900
```

3. application-eureka-7901.yml

```
server:
  port: 7901
eureka:
  client:
    #是否将自己注册到Eureka Server,默认为true，由于当前就是server，故而设置成false，表明该服务不会向eureka注册自己的信息
    register-with-eureka: true
    #是否从eureka server获取注册信息，由于单节点，不需要同步其他节点数据，用false
    fetch-registry: true
    service-url:
      #设置服务注册中心的URL，用于client和server端交流
      defaultZone: http://eureka-7900:7900/eureka/,http://eureka-7902:7902/eureka/
  instance:
    hostname: eureka-7901
```

4. application-eureka-7902.yml

```
server:
  port: 7902
eureka:
  client:
    #是否将自己注册到Eureka Server,默认为true，由于当前就是server，故而设置成false，表明该服务不会向eureka注册自己的信息
    register-with-eureka: true
    #是否从eureka server获取注册信息，由于单节点，不需要同步其他节点数据，用false
    fetch-registry: true
    service-url:
      #设置服务注册中心的URL，用于client和server端交流
      defaultZone: http://eureka-7900:7900/eureka/,http://eureka-7901:7901/eureka/
  instance:
    hostname: eureka-7902
```

5. 到这里，就搭建完了，然后运行起来

![1](./img/4.png)

6. 复制三个，并指定 profile，其他两个一样

   然后确定，指定这三个配置文件将服务启动起来，中间肯定会有报错的，因为在相互注册，而其他的服务还没起来，起来之后打开：http://localhost:7900/ ，unavaliable 一定是空的才对

![1](./img/5.png)

![1](./img/6.png)

# Eureka Client 搭建

1. 打开 Project  Structure，选择 Module

   ![1](./img/7.png)

2. 添加一个模块 consumer

   ![1](./img/8.png)

   ![1](./img/9.png)

3. 添加依赖 Spring Web 以及 Eureka Client，然后 Next 选择文件路径，确定，等待项目依赖加载完成

![1](./img/10.png)

![1](./img/11.png)

4. 然后看到的项目应该是酱紫的

   ![1](./img/12.png)

5. 在启动类添加注解 @EnableEurekaClient

   ```
   package com.park.consumer;
   
   import org.springframework.boot.SpringApplication;
   import org.springframework.boot.autoconfigure.SpringBootApplication;
   import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
   
   /**
    * @author BarryLee
    */
   @EnableEurekaClient
   @SpringBootApplication
   public class ConsumerApplication {
   
       public static void main(String[] args) {
           SpringApplication.run(ConsumerApplication.class, args);
       }
   
   }
   ```

6. 将配置文件 application.properties 改为 application.yml

```
server:
  port: 8800
spring:
  application:
    name: consumer
eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://eureka-7901:7901/eureka/
```

7. 启动服务，分别打开 http://localhost:7900/，http://localhost:7901/ ，http://localhost:7902/ 可以看到 consumer 已经成功注册到了注册中心

![1](./img/13.png)

# OpenFeign 引入

1. 准备 provider 服务。使用上述同样方法搭建一个 provider 服务（作为公用 API 方）：引入 web 以及 discover client 依赖；在启动类添加注解 @EnableEurekaClient；修改配置文件如下

   ```
   server:
     port: 8800
   spring:
     application:
       name: consumer
   eureka:
     client:
       register-with-eureka: true
       fetch-registry: true
       service-url:
         defaultZone: http://eureka-7901:7901/eureka/
   ```

![1](./img/14.png)

2. consumer 引入 OpenFeign

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>

<!-- 顺便引入 lombok，免得写 get/set -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.8</version>
</dependency>
```

3. 在 consumer 的启动类添加注解：@EnableFeignClients
4. 