package com.guoanshequ.daqweb.service;

import com.guoanshequ.daqweb.domain.NDRCProduct;

import java.util.List;

/**
 * @ProjectName: jgjc
 * @Package: com.guoanshequ.daqweb.service
 * @Description:
 * @Author: gbl
 * @CreateDate: 2018/11/9 10:06
 */
public interface NDRCProductService {

    /**
     * @Description 保存发改委展示商品
     * @author gbl
     * @date 2018/11/9 10:07
     **/

    public void saveNDRCProduct(NDRCProduct ndrcProduct);

    /**
     * @Description 查询所有发改委商品
     * @author gbl
     * @date 2018/11/9 11:09
     **/

    public List<NDRCProduct> findAllNDRCProduct();

}
