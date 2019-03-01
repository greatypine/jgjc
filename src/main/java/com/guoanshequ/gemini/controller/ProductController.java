package com.guoanshequ.gemini.controller;

import com.guoanshequ.daqweb.domain.NDRCProduct;
import com.guoanshequ.daqweb.domain.Store;
import com.guoanshequ.daqweb.domain.UploadRecord;
import com.guoanshequ.daqweb.repository.UploadRecordRepository;
import com.guoanshequ.daqweb.service.NDRCProductService;
import com.guoanshequ.daqweb.service.StoreService;
import com.guoanshequ.gemini.domain.Product;
import com.guoanshequ.gemini.repository.ProductRepository;
import com.guoanshequ.gemini.service.ProductService;
import com.guoanshequ.utils.CSVUtil;
import com.guoanshequ.utils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ProjectName: sp
 * @Package: com.guoanshequ.gemini.controller
 * @Description:
 * @Author: gbl
 * @CreateDate: 2018/11/2 15:19
 */
@RestController
public class ProductController {

    private  static  final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Value("${eprj.upload.url}")
    private String uploadUrl;

    @Value("${eprj.multi_upload.url}")
    private String multiUploadUrl;

    @Autowired
    private ProductService productService;

    @Autowired
    private NDRCProductService ndrcProductService;

    @Autowired
    private UploadRecordRepository uploadRecordRepository;

    @Autowired
    private StoreService storeService;



    /**
     * @Description 查询商品销售信息
     * @author gbl
     * @date 2018/11/6 14:02
     **/

    @RequestMapping(value = "/productsavles")
    @ResponseBody
    public List<Map<String,Object>> findProductSales(@RequestParam("beginDate")String beginDate,@RequestParam("endDate")String endDate){

        List<Map<String,Object>> list = productService.findAllProductSales(beginDate,endDate);
        return list;
    }


    /**
     * @Description 导出门店商品销售信息
     * @author gbl
     * @date 2018/11/8 14:03
     **/

    @RequestMapping("/exportProductSalesDataToCSV")
    @ResponseBody
    public void exportProductSalesDataToCSV(@RequestParam("beginDate")String beginDate,@RequestParam("endDate")String endDate){

        List<NDRCProduct> ndrcProducts = ndrcProductService.findAllNDRCProduct();
        if(ndrcProducts!=null&&ndrcProducts.size()>0){
           List<String> names = new ArrayList<String>();
            StringBuilder stringBuilder = new StringBuilder();
            for(NDRCProduct obj:ndrcProducts){
                names.add(obj.getName());
                stringBuilder.append("'").append(obj.getName()).append("',");
            }
            System.out.println(stringBuilder.toString());

            List<Store> storeList = storeService.findByCityName("北京");
            StringBuilder storeSb = new StringBuilder();
            List<String> storeIdList = new ArrayList<String>();
            if(storeList!=null&&storeList.size()>0){
                for(Store store:storeList){
                    storeIdList.add(store.getPlatformid());
                    storeSb.append("'").append(store.getPlatformid()).append(",");
                }

            }


            List<Object[]> list = productService.getProductSales(beginDate,endDate,names,storeIdList);


            String fileName = beginDate.replaceAll("-","")+"_GASQ_JG.csv";
            String[] headers = new String[]{"日期","商品内部编码","商品名称","商品条形码","内部分类编码","内部分类名称","规格","单位","标准售价","门店编码","门店名称","门店销售额","备注"};

            boolean result = CSVUtil.createCsvFile(fileName,headers,list);
            if(result){
                String uploadResult = HttpClientUtil.uploadMultiFile(multiUploadUrl,new String[]{fileName});
                HttpClientUtil.deleteFile(new String[]{fileName});

                UploadRecord record = new UploadRecord();
                record.setFileName(fileName);
                record.setCreateTime(new Date());
                record.setType("ndrc_product");
                if("fail".equals(uploadResult)){
                    record.setResult("fail");
                }else{
                    record.setResult("success");
                }
                uploadRecordRepository.save(record);
            }
        }else{
            System.out.println("导出商品失败，没有合适的商品类型！");
            log.info("导出商品失败，没有合适的商品类型！");
        }



    }

}
