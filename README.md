# seata-demo

#### 介绍

seata-1.3-demo

#### 使用说明

### 1.  启动seata server

#### 1. 修改配置

 下载seata server: https://github.com/seata/seata/releases/download/v1.3.0/seata-server-1.3.0.tar.gz

解压: tar -xzvf seata-server-1.3.0.tar.gz

修改 registry.conf 和 file.conf

registry.conf的功能是配置seata server的注册中心和配置项

file.config的功能是记录TM的回滚日志

registry.conf

```json
registry {
  type = "eureka"
  # register.type使用eureka的配置如下
  eureka {
    # 注册中心的地址
    serviceUrl = "http://127.0.0.1:5000/eureka/"
     # application 为seata server注册到eureka的名称
    application = "demo_tc"
    # 我也不知道这个干啥用
    weight = "1"
  }

}
# 用eureka的话config不用改动
config {
  type = "file"

  file {
    name = "file.conf"
  }
}
```

file.config

```json
store {
  # 全局事务回滚日志保存方式
  mode = "db"
  db {
    datasource = "druid"
    dbType = "mysql"
    driverClassName = "com.mysql.jdbc.Driver"
    url = "jdbc:mysql://127.0.0.1:3306/seata_demo_test"
    user = "root"
    password = "*********"
    minConn = 5
    maxConn = 30
    globalTable = "global_table"
    branchTable = "branch_table"
    lockTable = "lock_table"
    queryLimit = 100
    maxWait = 5000
  }
}
```

#### 2. 配置数据库

全局事务过程中，会涉及3块内容：

- 全局事务 global_table
- 分支事务 branch_table
- 全局锁  lock_table 

脚本在：https://github.com/seata/seata/tree/1.3.0/script

- #####  seata server使用的数据库执行https://github.com/seata/seata/blob/1.3.0/script/server/db/mysql.sql

- #####  seata client 使用的数据库执行https://github.com/seata/seata/blob/1.3.0/script/client/at/db/mysql.sql

- **该处使用AT模式**

#### 3.启动TC

在seata根目录下创建logs文件夹，进入bin目录，执行

```shell
nohup sh ./seata-server.sh &
tail -f nohup.out
```

该处以nohup的方式后台启动，参数可选

 -h: 注册到注册中心的ip

 -p: Server rpc 监听端口 

 -m: 全局事务会话信息存储模式，file、db，优先读取启动参数 

 -n: Server node，多个Server时，需区分各自节点，用于生成不同区间的transactionId，以免冲突 

 -e: 多环境配置参考 http://seata.io/en-us/docs/ops/multi-configuration-isolation.html 

 高可用部署可参考：https://seata.io/zh-cn/docs/ops/deploy-ha.html

### 2.  client配置

#### 1. 引入seata依赖

```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-seata</artifactId>
    <version>2.2.0.RELEASE</version>
    <exclusions>
    <exclusion>
        <groupId>io.seata</groupId>
        <artifactId>seata-spring-boot-starter</artifactId>
    </exclusion>
    </exclusions>
 </dependency>
<dependency>
    <groupId>io.seata</groupId>
    <artifactId>seata-spring-boot-starter</artifactId>
    <version>1.3.0</version>
</dependency>
```

#### 2. 在application中配置seata相关内容

```yaml
seata:
  enabled: true
  application-id: ${spring.application.name} #服务名
  tx-service-group: default # default是自定义的事务分组名称
  enable-auto-data-source-proxy: true # 启用自动数据源代理
  use-jdk-proxy: false
  service:
    vgroup-mapping:
      default: demo_tc # default是自定义的事务分组名称，fsp_tx是tc注册到注册中心的服务名称
    enable-degrade: false # 是否启用降级
    disable-global-transaction: false # 是否禁用全局事务
  config:
    type: file # 配置中心为file模式
  registry:
    type: eureka # 注册中心为eureka
    eureka:
      weight: 1
      service-url: http://127.0.0.1:5000/eureka/ # 注册中心地址
```

### 3.  使用

- @GlobalTransaction 全局事务注解
- @GlobalLock 防止脏读和脏写，又不想纳入全局事务管理时使用。（不需要rpc和xid传递等成本）