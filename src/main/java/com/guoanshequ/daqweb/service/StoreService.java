package com.guoanshequ.daqweb.service;

import com.guoanshequ.daqweb.domain.NDRCProduct;
import com.guoanshequ.daqweb.domain.Store;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: sp
 * @Package: com.guoanshequ.daqweb.service
 * @Description:
 * @Author: gbl
 * @CreateDate: 2018/11/2 13:37
 */

public interface StoreService {

    /**
     * @Description 根据门店编号查询门店
     * @author gbl
     * @date 2018/11/2 13:38
     **/

    public Store findStoreByStoreNo(String storeNo);

    /**
     * @Description 查询某一城市的门店
     * @author gbl
     * @date 2018/11/20 15:40
     **/

    public  List<Store> findByCityName(String cityName);

    /**
     * @Description 获取所有有效门店
     * @author gbl
     * @date 2018/11/2 16:08
     **/

    public List<Store> findAllStore(String estate);

    /**
     * @Description 获取门店以及店长信息
     * @author gbl
     * @date 2018/11/5 10:18
     **/

    public  List<Map<String,Object>> findStoreAndStoreKeeper();

    /**
     * @Description 获取门店信息
     * @author gbl
     * @date 2018/11/7 11:01
     **/

    public List<Object[]> getStoreAndStorekeeper();



}
