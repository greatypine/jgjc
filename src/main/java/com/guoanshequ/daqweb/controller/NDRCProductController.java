package com.guoanshequ.daqweb.controller;

import com.guoanshequ.daqweb.domain.NDRCProduct;
import com.guoanshequ.daqweb.service.NDRCProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ProjectName: jgjc
 * @Package: com.guoanshequ.daqweb.controller
 * @Description:
 * @Author: gbl
 * @CreateDate: 2018/11/9 10:10
 */
@Controller
public class NDRCProductController {

    @Autowired
    private NDRCProductService ndrcProductService;

    @RequestMapping("/saveNDRCProduct")
    @ResponseBody
    @Transactional(rollbackFor = Throwable.class)
    public  void saveNDRCProduct() throws Exception {

        NDRCProduct ndrcProduct = new NDRCProduct();
        ndrcProduct.setName("大米");
        ndrcProductService.saveNDRCProduct(ndrcProduct);
    }




}
