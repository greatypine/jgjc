package com.guoanshequ.gemini.service.impl;

import com.guoanshequ.gemini.repository.ProductRepository;
import com.guoanshequ.gemini.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: sp
 * @Package: com.guoanshequ.gemini.service.impl
 * @Description:
 * @Author: gbl
 * @CreateDate: 2018/11/6 13:10
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Map<String, Object>> findAllProductSales(String beginDate, String endDate) {

        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        List<Object[]> productSalesList = productRepository.findAllProductSales(beginDate,endDate);
        for(int i=0;i<productSalesList.size();i++){
            Object[] obj = (Object[])productSalesList.get(i);
            HashMap<String,Object> ps = new HashMap<String,Object>();
            ps.put("businessDay",obj[0]);
            ps.put("productCode",obj[1]);
            ps.put("productName",obj[2]);
            ps.put("productBar",obj[3]);
            ps.put("eshopCategoryCode",obj[4]);
            ps.put("eshopCategoryName",obj[5]);
            ps.put("productStandard",obj[6]);
            ps.put("productUnit",obj[7]);
            ps.put("productPrice",obj[8]);
            ps.put("storeName",obj[9]);
            ps.put("storeName",obj[10]);
            ps.put("sales",obj[11]);
            ps.put("remark",obj[12]);
            list.add(ps);
        }
        return list;
    }

    @Override
    public List<Object[]> getProductSales(String beginDate, String endDate,List<String> names,List<String> ids) {

        List<Object[]> list = new ArrayList<Object[]>();
        List<Object> productSalesList = productRepository.findProductSales(beginDate,endDate,names,ids);
        for(int i=0;i<productSalesList.size();i++){
            Object[] obj = (Object[])productSalesList.get(i);
            list.add(obj);
        }
        return list;
    }


}
