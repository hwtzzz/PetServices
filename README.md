# PetServices

这是一个宠物服务管理系统，后端采用Spring Boot框架开发，集成了MyBatis-Plus，用于简化数据库操作。系统实现了包括用户管理、服务管理、订单管理、商品管理、预约管理等在内的多种功能。

## 功能特性

- **用户管理**：支持用户注册、登录、信息修改、密码重置等功能。
- **服务管理**：提供服务信息的增删改查功能。
- **订单管理**：支持订单的创建、查询、状态更新等操作。
- **商品管理**：实现商品信息的维护和查询。
- **预约管理**：支持预约服务的创建、状态更新、评论等。
- **管理员功能**：包括管理员登录、管理员信息维护、系统数据统计等。
- **文件上传**：支持图片上传功能，用于更新用户头像等场景。
- **数据统计**：提供系统运行数据的可视化统计信息。

## 模块结构

- **Controller层**：处理HTTP请求，提供RESTful API接口。
- **Service层**：实现业务逻辑，调用Mapper层进行数据操作。
- **Mapper层**：使用MyBatis-Plus进行数据库操作。
- **Entity层**：定义数据库实体类，与数据库表结构对应。
- **DTO层**：数据传输对象，用于封装接口传输数据。
- **配置类**：包括MyBatis-Plus配置、Web MVC配置、全局异常处理等。

## 技术栈

- **后端框架**：Spring Boot
- **数据库**：MyBatis-Plus
- **数据库表映射**：MyBatis Plus 注解方式
- **异常处理**：全局异常处理器 `CustomException`
- **响应封装**：使用 `JsonResult` 统一返回格式
- **拦截器**：实现响应结果的统一包装处理
- **工具类**：包括HTTP工具、文件上传工具等

## 安装与运行

1. 确保已安装JDK 1.8或更高版本。
2. 安装并配置MySQL数据库。
3. 导入项目到IDE（如IntelliJ IDEA）。
4. 修改`application.yml`中的数据库配置。
5. 执行`PetServicesApplication`类中的`main`方法启动项目。

## 使用说明

- **用户模块**：通过`UserController`实现用户登录、注册、信息更新等功能。
- **服务模块**：通过`ServeController`进行服务信息的维护。
- **订单模块**：通过`OrderController`管理订单生命周期。
- **商品模块**：通过`GoodsController`进行商品信息管理。
- **预约模块**：通过`BookingController`处理预约相关业务。
- **管理员模块**：通过`AdminController`进行系统管理操作。

## 接口文档

所有接口返回统一格式的JSON数据，结构如下：

```json
{
  "success": true,
  "result": {},
  "msg": "",
  "resultCode": 200
}
```

- `success`：表示请求是否成功。
- `result`：返回的数据对象。
- `msg`：请求失败时的错误信息。
- `resultCode`：请求结果码。

## 贡献指南

欢迎贡献代码，请遵循以下步骤：

1. Fork本项目。
2. 创建新分支（`git checkout -b feature/new-feature`）。
3. 提交代码（`git commit -am 'Add some feature'`）。
4. 推送到分支（`git push origin feature/new-feature`）。
5. 创建Pull Request。

## 许可证

本项目采用MIT许可证。详情请查看项目中的LICENSE文件。