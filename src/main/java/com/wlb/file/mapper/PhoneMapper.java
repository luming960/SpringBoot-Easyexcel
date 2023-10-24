package com.wlb.file.mapper;

import com.wlb.file.pojo.Phone;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface PhoneMapper {
    // 查询所有手机信息
    List<Phone> getAllPhones();

    // 保存用户信息
    void saveAllPhones(List<Phone> list);
}
