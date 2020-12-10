package com.timing.study;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class WsClientTest {

    // 直接使用 interface 生成客户端
    public static void main(String[] args) {

        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(UserService.class);
        // 跟 server 端要一致
        factory.setAddress("http://localhost:9000/user");
        UserService client = (UserService) factory.create();
        String reply = client.sayHello("Timing");
        System.out.println("Response: " + reply);
    }
}
