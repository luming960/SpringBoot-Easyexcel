package com.wlb.file;
import org.apache.commons.codec.binary.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * @author: zk
 * @create 2022-09-08 16:06
 * 获取前一天日期
 */
public class test1 {

    public static void main(String[] args) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        Date date = new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(Calendar.DATE, -1);
//        String da = sdf.format(calendar.getTime());
//        System.out.println(sdf.format(calendar.getTime()));


        String keyword = "query={job=\"BucketKeep\"}";
        if (true) {
            String[] fbsArr = { "\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };
            for (String key : fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }

        System.out.println("keyword"+keyword);


    }
}
