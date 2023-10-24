package com.wlb.file.service.impl;

import com.wlb.file.mapper.UserphoneMapper;
import com.wlb.file.pojo.Phone;
import com.wlb.file.pojo.User;
import com.wlb.file.pojo.Userphone;
import com.wlb.file.service.UserphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserphoneServiceImpl implements UserphoneService {
    @Autowired
    private UserphoneMapper userphoneMapper;

    @Override
    public List<Userphone> selectUAndP() {
        return userphoneMapper.selectUAndP();
    }

    @Override
    public void saveUsers(List<User> list) {
        userphoneMapper.saveUsers(list);
    }

    @Override
    public void savePhones(List<Phone> list) {
        userphoneMapper.savePhones(list);
    }
}
