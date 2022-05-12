


## 前言

Pawen网:   一个基于spring boot、springcloud、mybatis、redis、rabbitmq的轻量级、前后端分离、为文件上传下载共享完全准备，数据库设计精美，拥有完整分布式文件存储系统的文件上传网站

## 技术选型

| 技术         | 版本          | 说明                                                         |
| ------------ | ------------- | ------------------------------------------------------------ |
| Spring Boot  | 2.1.6         | MVC核心框架                                                  |
| MyBatis      | 3.5.0         | ORM框架                                                      |
| MyBatisPlus  | 3.1.0         | 基于mybatis,更快更强的数据库操作框架                         |
| redis        | 2.1.5         | 高性能单进程内存级数据库，广泛用于缓存以及高并发读写         |
| hikari       | 3.2.0         | 数据库连接池                                                 |
| Spring Cloud | Greenwich.SR1 | 基于SpringBoot的一系列微服务框架的集合                       |
| rabbitMQ     | 2.1.4         | 实现了高级消息队列协议（AMQP）的开源消息代理软件             |
| Vue          | 2.x           | 用于构建用户界面的渐进式JavaScript框架                       |
| fastDFS      | 1.27          | 一个开源的轻量级分布式文件系统                               |
| JWT          | 0.9.1         | 一种用于双方之间传递安全信息的简洁的、URL安全的表述性声明规范 |
| Element-ui   | 基于Vue2.0    | 网站快速成型工具                                             |
| Jieba        | 5.1.1         | 分词工具                                                     |

## 功能描述

- 微服务架构体系：微服务是一种将项目的各个模块拆分为可独立运行、部署、测试的架构设计风格

- Eureka：微服务注册与发现组件，将服务注册在Eureka上可以方面服务之间的调用与发现

- 微服务网关Gateway：网关是介于客户端和服务器端之间的中间层，所有的外部请求都会先经过 网关这一层。也就是说，API 的实现方面更多的考虑业务逻辑，而安全、性能、监控可以交由 网关来做，这样既提高业务灵活性又不缺安全性

- 使用Vue+Element-ui实现前端页面和功能的搭建

- 使用Mybatis的pageHelper插件实现分页查询

- redis令牌桶算法实现限流：定义KeyResolver，防止用户过度进行全库检索使数据库宕机

- JWT为用户签发token实现鉴权。token包含过期时间，达到过期时间如果用户不在线需重新登录。如果用户在线会更新token

- 使用nginx+fastDFS实现用户上传和下载文件：当用户上传文件时，会使用部署在阿里云服务器上的fastDFS的tracker组件将文件保存在的fastDFS的storage组件中，并将文件的唯一url通过mybatis保存在数据库中。当用户下载文件时，会根据文件id查找文件的url，该url通过nginx的location映射会被下载到用户本地

- 使用redis进行文件列表第一页的缓存。因为第一页展示频繁，将其内容缓存入redis会提高响应速度

- 使用rabbitMQ的topic工作模式，将用户的举报操作加入消息队列中，达到处理和响应异步进行的作用


## 演示地址

[pawen整体功能展示 (bilibili.com)](https://www.bilibili.com/medialist/play/168668668?from=space&business=space_series&business_id=1966924&desc=1&spm_id_from=333.999.0.0)





### 前端地址

[PleaseGiveMeTheCoke/pawen_fronts: aa (github.com)](https://github.com/PleaseGiveMeTheCoke/pawen_fronts)   

