package com.wlb.file.service.impl;

import com.wlb.file.mapper.PhoneMapper;
import com.wlb.file.pojo.Phone;
import com.wlb.file.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneMapper phoneMapper;

    @Override
    public List<Phone> getAllPhones() {
        return phoneMapper.getAllPhones();
    }

    @Override
    public void saveAllPhones(List<Phone> list) {
        phoneMapper.saveAllPhones(list);
    }
}
