package com.wlb.file.mapper;

import com.wlb.file.pojo.Phone;
import com.wlb.file.pojo.User;
import com.wlb.file.pojo.Userphone;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserphoneMapper {
    //查询用户与手机
    List<Userphone> selectUAndP();
    // 保存用户
    void saveUsers(List<User> list);
    // 保存手机
    void savePhones(List<Phone> list);
}
