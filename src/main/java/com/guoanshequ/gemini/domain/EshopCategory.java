package com.guoanshequ.gemini.domain;

import javax.persistence.*;

/**
 * @ProjectName: sp
 * @Package: com.guoanshequ.gemini.domain
 * @Description:商品种类
 * @Author: gbl
 * @CreateDate: 2018/11/6 9:32
 */
@Entity
@Table(name = "t_eshop_category")
public class EshopCategory {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;

    @Column(name="pid",columnDefinition = "0:顶级,否则记录他的父级")
    private String pid;

    @Column(name="name",columnDefinition = "名称")
    private String name;

    @Column(name = "level",columnDefinition = "级别")
    private int level;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
