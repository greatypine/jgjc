package com.guoanshequ.daqweb.service.impl;

import com.guoanshequ.daqweb.domain.NDRCProduct;
import com.guoanshequ.daqweb.repository.NDRCProductRepository;
import com.guoanshequ.daqweb.service.NDRCProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: jgjc
 * @Package: com.guoanshequ.daqweb.service.impl
 * @Description:
 * @Author: gbl
 * @CreateDate: 2018/11/9 10:08
 */
@Service
public class NDRCProductServiceImpl implements NDRCProductService {

    @Autowired
    private NDRCProductRepository ndrcProductRepository;

    @Override
    public void saveNDRCProduct(NDRCProduct ndrcProduct) {
            ndrcProductRepository.save(ndrcProduct);
    }

    @Override
    public List<NDRCProduct> findAllNDRCProduct() {
        return ndrcProductRepository.findAll();
    }

}
