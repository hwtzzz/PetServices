# SpringBoot+Vue宠物服务预约系统（带后台）

#### 介绍
整合SpringBoot+Vue的宠物服务预约系统
该宠物服务预约系统由管理员模块和用户模块组成，其中用户模块可以进行注册、登录、查看宠物服务、查询宠物商品、宠物服务预约和修改个人信息等功能，由用户角色完成。管理员模块可以对宠物商品信息、用户信息、订单信息、预约信息等进行查看修改，由管理员角色进行完成。

#### 软件架构
![软件功能图](https://foruda.gitee.com/images/1760682916659092666/d753145d_11397283.png "功能设计图.png")


#### 安装教程

1. 使用Navicat或其它工具，在mysql中创建对应sql文件名称的数据库，并导入项目的sql文件；
2. 使用IDEA导入项目配置tomcat即可运行；
3. 将项目中src/main/resources/jdbc.properties配置文件中的数据库配置改为自己的配置

前端后台导入vscode后使用以下命令启动项目：
```
npm i
npm run serve
```

**后端：**
-  SpringBoot 2.X
-  Mysql 5.7
-  MybatisPlus 3.4.0

 **前端：** 
- Vue 2.6.X
- Router 3.5.1
- Axios 1.7.9
- ElementUI 2.15.14
- Echarts 5.6.0


#### 界面图
![后台首页](https://foruda.gitee.com/images/1760683287055988986/1a8d08a0_11397283.png "微信图片_20250927152551_51_14.png")
![前台服务预约页面](https://foruda.gitee.com/images/1760683303869000736/c3ee362a_11397283.png "微信图片_20250927152550_49_14.png")

####问题咨询
 
获取完整项目请联系作者

QQ：2975612823

WX：HwttzzZ
