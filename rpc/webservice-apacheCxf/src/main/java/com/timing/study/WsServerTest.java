package com.timing.study;

import javax.xml.ws.Endpoint;

public class WsServerTest {

    public static void main(String[] args) {

        // server 启动参考文档 http://cxf.apache.org/docs/a-simple-jax-ws-service.html
        UserServiceImpl userService = new UserServiceImpl();
        String address = "http://localhost:9000/user";
        Endpoint.publish(address, userService);

    }
}
