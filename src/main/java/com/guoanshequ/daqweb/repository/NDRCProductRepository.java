package com.guoanshequ.daqweb.repository;

import com.guoanshequ.daqweb.domain.NDRCProduct;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ProjectName: jgjc
 * @Package: com.guoanshequ.daqweb.repository
 * @Description:发改委需要商品
 * @Author: gbl
 * @CreateDate: 2018/11/9 10:04
 */
public interface NDRCProductRepository extends JpaRepository<NDRCProduct,Long> {

}
