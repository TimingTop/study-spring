package com.timing.study.oauth.jwt;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

public class Oauth {

    public void validate(String token, String jwksUri) throws Exception {

        SignedJWT signedJWT = SignedJWT.parse(token);


        signedJWT.getPayload();


    }

    public void testRSAKey() throws Exception {
        RSAKey rsa = new RSAKeyGenerator(2048).algorithm(JWSAlgorithm.RS384).generate();

        RSASSASigner signer = new RSASSASigner(rsa);

        JWTClaimsSet build = new JWTClaimsSet.Builder().subject("aa").issuer("bb")
                .build();




    }
}
