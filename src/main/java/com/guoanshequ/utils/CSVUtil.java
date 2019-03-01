package com.guoanshequ.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * @ProjectName: sp
 * @Package: com.guoanshequ.utils
 * @Description:csv 文件工具类
 * @Author: gbl
 * @CreateDate: 2018/11/5 16:53
 */
public class CSVUtil {


    private static  final Logger logger = LoggerFactory.getLogger(CSVUtil.class);
    /**
     * @Description 创建csv 文件
     * @param headers 文件表头
     * @param fileName 本地文件名
     * @param data 数据
     * @author gbl
     * @date 2018/11/5 17:13
     **/

    public static boolean createCsvFile(String fileName,Object[] headers,List<Object[]> data){

        boolean result = true;
         //初始化csvformat
        CSVFormat formator = CSVFormat.DEFAULT;

         //创建FileWriter对象
        OutputStream os = null;
        OutputStreamWriter osw = null;
        File file = new File(fileName);
        if(file.exists()){
            file.delete();
        }
        try {
            os = new FileOutputStream(fileName);
            osw = new OutputStreamWriter(os,"gb2312");
            //创建CSVPrinter对象
            CSVPrinter printer=new CSVPrinter(osw,formator);

            //写入列头数据
            printer.printRecord(headers);

            if(null!=data){
                //循环写入数据
                for(Object[] lineData:data){

                    printer.printRecord(lineData);

                }
            }
            osw.flush();
            logger.info("创建csv文件成功");
        } catch (IOException e) {
            result=false;
            e.printStackTrace();
            logger.info("创建csv文件失败>>>>>"+fileName+e.getMessage());
            return result;
        }finally {
                try {
                    if(os!=null){
                        os.close();
                    }

                    if(osw!=null){
                        osw.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }

        }
        return  result;
    }
}

