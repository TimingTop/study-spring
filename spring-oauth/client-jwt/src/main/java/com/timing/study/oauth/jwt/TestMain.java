package com.timing.study.oauth.jwt;

public class TestMain {

    public static void main(String[] args) throws Exception {
        MockOauthServer server = new MockOauthServer();

        String cert = server.getPublicCert();
        System.out.println(cert);

        String token = server.getSignToken();
        System.out.println(token);
    }
}
