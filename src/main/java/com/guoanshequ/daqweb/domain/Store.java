package com.guoanshequ.daqweb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName: sp
 * @Package: com.guoanshequ.daqweb.domain
 * @Description:
 * @Author: gbl
 * @CreateDate: 2018/11/2 10:25
 */
@Entity
@Table(name="t_store")
public class Store {

    @Id
    @Column(name = "store_id",columnDefinition = "门店Id")
    private Long storeId;

    @Column(name = "storeno",columnDefinition = "门店编号")
    private String storeNo;

    @Column(name = "name",columnDefinition = "门店名称")
    private String storeName;

    @Column(name="estate",columnDefinition = "门店状态")
    private String estate;

    @Column(name="storeType",columnDefinition = "门店类型")
    private String storeType;

    @Column(name = "flag",columnDefinition = "状态标志位")
    private Integer flag;

    @Column(name = "platformid")
    private String platformid;

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getEstate() {
        return estate;
    }

    public void setEstate(String estate) {
        this.estate = estate;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getPlatformid() {
        return platformid;
    }

    public void setPlatformid(String platformid) {
        this.platformid = platformid;
    }
}
