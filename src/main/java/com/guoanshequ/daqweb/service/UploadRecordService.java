package com.guoanshequ.daqweb.service;

import com.guoanshequ.daqweb.domain.UploadRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @ProjectName: jgjc
 * @Package: com.guoanshequ.daqweb.service
 * @Description:
 * @Author: gbl
 * @CreateDate: 2018/11/12 13:58
 */
public interface UploadRecordService {

    /**
     * @Description 根据记录类型查询上传记录
     * @author gbl
     * @date 2018/11/12 14:00
     **/

    public Page<UploadRecord> findByType(String type, Integer pageNum,Integer pageSize);
}
