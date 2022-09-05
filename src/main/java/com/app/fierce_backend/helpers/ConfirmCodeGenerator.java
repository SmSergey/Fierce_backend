package com.app.fierce_backend.helpers;


import java.nio.charset.StandardCharsets;
import java.util.Random;

public class ConfirmCodeGenerator {

    public static String generateCode() {
        byte[] array = new byte[20];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }
}
