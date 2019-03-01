package com.guoanshequ.daqweb.service.impl;

import com.guoanshequ.daqweb.domain.UploadRecord;
import com.guoanshequ.daqweb.repository.UploadRecordRepository;
import com.guoanshequ.daqweb.service.UploadRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @ProjectName: jgjc
 * @Package: com.guoanshequ.daqweb.service.impl
 * @Description:
 * @Author: gbl
 * @CreateDate: 2018/11/12 13:58
 */
@Service
public class UploadRecordServiceImpl implements UploadRecordService {

    @Autowired
    private UploadRecordRepository uploadRecordRepository;

    @Override
    public Page<UploadRecord> findByType(String type, Integer pageNum,Integer pageSize){
//        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Integer pageNo = (pageNum==null||pageNum<=0)?0:pageNum-1;
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        return uploadRecordRepository.findByType(type,pageable);
    }


}
