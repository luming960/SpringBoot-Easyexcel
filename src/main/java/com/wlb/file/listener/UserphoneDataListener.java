package com.wlb.file.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.wlb.file.pojo.Phone;
import com.wlb.file.pojo.Userphone;
import com.wlb.file.pojo.User;
import com.wlb.file.service.UserphoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserphoneDataListener extends AnalysisEventListener<Userphone> {

    private final UserphoneService userphoneService;

    public UserphoneDataListener(UserphoneService userphoneService) {
        this.userphoneService = userphoneService;
    }

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */

    private static final int BATCH_COUNT = 5;
    List<Userphone> list = new ArrayList<Userphone>();

    @Override
    public void invoke(Userphone data, AnalysisContext context) {
        System.out.println("解析到一条数据:{}"+JSON.toJSONString(data));
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        System.out.println("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        System.out.println("{}条数据，开始存储数据库！"+ list.size());
        List<User> users = new ArrayList<>();   //创建需要保存的实体类User集合
        List<Phone> phones = new ArrayList<>();   //创建需要保存的实体类Phone集合
        for (int i = 0; i < list.size(); i++) {
            try {
                User user = new User();
                user.setName(list.get(i).getName()); //提取需要保存的用户的属性
                user.setSex(list.get(i).getSex());
                user.setAge(list.get(i).getAge());
                user.setBirthday(list.get(i).getBirthday());
                users.add(user);                    //添加到集合
                Phone phone = new Phone();
                phone.setPhone_name(list.get(i).getPhone_name()); //提取需要保存的手机的属性
                phone.setBrand(list.get(i).getBrand());
                phone.setPhone_color(list.get(i).getPhone_color());
                phone.setRam(list.get(i).getRam());
                phone.setRom(list.get(i).getRom());
                phone.setUser_id(list.get(i).getUser_id());
                phones.add(phone);                  //添加到集合
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!CollectionUtils.isEmpty(users)) {
            userphoneService.saveUsers(users);   //集合不为空之后存储用户
        }
        if (!CollectionUtils.isEmpty(phones)) {
            userphoneService.savePhones(phones);   //集合不为空之后存储手机
        }
        System.out.println("存储数据库成功！");
    }
}
