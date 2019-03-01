package com.guoanshequ.daqweb.service.impl;

import com.guoanshequ.daqweb.domain.NDRCProduct;
import com.guoanshequ.daqweb.domain.Store;
import com.guoanshequ.daqweb.repository.StoreRepository;
import com.guoanshequ.daqweb.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: sp
 * @Package: com.guoanshequ.daqweb.service.impl
 * @Description:
 * @Author: gbl
 * @CreateDate: 2018/11/2 13:39
 */
@Service
@Transactional
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public Store findStoreByStoreNo(String storeNo) {
        Store store = storeRepository.findByStoreNo(storeNo);
        return store;
    }

    @Override
    public List<Store> findByCityName(String cityName) {
        return storeRepository.findByCityName(cityName);
    }

    @Override
    public List<Store> findAllStore(String estate) {
        return storeRepository.findAllStore(estate);
    }

    @Override
    public List<Map<String, Object>> findStoreAndStoreKeeper() {
        List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
        List<Object> list = storeRepository.findStoreAndStoreKeeper();

        if(list!=null){
            for(int i=0;i<list.size();i++){

                Object storeInfo = list.get(i);
                Object[] storeInfoArr = (Object[])storeInfo;
                Map<String,Object> result = new HashMap<String,Object>();
                result.put("storeName",storeInfoArr[0]);
                result.put("storeNo",storeInfoArr[1]);
                result.put("address",storeInfoArr[2]);
                result.put("storeKeeper",storeInfoArr[3]);
                result.put("phone",storeInfoArr[4]);
                resultList.add(result);
            }
        }

        return resultList;
    }

    @Override
    public List<Object[]> getStoreAndStorekeeper() {

        List<Object[]> list = new ArrayList<Object[]>();
        List<Object> storeList = storeRepository.findStoreAndStoreKeeper();
        for(int i=0;i<storeList.size();i++){
            Object[] obj = (Object[])storeList.get(i);
            list.add(obj);
        }
        return list;
    }



}
