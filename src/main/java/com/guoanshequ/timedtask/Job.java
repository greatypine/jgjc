package com.guoanshequ.timedtask;

import com.guoanshequ.daqweb.controller.StoreController;
import com.guoanshequ.gemini.controller.ProductController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @ProjectName: jgjc
 * @Package: com.guoanshequ.timedtask
 * @Description:
 * @Author: gbl
 * @CreateDate: 2018/11/12 15:53
 */
@Component
public class Job {

    @Autowired
    private StoreController storeController;

    @Autowired
    private ProductController productController;

    /**
     * @Description 定时获取门店信息
     * @author gbl
     * @date 2018/11/12 16:13
     **/

    @Scheduled(cron = "0 0 07 * * ?")
    public void getStoreInfo(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_MONTH,-1);
                String beginDate = sdf.format(calendar.getTime());
                storeController.exportStoreInfoToCSV(beginDate);
            }
        }).start();

    }

    /**
     * @Description 定时获取门店商品销售数据
     * @author gbl
     * @date 2018/11/12 16:14
     **/

    @Scheduled(cron = "10 0 07 * * ?")
    public void getProductSales(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar calendar = Calendar.getInstance();
                String endDate = sdf.format(calendar.getTime());
                calendar.add(Calendar.DAY_OF_MONTH,-1);
                String beginDate = sdf.format(calendar.getTime());
                productController.exportProductSalesDataToCSV(beginDate,endDate);
            }
        }).start();

    }
}
