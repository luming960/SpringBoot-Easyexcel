package com.wlb.file.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Userphone {
    // 主键id
//    @ExcelIgnore // 生成报表时忽略，不生成次字段
//    private Integer id;
    @ExcelProperty(value = "姓名", index = 0) // 定义表头名称和位置,0代表第一列
    private String name;
    @ExcelProperty(value = "性别", index = 1)
    private String sex;
    @ExcelProperty(value = "年龄", index = 2)
    private Integer age;
    @DateTimeFormat(value = "yyyy-MM-dd")
    @ExcelProperty(value = "出生日期", index = 3)
    private String birthday;
    @ExcelProperty(value = "手机名称", index = 4)
    private String phone_name;
    @ExcelProperty(value = "手机品牌", index = 5)
    private String brand;
    @ExcelProperty(value = "手机颜色", index = 6)
    private String phone_color;
    @ExcelProperty(value = "运行内存", index = 7)
    private String ram;
    @ExcelProperty(value = "存储内存", index = 8)
    private String rom;
    @ExcelProperty(value = "用户id", index = 9)
    private Integer user_id;  //该用户与手机id可忽略，但是作为外键的用户id需要保存下来
}
