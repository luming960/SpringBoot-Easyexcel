package com.wlb.file.pojo;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Phone {
    // 主键id
    @ExcelIgnore // 生成报表时忽略，不生成次字段
    private Integer id;
    @ExcelProperty(value = "手机名称", index = 0)
    private String phone_name;
    @ExcelProperty(value = "手机品牌", index = 1)
    private String brand;
    @ExcelProperty(value = "手机颜色", index = 2)
    private String phone_color;
    @ExcelProperty(value = "运行内存", index = 3)
    private String ram;
    @ExcelProperty(value = "存储内存", index = 4)
    private String rom;
    @ExcelProperty(value = "用户id", index = 5)
    private Integer user_id;


}
