package com.guoanshequ.daqweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.guoanshequ.daqweb.domain.UploadRecord;
import com.guoanshequ.daqweb.repository.UploadRecordRepository;
import com.guoanshequ.daqweb.service.UploadRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: jgjc
 * @Package: com.guoanshequ.daqweb.controller
 * @Description:
 * @Author: gbl
 * @CreateDate: 2018/11/12 9:33
 */
@Controller
public class UpLoadRecordController {

    @Autowired
    private UploadRecordService uploadRecordService;

    @RequestMapping("/uploadRecord/{type}")
    public String findUploadRecorByType(@PathVariable("type")String type, Model model){
        String view = "";
        if("store".equals(type)){
            view = "storeRecord.html";
        }else if("product".equals(type)){
            view = "productRecord.html";
        }else{
            view="404.html";
        }

        return  view;

    }


    @RequestMapping("/uploadRecordOfPage")
    @ResponseBody
    public JSONObject findUploadRecorOfPage(@RequestParam(value = "pageNo")Integer pageNo, @RequestParam(value="pageSize")Integer pageSize, @RequestParam(value = "type")String type){

        Page<UploadRecord> list = uploadRecordService.findByType(type,pageNo,pageSize);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("recordList",list);

        return  jsonObject;
    }
}
