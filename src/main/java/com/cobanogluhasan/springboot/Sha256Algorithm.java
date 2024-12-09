package com.cobanogluhasan.springboot;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256Algorithm {

    public static String sha256(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hextString  = new StringBuilder(2 * hash.length);
        for(int i=0; i<hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() ==1) {
                hextString.append('0');
            }
            hextString.append(hex);
        }
        return hextString.toString();
    }
}
