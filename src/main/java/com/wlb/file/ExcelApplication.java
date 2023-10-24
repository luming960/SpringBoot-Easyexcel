package com.wlb.file;

import com.wlb.file.pojo.Dto;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
@MapperScan(basePackages = "com.wlb.file.mapper")
public class ExcelApplication {
    public static void main(String[] args) {

        SpringApplication.run(ExcelApplication.class, args);

//        Integer a = 128;
//        Integer b = 128;
//        // -128-127
//        System.out.println("a:"+a+" "+"b:"+b);
//        if(a==b){
//            System.out.println("Integer可以用==比较");
//            System.out.println("不过有前提条件的！！！");
//            System.out.println("两个Integer的值在-128~127之内时可以用==比较");
//        }else if(b.equals(a)){
//            System.out.println("两个Integer的值不在-128~127之内时，必须用b.equals(a)进行比较");
//        }



//        Dto dto = new Dto();
//        String deepCode1 = "350000.350100.350102";
//        String[] split = deepCode1.split("\\.");
//        System.out.println("String数组原来的长度为"+split.length);
//        while (split.length < 4) {
//            int size = split.length;
//            String[] tmp = new String[size + 1];
//            System.arraycopy(split, 0, tmp, 0, size);
//            tmp[size] = "";
//            split = tmp;
//        }
//        System.out.println("扩容后String数组的长度为"+split.length);
//        dto.setA(split[0] == null ? null : split[0]);
//        dto.setB(split[1] == null ? null : split[1]);
//        dto.setC(split[2] == null ? null : split[2]);
//        dto.setD(split[3] == null ? null : split[3]);
//        dto.setA(split[0]);
//        dto.setB(split[1]);
//        dto.setC(split[2]);
//        dto.setD(split[3]);
//        System.out.println(dto);




//        List<String> deepCodes = new ArrayList<>();
//        deepCodes.add("212249.211848.682859.412401863dc446cf84d8e8c5341bd340");
//        deepCodes.add("212249.412401863dc446cf84d8e8c5341");
//        deepCodes.add("212249.222222.412401863dc446cf84d8e8c5341bd");
//        deepCodes.add("212249.211848.222222");
//        for(int i =0;i<deepCodes.size();i++){
//            String[] spli = deepCodes.get(i).split("\\.");
//            List<String> split = transferArrayToList(spli);
//            System.out.println(split);
//
//            for (int q=0;q<split.size();q++){
//                //System.out.println(split.get(q));
//                //System.out.println(split.get(q).length());
//                if (split.get(q).length()>10){
//                    split.remove(q);
//                    q--;//不减减不行，list元素在减少
//                }
//            }
//            System.out.println(split);
//        }


//        deepCodes.add("212249.211848.682859");
//        deepCodes.add("212249");
//        deepCodes.add("212249.222222");
//        deepCodes.add("212249.211848.222222");
    }

    private static List<String> transferArrayToList(String[] array){
        List<String> transferedList = new ArrayList<>();
        Arrays.stream(array).forEach(arr -> transferedList.add(arr));
        return transferedList;
    }



}
