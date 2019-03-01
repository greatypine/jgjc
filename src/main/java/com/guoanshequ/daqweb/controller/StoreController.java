package com.guoanshequ.daqweb.controller;

import com.guoanshequ.daqweb.domain.NDRCProduct;
import com.guoanshequ.daqweb.domain.Store;
import com.guoanshequ.daqweb.domain.UploadRecord;
import com.guoanshequ.daqweb.repository.UploadRecordRepository;
import com.guoanshequ.daqweb.service.StoreService;
import com.guoanshequ.utils.CSVUtil;
import com.guoanshequ.utils.FTPClientUtil;
import com.guoanshequ.utils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ProjectName: sp
 * @Package: com.guoanshequ.daqweb.controller
 * @Description:
 * @Author: gbl
 * @CreateDate: 2018/11/2 13:42
 */
@RestController
public class StoreController {


    private static final Logger logger = LoggerFactory.getLogger(StoreController.class);

    @Value("${eprj.upload.url}")
    private String uploadUrl;

    @Value("${eprj.multi_upload.url}")
    private String multiUploadUrl;

    @Autowired
    private StoreService storeService;

    @Autowired
    private UploadRecordRepository uploadRecordRepository;

    /**
     * @Description 根据门店编号查询门店信息
     * @author gbl
     * @date 2018/11/8 14:01
     **/

    @RequestMapping(value="/storeByNo/{storeNo}",method= RequestMethod.GET)
    public Store findStoreByStoreNo(@PathVariable("storeNo") String storeNo){

        Store store= storeService.findStoreByStoreNo(storeNo);
        return store;
    }

    /**
     * @Description 根据门店状态查询门店信息
     * @author gbl
     * @date 2018/11/8 14:02
     **/

    @RequestMapping("/allStore/{estate}")
    public List<Store> findAllStore(@PathVariable("estate") String estate){

        List<Store> list = storeService.findAllStore(estate);
        return list;
    }

    /**
     * @Description 查询门店以及店长信息
     * @author gbl
     * @date 2018/11/8 14:02
     **/

    @RequestMapping("/allStoreAndStoreKeeper")
    public List<Map<String,Object>> findStoreAndStoreKeeper(){
         List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
         list = storeService.findStoreAndStoreKeeper();
        System.out.println("中文乱码");
         return list;
    }


    /**
     * @Description 导出门店以及店长信息
     * @author gbl
     * @date 2018/11/8 14:02
     **/

    @RequestMapping("/exportStoreInfoToCSV")
    @ResponseBody
    public void exportStoreInfoToCSV(@RequestParam("beginDate")String beginDate){


        List<Object[]> list = storeService.getStoreAndStorekeeper();

        String fileName = beginDate.replaceAll("-","")+"_GASQ_MD.csv";
        String[] headers = new String[]{"门店编码","门店名称","门店地址","所属城区","联系人","联系电话"};
        boolean result = CSVUtil.createCsvFile(fileName,headers,list);
        if(result){
            File file = new File(fileName);
            String uploadResult = HttpClientUtil.uploadSingleFile(uploadUrl,file);
            HttpClientUtil.deleteFile(file);

            UploadRecord record = new UploadRecord();
            record.setFileName(fileName);
            record.setCreateTime(new Date());
            record.setType("ndrc_store");
            if("fail".equals(uploadResult)){
                record.setResult("fail");
            }else{
                record.setResult("success");
            }
            uploadRecordRepository.save(record);

        }
    }

}
