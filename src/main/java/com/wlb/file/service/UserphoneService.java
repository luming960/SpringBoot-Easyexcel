package com.wlb.file.service;

import com.wlb.file.pojo.Phone;
import com.wlb.file.pojo.User;
import com.wlb.file.pojo.Userphone;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserphoneService {
    // 查询用户与其手机信息
    List<Userphone> selectUAndP();
    // 保存用户信息
    void saveUsers(List<User> users);
    // 保存手机信息
    void savePhones(List<Phone> list);
}
