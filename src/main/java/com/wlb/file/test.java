package com.wlb.file;

import com.wlb.file.pojo.Dto;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

/**
 * @author: zk
 * @create 2022-09-07 15:52
 * 从list里获得分数最高的对象
 */
public class test {

    public static void main(String[] args) {
        Dto dto1 = new Dto();
        dto1.setScore(99);
        Dto dto2 = new Dto();
        dto2.setScore(55);
        Dto dto3 = new Dto();
        dto3.setScore(89);
        Dto dto4 = new Dto();
        dto4.setScore(78);

        System.out.println("dto1:"+dto1+"dto2:"+dto2+"dto3:"+dto3+"dto4:"+dto4);

        ArrayList<Dto> listDto = new ArrayList<>();
        listDto.add(dto1);
        listDto.add(dto2);
        listDto.add(dto3);
        listDto.add(dto4);


        Optional<Dto> max = listDto.stream().filter(Objects::nonNull).max(Comparator.comparingInt(Dto::getScore));
        System.out.println("max:"+max);
        Dto dto = max.orElse(new Dto());
        System.out.println("dto:"+dto);

    }
}
