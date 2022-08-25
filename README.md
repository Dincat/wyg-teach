<p align="center">
	<img alt="logo" src="https://worrilessgo.oss-cn-guangzhou.aliyuncs.com/WorrilessGo/Logo.png">
</p>
<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">无忧果教学教务系统</h1>
<h4 align="center">基于 Vue/Element UI 和 Spring Boot/Spring Cloud & Alibaba 前后端分离的分布式微服务架构</h4>

## 平台简介

基于及结合[若依微服务框架](http://doc.ruoyi.vip/ruoyi-cloud/)开发的[无忧果教学教务系统](https://gitee.com/Dincat/wyg-teach)，集成[无忧果考试平台](https://gitee.com/Dincat/wyg-exam)：

1.  多校区/院校管理；
2.  专业设置；
3.  班级管理；
4.  学期阶段管理；
5.  课程管理
6.  班级作业；
7.  班级考试；
8.  考试教务（集成[无忧果考试平台](https://gitee.com/Dincat/wyg-exam)）；
9.  通知公告；
10.  在线办公（工作流，待实现）
11. 更多……


  后续将继续完善功能，并将实现教师端、学生端APP（uni-app)。
  
## 项目介绍
* 采用前后端分离的模式，微服务版本前端。
* 后端采用Spring Boot、Spring Cloud & Alibaba。
* 注册中心、配置中心选型Nacos，权限认证使用Redis。
* 流量控制框架选型Sentinel，分布式事务选型Seata。
* 使用了RabbitMQ。

## 项目部署
* 阿里云折扣场：[点我进入](https://www.aliyun.com/minisite/goods?source=5176.11533457&userCode=hd9sngi1)，腾讯云秒杀场：[点我进入](https://cloud.tencent.com/act/cps/redirect?redirect=2446&cps_key=af59af0abad1f7fa52d35a6e931825d1&from=console);
* 阿里云优惠券：[点我领取](https://www.aliyun.com/minisite/goods?userCode=hd9sngi1&share_source=copy_link)，
腾讯云优惠券：[点我领取](https://cloud.tencent.com/act/cps/redirect?redirect=35834&cps_key=af59af0abad1f7fa52d35a6e931825d1);

* 如需技术服务支持，快速上线。可以直接联系作者私聊

* <p>欢迎加群交流：  <a href="https://qm.qq.com/cgi-bin/qm/qr?k=b2NMgSJsEX7fAI2FJu6jjCTqzU707UZl&jump_from=webapi"><img src="https://img.shields.io/badge/747860738-blue.svg" alt="无忧果服务" title="无忧果服务"></a></p>



## 系统模块

~~~
com.wyg     
├── wyg-ui                                          // 前端框架 [80]
├── wyg-gateway                                     // 网关模块 [8080]
├── wyg-auth                                        // 认证中心 [9200]
├── wyg-api                                         // 接口模块
│       └── wyg-api-system                          // 系统接口
├── wyg-common                                      // 通用模块
│       └── wyg-common-core                         // 核心模块
│       └── wyg-common-datascope                    // 权限范围
│       └── wyg-common-datasource                   // 多数据源
│       └── wyg-common-log                          // 日志记录
│       └── wyg-common-redis                        // 缓存服务
│       └── wyg-common-security                     // 安全模块
│       └── wyg-common-swagger                      // 系统接口
├── wyg-modules         // 业务模块
│       └── wyg-system                              // 系统模块 [9201]
│       └── wyg-gen                                 // 代码生成 [9202]
│       └── wyg-job                                 // 定时任务 [9203]
│       └── wyg-file                                // 文件服务 [9300]
│       └── wyg-wechat                              // 微信服务 [8990]
│       └── wyg-teach                               // 教学服务 [9600]
│       └── wyg-exam                                // 考务服务 [11000]
├── wyg-visual                                      // 图形化管理模块
│       └── wyg-visual-monitor                      // 监控中心 [9100]
├──pom.xml                                          // 公共依赖
~~~

## 架构图

<img src="https://oscimg.oschina.net/oscnet/up-82e9722ecb846786405a904bafcf19f73f3.png"/>

## 内置功能

1.  用户管理：用户是系统操作者，该功能主要完成系统用户配置。
2.  部门管理：配置系统组织机构（公司、部门、小组），树结构展现支持数据权限。
3.  岗位管理：配置系统用户所属担任职务。
4.  菜单管理：配置系统菜单，操作权限，按钮权限标识等。
5.  角色管理：角色菜单权限分配、设置角色按机构进行数据范围权限划分。
6.  字典管理：对系统中经常使用的一些较为固定的数据进行维护。
7.  参数管理：对系统动态配置常用参数。
8.  通知公告：系统通知公告信息发布维护。
9.  教务管理：校区管理、专业管理、课程管理、教职工管理、学生管理。
9.  考务管理：课程管理、试卷管理、题库管理、成绩管理。
10. 公众号管理：微信配置、用户标签、用户消息、素材管理、自定义菜单、消息自动回复、数据统计。
11. 操作日志：系统正常操作日志记录和查询；系统异常信息日志记录和查询。
12. 登录日志：系统登录日志记录查询包含登录异常。
13. 在线用户：当前系统中活跃用户状态监控。
14. 定时任务：在线（添加、修改、删除)任务调度包含执行结果日志。
15. 代码生成：前后端代码的生成（java、html、xml、sql）支持CRUD下载 。
16. 系统接口：根据业务代码自动生成相关的api接口文档。
17. 服务监控：监视当前系统CPU、内存、磁盘、堆栈等相关信息。
18. 在线构建器：拖动表单元素生成相应的HTML代码。
19. 连接池监视：监视当前系统数据库连接池状态，可进行分析SQL找出系统性能瓶颈。

## 在线体验

[http://www.51teach.vip](http://www.51teach.vip)


## 演示图

<table>
    <tr>
        <td><img src="https://worrilessgo.oss-cn-guangzhou.aliyuncs.com/WorrilessGo/wyg-teach/1.png"/></td>
        <td><img src="https://worrilessgo.oss-cn-guangzhou.aliyuncs.com/WorrilessGo/wyg-teach/2.png"/></td>
    </tr>
    <tr>
        <td><img src="https://worrilessgo.oss-cn-guangzhou.aliyuncs.com/WorrilessGo/wyg-teach/3.png"/></td>
        <td><img src="https://worrilessgo.oss-cn-guangzhou.aliyuncs.com/WorrilessGo/wyg-teach/4.png"/></td>
    </tr>
    <tr>
        <td><img src="https://worrilessgo.oss-cn-guangzhou.aliyuncs.com/WorrilessGo/wyg-teach/5.png"/></td>
        <td><img src="https://worrilessgo.oss-cn-guangzhou.aliyuncs.com/WorrilessGo/wyg-teach/6.png"/></td>
    </tr>
    <tr>
        <td><img src="https://worrilessgo.oss-cn-guangzhou.aliyuncs.com/WorrilessGo/wyg-teach/7.png"/></td>
        <td><img src="https://worrilessgo.oss-cn-guangzhou.aliyuncs.com/WorrilessGo/wyg-teach/8.png"/></td>
    </tr>
    <tr>
        <td><img src="https://worrilessgo.oss-cn-guangzhou.aliyuncs.com/WorrilessGo/wyg-teach/9.png"/></td>
        <td><img src="https://worrilessgo.oss-cn-guangzhou.aliyuncs.com/WorrilessGo/wyg-teach/10.png"/></td>
    </tr>
    <tr>
        <td><img src="https://worrilessgo.oss-cn-guangzhou.aliyuncs.com/WorrilessGo/wyg-teach/11.png"/></td>
        <td><img src="https://worrilessgo.oss-cn-guangzhou.aliyuncs.com/WorrilessGo/wyg-teach/12.png"/></td>
    </tr>
    <tr>
        <td><img src="https://worrilessgo.oss-cn-guangzhou.aliyuncs.com/WorrilessGo/wyg-teach/13.png"/></td>
        <td><img src="https://worrilessgo.oss-cn-guangzhou.aliyuncs.com/WorrilessGo/wyg-teach/14.png"/></td>
    </tr>
    <tr>
        <td><img src="https://worrilessgo.oss-cn-guangzhou.aliyuncs.com/WorrilessGo/wyg-teach/15.png"/></td>
        <td><img src="https://worrilessgo.oss-cn-guangzhou.aliyuncs.com/WorrilessGo/wyg-teach/16.png"/></td>
    </tr>


   
</table>


## 无忧果服务交流群

QQ群：<a href="https://qm.qq.com/cgi-bin/qm/qr?k=b2NMgSJsEX7fAI2FJu6jjCTqzU707UZl&jump_from=webapi"><img src="https://img.shields.io/badge/747860738-blue.svg" alt="无忧果服务" title="无忧果服务"></a> 点击按钮入群。


##支付作者

<table>
    <tr>
        <td><img src="https://images.gitee.com/uploads/images/2022/0404/135849_2e646b55_1284634.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2022/0404/141709_8dca2974_1284634.jpeg"/></td>
    </tr>
</table>

你可以请作者喝杯咖啡表示鼓励
