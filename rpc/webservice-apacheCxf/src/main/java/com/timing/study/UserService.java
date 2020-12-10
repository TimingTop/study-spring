package com.timing.study;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface UserService {

    @WebMethod(operationName = "add")
    int add(@WebParam(name = "firstArg") int a, @WebParam(name = "secondArg") int b);

    String sayHello(String name);
}
