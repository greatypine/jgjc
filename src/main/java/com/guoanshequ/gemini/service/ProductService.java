package com.guoanshequ.gemini.service;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: sp
 * @Package: com.guoanshequ.gemini
 * @Description:
 * @Author: gbl
 * @CreateDate: 2018/11/6 11:35
 */
public interface ProductService {

    /**
     * @Description 查询门店某一时间段的所有商品销售
     * @author gbl
     * @date 2018/11/6 11:37
     **/

    public List<Map<String,Object>> findAllProductSales(String beginDate, String endDate);

    /**
     * @Description 获取门店某一时间段指定商品销售信息
     * @author gbl
     * @date 2018/11/6 18:01
     **/

    public List<Object[]> getProductSales(String beginDate,String endDate,List<String> names,List<String> ids);
}
