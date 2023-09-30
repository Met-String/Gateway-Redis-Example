# Gateway-Redis-Example
基于SpringBoot/Maven/SpringCloudGateway/Redis/实现的一个池沼网关。有简单的密钥访问、路由转发。因为只是一个孤零零的网关，所以所有请求都只能被转发至断路器力（悲）。

# 项目简介
一个极为池沼的网关项目。是的没错，只有一个网关。

# 使用方法
首先您需要确保您的电脑已经（通过某种方式）安装并且启动了Redis，并且Redis的所有配置都是默认的状态（麻木）。

在IDEA中启动本应用之后，如果您尝试使用：
localhost:8500/book/{这里写任意数字}
localhost:8500/user/{这里写任意数字}
localhost:8500/borrow/{这里写任意数字}
三者其一的URL进行访问时,这个网关会识别并尝试处理它，但是很可惜！因为只有一个网关它自己，因此它的所有转发都会失败，并返回断路器中设置好的信息。
如果您有自己的...应用和此网关进行对接（应该不会有的），那么想必您对网关已经有了足够的了解，也看不上这个池沼项目了（悲）。

但是在此之前，想要让您的请求通过这个网关的过滤器，您需要在您的请求中加上参数key，其值必须是正确的密钥值，否则您会看到以下内容：
![image](https://github.com/Met-String/Gateway-Redis-Example/assets/111751431/a4c14127-1513-4502-a110-a3ad4baec4df)

密钥在哪里发放？这个项目运行的时候会在根目录里面创建一个MyKey.txt，并且把随机生成的密钥放进去。
![image](https://github.com/Met-String/Gateway-Redis-Example/assets/111751431/0a90c979-c2b3-4483-9abc-f355235579c8)


接着您只需要让key的值等于密钥值就可以了，就像这样：
localhost:8500/user/2?key=486778

接着恭喜您！您成功访问了（断路器）页面！
![image](https://github.com/Met-String/Gateway-Redis-Example/assets/111751431/c805d653-9b0e-44b4-95d7-86cada1ab335)


# 联系方式
QQ2495726551

