package com.timing.study.oauth.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.util.Date;
import java.util.Random;

public class MockOauthServer {


    private static final JWSAlgorithm JWS_ALGORITHM = JWSAlgorithm.RS384;
    private static final int RSA_LENGTH = 2048;

    private RSAKey rsaKey;
    private Random random;
    private String KID;

    public MockOauthServer() {

        try {
            random = new Random(System.currentTimeMillis());
            KID = String.valueOf(random.nextLong());
            rsaKey = new RSAKeyGenerator(2048).keyID(KID).generate();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
    }

    // 拿 公钥 + 证书， 模仿 的是 oauth 的 endpoint
    public String getPublicCert() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"keys\":[{\"kid\":\"");
        sb.append(KID);
        sb.append("\",\"kty\":\"RSA\",\"alg\":\"RS384\",\"use\":\"sig\",\"n\":\"");
        sb.append(rsaKey.toJSONObject().get("n"));
        sb.append("\",\"e\":\"AQAB\"}]}");
        return sb.toString();
    }
    // 拿到签名 token
    public String getSignToken() throws Exception {
        JWSSigner signer = new RSASSASigner(rsaKey);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject("baidu").issuer("http://www.baidu.com") // 唯一值
                .expirationTime(new Date(new Date().getTime() + 60 * 1000)) // 有效时间
                .issueTime(new Date()) // 签发时间
                .claim("aa", "bbb")
                .claim("scope", "scope1,scope2,scope3")
                .build();
        // format 成 jwt 格式
        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.RS384).keyID(rsaKey.getKeyID()).build(),
                claimsSet
        );
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }
}
