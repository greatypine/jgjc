package com.guoanshequ.daqweb.repository;

import com.guoanshequ.daqweb.domain.NDRCProduct;
import com.guoanshequ.daqweb.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: sp
 * @Package: com.guoanshequ.daqweb.repository
 * @Description:
 * @Author: gbl
 * @CreateDate: 2018/11/2 10:27
 */
@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {
    /**
     * @Description 根据门店编号查询门店
     * @author gbl
     * @date 2018/11/2 10:54
     **/
   public  Store findByStoreNo(String storeNo);

   /**
    * @Description 查询某一城市的门店
    * @author gbl
    * @date 2018/11/20 15:39
    **/

   @Query(value = "select ts.* from t_store ts where  ifnull(ts.estate,'') ='运营中' and ts.name not like '%测试%' and ts.name not like '%储备%' and ts.name not like '%办公室%' and ts.flag='0'  and ts.storetype!='V' and ts.storetype!='W' and ts.city_name=?1",nativeQuery = true)
   public List<Store> findByCityName(String cityName);

   /**
    * @Description 查询所有有效门店
    * @author gbl
    * @date 2018/11/2 16:06
    **/

   @Query(value = "select ts.* from t_store ts where  ts.name not like '%测试%' and ts.name not like '%储备%' and ts.name not like '%办公室%' and ts.flag='0'  and ts.storetype!='V' and ts.storetype!='W' and ts.estate=?1",nativeQuery = true)
   public List<Store> findAllStore(String estate);


   /**
    * @Description 查询北京运营门店的信息
    * @author gbl
    * @date 2018/11/5 10:14
    **/

   @Query(value="SELECT CONCAT(\"'\",ts.storeno,\"'\") as storeno,ts.name,ts.address,'' as cityArea,tbu.name as storeKeeper,CONCAT(\"'\",tbu.mobilephone,\"'\") as mobilephone FROM `t_store` ts left join tb_bizbase_user tbu on ts.skid = tbu.id where ts.city_name='北京' and ts.`name` not like '%测试%'  and ts.name not like '%储备%' and ts.name not like '%办公室%' and ts.flag='0' and ifnull(ts.estate,'') ='运营中' and ts.storetype!='V' and ts.storetype!='W'",nativeQuery = true)
   public List<Object> findStoreAndStoreKeeper();



}
