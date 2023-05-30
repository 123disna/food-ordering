package com.foodapp.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class ImageUtils {
    public static String encodeImage(byte[] image) {
        return Base64.encodeBase64String(image);
    }
}
