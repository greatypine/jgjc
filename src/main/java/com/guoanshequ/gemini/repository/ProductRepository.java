package com.guoanshequ.gemini.repository;

import com.guoanshequ.gemini.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: sp
 * @Package: com.guoanshequ.gemini.repository
 * @Description:
 * @Author: gbl
 * @CreateDate: 2018/11/2 10:30
 */
@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

    @Query("from Product  b where bar=?1")
    public List<Product> findProductByBar(String  bar);



    /**
     * @Description 查询t_ndrc_product 指定的商品
     * @author gbl
     * @date 2018/11/9 11:31
     **/

    @Query(value = "SELECT" +
            "   DATE_FORMAT(tor.create_time, '%Y-%m-%d') AS businessDay," +
            "   tp.code AS productCode," +
            "   tp.content_name AS productName," +
            "   CONCAT(\"'\",tp.content_bar,\"'\") AS productBar," +
            "   CONCAT(\"'\",tec.id,\"'\") AS eshopCategoryCode," +
            "   tec.name AS eshopCategoryName," +
            "   tp.content_standard," +
            "   tp.content_unit," +
            "   tp.content_price," +
            "   ts.code AS storeCode," +
            "   ts.name AS storeName," +
            "   ifnull(sum(toi.quantity),0)*ifnull(tp.content_price,0) AS sales," +
            "    '' AS remark " +
            "FROM" +
            "   t_order tor," +
            "   t_order_item toi," +
            "   t_product tp," +
            "   t_eshop_category tec," +
            "   t_store ts " +

            "WHERE " +
            "   tor.create_time >=?1 AND tor.create_time<?2 " +
            "   AND toi.order_id = tor.id " +
            "   AND toi.eshop_pro_id = tp.id " +
            "   AND tp.eshop_category_id3 = tec.id " +
            "   AND tp.content_name NOT LIKE '%测试%' " +
            "   AND tor.store_id = ts.id " +
            "   AND tec.name in (?3)"+
            "   AND ts.id in (?4) "+
            "   GROUP BY " +
            "     tp.id," +
            "     tor.store_id",nativeQuery = true)
    public List<Object> findProductSales(String beginDate,String endDate,List<String> names,List<String> ids);



    /**
     * @Description 查询所有商品
     * @author gbl
     * @date 2018/11/9 11:32
     **/

    @Query(value = "SELECT" +
            "   DATE_FORMAT(tor.create_time, '%Y-%m-%d') AS businessDay," +
            "   tp.code AS productCode," +
            "   tp.content_name AS productName," +
            "   CONCAT(\"'\",tp.content_bar,\"'\") AS productBar," +
            "   CONCAT(\"'\",tec.id,\"'\") AS eshopCategoryCode," +
            "   tec.name AS eshopCategoryName," +
            "   tp.content_standard," +
            "   tp.content_unit," +
            "   tp.content_price," +
            "   ts.code AS storeCode," +
            "   ts.name AS storeName," +
            "   ifnull(sum(toi.quantity),0)*ifnull(tp.content_price,0) AS sales," +
            "    '' AS remark " +
            "FROM" +
            "   t_order tor," +
            "   t_order_item toi," +
            "   t_product tp," +
            "   t_eshop_category tec," +
            "   t_store ts " +

            "WHERE " +
            "   tor.create_time >=?1 AND tor.create_time<?2 " +
            "   AND toi.order_id = tor.id " +
            "   AND toi.eshop_pro_id = tp.id " +
            "   AND tp.eshop_category_id3 = tec.id " +
            "   AND tp.content_name NOT LIKE '%测试%' " +
            "   AND tor.store_id = ts.id " +

            "   GROUP BY " +
            "     tp.id," +
            "     tor.store_id",nativeQuery = true)
    public List<Object[]> findAllProductSales(String beginDate,String endDate);


}
