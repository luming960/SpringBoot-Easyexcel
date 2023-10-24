package com.wlb.file.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.wlb.file.pojo.Phone;
import com.wlb.file.pojo.User;
import com.wlb.file.service.PhoneService;
import com.wlb.file.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UpDataListener<T> extends AnalysisEventListener<T> {

    private UserService userService;
    private PhoneService phoneService;

    public UpDataListener(UserService userService) {
        this.userService = userService;
    }
    public UpDataListener(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<T> list = new ArrayList<T>();
    private Integer sheetNo;   //获取sheetNo,sheet号，也可以获取名字sheetName



    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        System.out.println("解析到一条数据:{}"+JSON.toJSONString(t));
        list.add(t);
        sheetNo = analysisContext.readSheetHolder().getSheetNo();
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        if (list.size() != 0) {
            saveData();
        }
        System.out.println("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        System.out.println("{}条数据，开始存储数据库！"+ list.size());
        if(sheetNo==0){
            List<User> users = new ArrayList<>();
            for (T t : list) {
                User user = (User) t;
                users.add(user);
            }
            if (!CollectionUtils.isEmpty(users)) {
                userService.saveBatch(users);   //集合不为空之后存储用户
            }
        }
        if(sheetNo==1){
            List<Phone> phones = new ArrayList<>();
            for (T t : list) {
                Phone  phone = (Phone) t;
                phones.add(phone);
            }
            if (!CollectionUtils.isEmpty(phones)) {
                phoneService.saveAllPhones(phones);   //集合不为空之后存储手机
            }
        }

        System.out.println("存储数据库成功！");
    }
}
