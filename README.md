## 破茧作业项目

本项目使用JDK11，请确保运行在正确的java版本下

数据库使用MYSQL8.0, 启动项目前请执行下方语句, 在docker中启动mysql数据库
```shell
docker-compose up -d
```

本项目配置了checkstyle与jacoco，并设置了pre-commit，
在commit代码前会运行测试并检查代码样式，如果测试覆盖率低于60%或者代码格式不正确都无法正常提交

### 请务必不要使用--no-verify


## 其他文件说明

./localBuild.sh 项目构建用脚本

./runLocal.sh 本地运行项目脚本

## 项目结构说明

/access 入口层

access目录作为项目的入口， 例如所有的Controller都应该在目录下

在该目录下，按照资源再进行划分

```
    例如 商品(commodity) 这一资源的访问，都应该由
    /access/commodity 这一目录下的CommodityController接收
    /access/representation 这一目录下包含了返回的结果体
```

/business 业务层

在该目录下，根据领域进行划分

business目录下包含了主要的业务逻辑，例如CommandService与QueryService都应该在这一目录下

```
    例如 需要对商品(Commodity)进行CRUD，
    那么一个请求会通过Controller传递给
    /bussiness/commodity/CommodityCommandService 进行处理
    
```

/domain 领域层

在该目录下，再根据领域进行划分
