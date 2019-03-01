package com.guoanshequ.daqweb.domain;

import javax.persistence.*;

/**
 * @ProjectName: jgjc
 * @Package: com.guoanshequ.daqweb.domain
 * @Description:
 * @Author: gbl
 * @CreateDate: 2018/11/9 9:22
 */
@Entity
@Table(name="t_ndrc_product")
public class NDRCProduct  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(20)")
    private Long id;

    @Column(name = "name",columnDefinition = "varchar(30) comment '商品类型名称'")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
