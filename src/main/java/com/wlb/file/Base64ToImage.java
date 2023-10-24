package com.wlb.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * @author: zk
 * @create 2023-06-13 15:59
 */
public class Base64ToImage {

    public static void main(String[] args) {
        String base64Image = "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";
        String imagePath = "/image.jpg"; // 指定保存图片的路径和文件名

        try {
            // 将Base64编码的图片字符串转换为字节数组
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);

            // 创建文件输出流
            FileOutputStream outputStream = new FileOutputStream(imagePath);

            // 写入字节数组到文件输出流
            outputStream.write(imageBytes);

            // 关闭文件输出流
            outputStream.close();

            System.out.println("图片保存成功！");
        } catch (IOException e) {
            System.out.println("图片保存失败：" + e.getMessage());
        }
    }
}
