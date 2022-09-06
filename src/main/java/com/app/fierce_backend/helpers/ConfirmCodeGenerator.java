package com.app.fierce_backend.helpers;


import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;

public class ConfirmCodeGenerator {

    public static String generateCode() {
        byte[] array = new byte[20];
        new Random().nextBytes(array);

        return new String(Base64.getEncoder().encode(array), StandardCharsets.UTF_8);
    }
}
