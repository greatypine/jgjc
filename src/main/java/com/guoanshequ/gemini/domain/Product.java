package com.guoanshequ.gemini.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName: sp
 * @Package: com.guoanshequ.gemini.domain
 * @Description:
 * @Author: gbl
 * @CreateDate: 2018/11/2 10:27
 */
@Entity
@Table(name="t_product")
public class Product {

    @Id
    @Column(name="id")
    private String productId;

    @Column(name = "content_name",columnDefinition="商品名称")
    private String name;

    @Column(name = "content_bar",columnDefinition="商品条形码")
    private String bar;

    @Column(name="self_code",columnDefinition = "自定义编码")
    private String selfCode;

    @Column(name = "content_standard",columnDefinition = "规格")
    private String contentStandard;

    @Column(name="eshop_category_id1",columnDefinition = "店内一级分类")
    private String eshopCategoryId1;

    @Column(name="eshop_category_id2",columnDefinition = "店内二级分类")
    private String eshopCategoryId2;

    @Column(name="eshop_category_id3",columnDefinition = "店内三级分类")
    private String eshopCategoryId3;

    @Column(name="content_price",columnDefinition = "商品价格")
    private double contentPrice;

    @Column(name="member_price",columnDefinition = "会员价格")
    private double memberPrice;

    @Column(name="content_unit",columnDefinition = "单位")
    private String contentUnit;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }



    public String getContentStandard() {
        return contentStandard;
    }

    public void setContentStandard(String contentStandard) {
        this.contentStandard = contentStandard;
    }


    public double getContentPrice() {
        return contentPrice;
    }

    public void setContentPrice(double contentPrice) {
        this.contentPrice = contentPrice;
    }

    public double getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(double memberPrice) {
        this.memberPrice = memberPrice;
    }

    public String getContentUnit() {
        return contentUnit;
    }

    public void setContentUnit(String contentUnit) {
        this.contentUnit = contentUnit;
    }
}
