package com.wlb.file.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.wlb.file.listener.UpDataListener;
import com.wlb.file.listener.UserphoneDataListener;
import com.wlb.file.pojo.Phone;
import com.wlb.file.pojo.User;
import com.wlb.file.pojo.Userphone;
import com.wlb.file.service.PhoneService;
import com.wlb.file.service.UserService;
import com.wlb.file.service.UserphoneService;
import com.wlb.file.util.UserphoneUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/up")
public class UserphoneController {
    @Autowired
    private UserService userService;
    @Autowired
    private PhoneService phoneService;
    @Autowired
    private UserphoneService userphoneService;

    //跳转到用户与手机表上传页面
    @RequestMapping("/toUploadPage")
    public String toUploadPage() {
        return "upupload";
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public List<Userphone> getAllUAndP(){
        return userphoneService.selectUAndP();
    }

    //导出到单个sheet
    @GetMapping("/exportWeb1")
    public void exportToWeb1(HttpServletResponse response) {
        try {
            UserphoneUtils.exportToWeb(response, "用户手机表", "用户拥有手机情况", Userphone.class, userphoneService.selectUAndP());
        } catch (Exception e) {
            log.error("报表导出异常:", e);
        }
    }

    //导出到多个sheet
    @GetMapping("/exportWeb2")
    public void exportToWeb2(HttpServletResponse response) {
        try {
            UserphoneUtils.allExportToWeb(response, "用户手机表",   //excel表格名称
                    "用户表", "手机表",           //excel表格的sheet名称
                    userService.getAll(),phoneService.getAllPhones());    //导入sheet的两份数据
        } catch (Exception e) {
            log.error("报表导出异常:", e);
        }
    }

    // easyexcel上传文件,单sheet表导入
    @PostMapping("/uploadone")
    @ResponseBody
    public String uploadOne(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), Userphone.class, new UserphoneDataListener(userphoneService)).sheet().doRead();
        return "上传成功";
    }

    // easyexcel上传文件,多sheet表导入
    @PostMapping("/uploadmany")
    @ResponseBody
    public String uploadMany(MultipartFile file) throws IOException {
        ExcelReader excelReader = EasyExcel.read(file.getInputStream()).build();
        // 读取sheet0
        ReadSheet sheet0 = EasyExcel.readSheet(0).head(User.class).registerReadListener(new UpDataListener<User>(userService)).build();
        // 读取sheet1
        ReadSheet sheet1 = EasyExcel.readSheet(1).head(Phone.class).registerReadListener(new UpDataListener<Phone>(phoneService)).build();
        excelReader.read(sheet0, sheet1);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
        return "上传成功";
    }
}
