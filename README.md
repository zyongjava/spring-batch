## spring-batch

#### 简介

  通过 spring boot 启动spring batch读取csv文件并使用hibernate将插入MYSQL数据库

#### 部署方式

  更改数据库连接用户名和密码即可运行,数据库表结构hibernater会自动创建

#### 运行方式

  方式一: 执行命令`mvn spring-boot:run`即可启动

  方式二: maven打成jar包后,将使用命令 `java -jar spring-batch.jar` 启动spring-batch-1.0.0-SNAPSHOT.jar
