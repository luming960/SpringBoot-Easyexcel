package com.wlb.file.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.wlb.file.pojo.Phone;
import com.wlb.file.pojo.User;
import com.wlb.file.pojo.Userphone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

@Component
public class UserphoneUtils {
    /**
     * 导出Excel(07版.xlsx)到web
     * 单sheet
     */
    public static void exportToWeb(HttpServletResponse response, String excelName, String sheetName, Class clazz, List data) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码
        excelName = URLEncoder.encode(excelName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + excelName + ExcelTypeEnum.XLSX.getValue());
        //调用自适应列宽方法（util包里面的Custemhandler）
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).registerWriteHandler(new Custemhandler()).build();
        //ExcelWriter excelWriter = null;
        WriteSheet writeSheet = EasyExcel.writerSheet(0, sheetName).head(Userphone.class).build();
        excelWriter.write(data, writeSheet);
        excelWriter.finish();
        //不使用自动适应列宽则注释上面四行，取消下面一行的注释，然后在user实体类里面使用注解自定义列宽
        EasyExcel.write(response.getOutputStream(), clazz).sheet(sheetName).doWrite(data);
    }

    /**
     * 导出Excel(07版.xlsx)到web
     * 多sheet
     */
    public static void allExportToWeb(HttpServletResponse response, String excelName, String sheetName, String sheetName1, List data1, List data2) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码
        excelName = URLEncoder.encode(excelName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + excelName + ExcelTypeEnum.XLSX.getValue());
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).registerWriteHandler(new Custemhandler()).build();
        //ExcelWriter excelWriter = null;
                WriteSheet writeSheet1 = EasyExcel.writerSheet(0, sheetName).head(User.class).build();
        WriteSheet writeSheet2 = EasyExcel.writerSheet(1, sheetName1).head(Phone.class).build();
        excelWriter.write(data1, writeSheet1);
        excelWriter.write(data2, writeSheet2);
        excelWriter.finish();
    }
}
