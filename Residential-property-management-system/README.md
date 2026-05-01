# 小区物业管理系统 (Residential Property Management System)

## 项目概述

本系统是一个面向物业管理员、小区住户、物业工作人员、外部广告申请企业的小区物业管理系统，实现人员管理、费用核算收取、维修服务流程化、广告入驻审核、线上报修、缴费查询、任务同步、广告申请与审批等核心功能，替代传统人工模式，实现物业管理高效化、标准化、透明化。

## 技术栈

- **JDK**: 23.0.2
- **数据库**: MySQL 5.5.28
- **服务器**: Tomcat 10.1.52
- **规范**: Jakarta EE 11
- **前端**: JSP + HTML + CSS + JavaScript
- **后端**: Servlet + JavaBean + JDBC
- **设计模式**: MVC

## 项目结构

```
Residential-property-management-system/
├── database.sql                 # 数据库建库建表脚本
├── README.md                    # 项目说明文档
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── property/
        │           ├── entity/  # 实体类
        │           ├── dao/     # 数据访问层
        │           ├── service/ # 业务逻辑层
        │           ├── servlet/ # 控制层
        │           ├── filter/  # 过滤器
        │           ├── listener/# 监听器
        │           └── utils/   # 工具类
        └── webapp/
            ├── WEB-INF/
            │   ├── web.xml      # 配置文件
            │   └── jsp/         # JSP页面
            ├── css/             # 样式文件
            ├── js/              # JavaScript文件
            └── images/          # 图片资源
```

## 环境要求

### 必须严格遵守的运行环境

1. **JDK版本**: JDK 23.0.2
2. **数据库**: MySQL 5.5.28
3. **服务器**: Tomcat 10.1.52
4. **编码格式**: 全局 UTF-8

### 数据库配置

```properties
驱动：com.mysql.jdbc.Driver
URL: jdbc:mysql://localhost:3306/property_management?useSSL=false&serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8
用户名：root
密码：123456
数据库名：property_management
```

## 部署步骤

### 1. 数据库初始化

```bash
# 登录MySQL
mysql -u root -p

# 执行建库建表脚本
source /path/to/database.sql
```

或者直接导入：
```bash
mysql -u root -p123456 < database.sql
```

### 2. 项目编译

```bash
cd Residential-property-management-system

# 设置JAVA_HOME为JDK 23
export JAVA_HOME=/path/to/jdk-23

# 编译Java源文件
mkdir -p target/classes
javac -d target/classes -cp "src/main/webapp/WEB-INF/lib/*" src/main/java/com/property/**/*.java
```

### 3. 部署到Tomcat

```bash
# 方式一：直接复制项目到webapps
cp -r src/main/webapp $TOMCAT_HOME/webapps/property

# 方式二：打包成WAR文件
cd src/main/webapp
jar -cvf ../property.war .
mv ../property.war $TOMCAT_HOME/webapps/
```

### 4. 启动Tomcat

```bash
$TOMCAT_HOME/bin/startup.sh
```

### 5. 访问系统

浏览器访问：`http://localhost:8080/property/`

## 核心角色与功能

### 1. 物业管理员（最高权限）
- 工作人员管理：增删改查
- 小区住户管理：信息审核、管理
- 费用管理：自动核算、缴费记录查看
- 维修管理：报修审核、任务分配、进度管理
- 广告管理：广告入驻申请审核、审批/驳回

### 2. 小区住户
- 个人信息管理
- 在线提交维修申请
- 查看个人缴费信息、缴费状态
- 查看维修进度

### 3. 物业工作人员
- 查看个人工作安排
- 同步维修任务处理进度
- 查看任务详情

### 4. 外部广告申请企业
- 线上提交广告入驻申请
- 实时查看审核状态

## 默认账号密码

| 角色 | 用户名 | 密码 | 说明 |
|------|--------|------|------|
| 物业管理员 | admin | 123456 | 最高权限，可管理所有模块 |
| 小区住户 | user001 | 123456 | 普通住户，可报修、缴费查询 |
| 物业工作人员 | staff001 | 123456 | 维修人员，可处理维修任务 |
| 广告企业 | adcompany001 | 123456 | 外部企业，可申请广告位 |

## 主要功能模块

### 用户认证模块
- 登录/登出
- 权限拦截过滤器
- 会话管理

### 人员管理模块
- 工作人员管理
- 住户信息管理
- 企业信息管理

### 维修管理模块
- 在线报修
- 任务分配
- 进度跟踪
- 完成确认

### 费用管理模块
- 费用核算
- 缴费记录
- 账单查询

### 广告管理模块
- 广告位申请
- 审核流程
- 状态查询

## 技术特性

### 过滤器 (Filter)
- **LoginFilter**: 登录拦截，未登录用户重定向到登录页
- **EncodingFilter**: 全局UTF-8编码，防止中文乱码
- **AuthFilter**: 权限控制，根据角色限制访问

### 监听器 (Listener)
- **SessionListener**: 会话监听，统计在线用户
- **ContextListener**: 项目启动初始化，加载配置

### 数据库连接池
- 使用原生JDBC连接MySQL 5.5.28
- 自定义连接池管理
- 自动重连机制

## 常见问题

### 1. 中文乱码问题
确保以下配置正确：
- Tomcat server.xml 中 URIEncoding="UTF-8"
- web.xml 中配置编码过滤器
- JSP页面声明 `<%@ page contentType="text/html;charset=UTF-8" %>`
- 数据库连接URL包含 `useUnicode=true&characterEncoding=utf8`

### 2. 数据库连接失败
检查：
- MySQL服务是否启动
- 数据库名、用户名、密码是否正确
- MySQL版本是否为5.5.28
- 驱动包是否在 WEB-INF/lib 目录下

### 3. 权限不足
- 确认用户已登录
- 检查用户角色是否有对应权限
- 查看过滤器配置是否正确

## 开发规范

### 代码规范
- 遵循MVC设计模式
- 实体类与数据库表一一对应
- Servlet处理请求，调用Service层
- Service层处理业务逻辑，调用DAO层
- DAO层负责数据库操作

### 命名规范
- 实体类：大驼峰命名 (User, Staff, RepairOrder)
- Servlet：功能 + Servlet (LoginServlet, RepairServlet)
- JSP页面：小写 + 下划线 (login.jsp, repair_list.jsp)
- 数据库表：小写 + 下划线 (t_user, t_staff)

### 注释规范
- 类注释：说明类的用途
- 方法注释：说明方法功能、参数、返回值
- 关键代码注释：说明复杂逻辑

## 安全建议

1. **生产环境修改默认密码**
2. **启用HTTPS**
3. **定期备份数据库**
4. **限制数据库访问权限**
5. **开启Tomcat访问日志**

## 许可证

本项目仅供学习和参考使用。

## 联系方式

如有问题，请查看项目文档或联系开发团队。

---

**注意**: 本项目严格按照 Jakarta EE 11 规范开发，包名使用 `jakarta.*` 而非 `javax.*`，确保与 Tomcat 10.1.52 完全兼容。
