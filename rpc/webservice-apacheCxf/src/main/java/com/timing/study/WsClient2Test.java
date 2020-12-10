package com.timing.study;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class WsClient2Test {

    // 使用 wsdl 文件生成客户端
    public static void main(String[] args) {

        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();

        String wsdlUrl = "http://localhost:9000/user?wsdl";

        Object[] objects = null;

        try {
            Client client = dcf.createClient(wsdlUrl);
            objects = client.invoke("add", 1, 2);
            System.out.println("response: " + objects[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
