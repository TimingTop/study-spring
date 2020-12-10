
## server 端启动
com.timing.study.WsServerTest  
然后使用 浏览器访问 http://localhost:9000/user?wsdl 得到 wsdl xml文件  

## client 端启动
### 1. 如果拿到 interface 文件，则直接用地址访问
com.timing.study.WsClientTest  
直接使用 interface 文件，设置 url 即可  

### 2. 如果只有 wsdl 描述文档，则使用 wsdl2java 工具生成 interface 文件，再用地址访问
com.timing.study.WsClient2Test  


## 参考文档
https://cwiki.apache.org/confluence/display/CXF/Index

https://blog.csdn.net/yhahaha_/article/details/93716263

http://cxf.apache.org/docs/how-tos.html

