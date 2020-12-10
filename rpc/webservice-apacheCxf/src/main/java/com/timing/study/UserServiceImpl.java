package com.timing.study;

import javax.jws.WebService;

@WebService(endpointInterface = "com.timing.study.UserService",
            serviceName = "UserService")
public class UserServiceImpl implements UserService {
    @Override
    public int add(int a, int b) {
        int result = a + b;
        System.out.println("a + b = " + result);
        return result;
    }

    @Override
    public synchronized String sayHello(String name) {
        return "Hello! " + name;
    }
}
