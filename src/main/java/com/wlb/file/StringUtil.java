package com.wlb.file;

/**
 * @author: zk
 * @create 2023-06-16 15:04
 */
public class StringUtil {
    public static void main(String[] args) {
        String test = "123456789";
        final String s = test.length() < 4 ? test : test.substring(0, 4);
        System.out.println(s);
    }
}
