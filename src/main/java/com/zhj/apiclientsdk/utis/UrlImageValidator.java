package com.zhj.apiclientsdk.utis;

import java.util.regex.Pattern;

public class UrlImageValidator {
    // 正则表达式，用于匹配常见图片扩展名
    private static final Pattern IMAGE_URL_PATTERN = Pattern.compile(".*\\.(jpg|jpeg|png|gif|bmp)$", Pattern.CASE_INSENSITIVE);

    // 方法：检查URL是否为图片
    public static boolean isImageUrl(String url) {
        return IMAGE_URL_PATTERN.matcher(url).matches();
    }

    public static void main(String[] args) {
        System.out.println(isImageUrl("http://example.com/image.jpg"));  // true
        System.out.println(isImageUrl("http://example.com/document.pdf"));  // false
    }
}
