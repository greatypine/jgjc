package com.guoanshequ.daqweb.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @ProjectName: jgjc
 * @Package: com.guoanshequ.daqweb.domain
 * @Description:文件上传记录
 * @Author: gbl
 * @CreateDate: 2018/11/8 14:04
 */
@Entity
@Table(name="t_upload_record")
public class UploadRecord {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(20)")
    private Long id;

    @Column(name = "type",columnDefinition = "varchar(10) comment '类型'")
    private String type;

    @Column(name = "fileName",columnDefinition = "varchar(20) comment '文件名'")
    private String fileName;

    @Column(name = "createTime",columnDefinition = "datetime comment '创建时间'")
    private Date createTime;

    @Column(name = "result",columnDefinition = "varchar(20) comment '上传结果'")
    private String result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
