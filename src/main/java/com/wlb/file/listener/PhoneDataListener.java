package com.wlb.file.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.wlb.file.pojo.Phone;
import com.wlb.file.service.PhoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import sun.rmi.runtime.Log;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class PhoneDataListener extends AnalysisEventListener<Phone> {

    private PhoneService phoneService;

    public PhoneDataListener(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */

    private static final int BATCH_COUNT = 5;
    List<Phone> list = new ArrayList<Phone>();

    @Override
    public void invoke(Phone data, AnalysisContext context) {
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
        if (!CollectionUtils.isEmpty(list)) {
            phoneService.saveAllPhones(list);
        }
        System.out.println("存储数据库成功！");
    }
}
