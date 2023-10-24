package com.wlb.file.controller;


import com.alibaba.excel.EasyExcel;
import com.wlb.file.listener.PhoneDataListener;
import com.wlb.file.pojo.Phone;
import com.wlb.file.service.PhoneService;
import com.wlb.file.util.PhoneUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/phone")
public class PhoneController {
    @Autowired
    private PhoneService phoneService;

    //跳转到手机表上传页面
    @RequestMapping("/toUploadPage")
    public String toUploadPage() {
        return "phoneupload";
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public List<Phone> selectPhones(){
        return phoneService.getAllPhones();
    }

    //导出到单个sheet
    @GetMapping("/exportWeb")
    public void exportToWeb(HttpServletResponse response) {
        try {
            PhoneUtils.exportToWeb(response, "手机表", "手机信息", Phone.class, phoneService.getAllPhones());
        } catch (Exception e) {
            log.error("报表导出异常:", e);
        }
    }

    // easyexcel上传文件
    @PostMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), Phone.class, new PhoneDataListener(phoneService)).sheet().doRead();
        return "上传成功";
    }
}
