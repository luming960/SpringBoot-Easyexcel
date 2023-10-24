package com.wlb.file.service;

import com.wlb.file.pojo.Phone;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;
import java.util.List;

@Component
public interface PhoneService {
    // 查询所有手机信息
    List<Phone> getAllPhones();

    // 保存用户信息
    void saveAllPhones(List<Phone> list);
}
