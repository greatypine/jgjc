package com.guoanshequ.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: jgjc
 * @Package: com.guoanshequ.utils
 * @Description: HttpClient 工具类
 * @Author: gbl
 * @CreateDate: 2018/11/7 16:23
 */
@Component
public class HttpClientUtil {


    private static  boolean httpProxySwitch;

    private static String httpProxyServer;

    private static  Integer httpProxyPort;

    @Value("${http.proxy.switch}")
    public  void setHttpProxySwitch(boolean httpProxySwitch) {
        HttpClientUtil.httpProxySwitch = httpProxySwitch;
    }

    @Value("${http.proxy.server}")
    public  void setHttpProxyServer(String httpProxyServer) {
        HttpClientUtil.httpProxyServer = httpProxyServer;
    }

    @Value("${http.proxy.port}")
    public  void setHttpProxyPort(Integer httpProxyPort) {
        HttpClientUtil.httpProxyPort = httpProxyPort;
    }

    private static  final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
    // 默认字符集
    private static final String ENCODING = "UTF-8";

    /**
     * @Description: TODO(发送post请求)
     * @param url 请求地址
     * @param headers 请求头
     * @param data 请求实体
     * @param encoding 字符集
     * @author gbl
     * @return String
     * @date 2018/11/7 16:42
     * @throws
     */

    public static String sendPost(String url, Map<String, String> headers, JSONObject data, String encoding) {
        log.info("进入post请求方法...");
        log.info("请求入参：URL= " + url);
        log.info("请求入参：headers=" + JSON.toJSONString(headers));
        log.info("请求入参：data=" + JSON.toJSONString(data));
        // 请求返回结果
        String resultJson = null;
        // 创建Client
        CloseableHttpClient client = null;
        // 创建HttpPost对象
        HttpPost httpPost = new HttpPost(url);

        try {

            if(httpProxySwitch){
                //设置代理IP、端口、协议（请分别替换）
                HttpHost proxy = new HttpHost(httpProxyServer, httpProxyPort, "http");

                //把代理设置到请求配置
                RequestConfig defaultRequestConfig = RequestConfig.custom() .setProxy(proxy).build();

                //实例化CloseableHttpClient对象
                client = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();

            }else{
                client = HttpClients.custom().build();
            }
            // 设置请求头
            if (headers != null) {
                Header[] allHeader = new BasicHeader[headers.size()];
                int i = 0;
                for (Map.Entry<String, String> entry: headers.entrySet()){
                    allHeader[i] = new BasicHeader(entry.getKey(), entry.getValue());
                    i++;
                }
                httpPost.setHeaders(allHeader);
            }
            // 设置实体
            httpPost.setEntity(new StringEntity(JSON.toJSONString(data)));
            // 发送请求,返回响应对象
            CloseableHttpResponse response = client.execute(httpPost);
            // 获取响应状态
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                // 获取响应结果
                resultJson = EntityUtils.toString(response.getEntity(), encoding);
            } else {
                log.error("响应失败，状态码：" + status);
            }

        } catch (Exception e) {
            log.error("发送post请求失败", e);
        } finally {
            httpPost.releaseConnection();
        }
        return resultJson;
    }

    /**
     * @Description: 发送post请求，请求数据默认使用json格式，默认使用UTF-8编码
     * @param url 请求地址
     * @param data 请求实体
     * @author gbl
     * @return String
     * @date 2018/11/7 16:43
     * @throws
     */

    public static String sendPost(String url, JSONObject data) {
        // 设置默认请求头
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json;charset=utf-8");

        return sendPost(url, headers, data, ENCODING);
    }

    /**
     * @Description: 发送post请求，请求数据默认使用json格式，默认使用UTF-8编码
     * @param url 请求地址
     * @param params 请求实体
     * @author gbl
     * @return String
     * @date 2018/11/7 16:43
     * @throws
     */
    public static String sendPost(String url,Map<String,Object> params){
        // 设置默认请求头
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json;charset=utf-8");
        // 将map转成json
        JSONObject data = JSONObject.parseObject(JSON.toJSONString(params));
        return sendPost(url,headers,data,ENCODING);
    }

    /**
     * @Description: 发送post请求，请求数据默认使用UTF-8编码
     * @param url 请求地址
     * @param headers 请求头
     * @param data 请求实体
     * @author gbl
     * @return String
     * @date 2018/11/7 16:43
     * @throws
     */
    public static String sendPost(String url, Map<String, String> headers, JSONObject data) {
        return sendPost(url, headers, data, ENCODING);
    }

    /**
     * @Description:发送post请求，请求数据默认使用UTF-8编码
     * @param url 请求地址
     * @param headers 请求头
     * @param params 请求实体
     * @author gbl
     * @return String
     * @date 2018/11/7 16:43
     * @throws
     */
    public static String sendPost(String url,Map<String,String> headers,Map<String,String> params){
        // 将map转成json
        JSONObject data = JSONObject.parseObject(JSON.toJSONString(params));
        return sendPost(url,headers,data,ENCODING);
    }

    /**
     * @Description: 发送get请求
     * @param url 请求地址
     * @param params 请求参数
     * @param encoding 编码
     * @author gbl
     * @return String
     * @date 2018/11/7 16:43
     * @throws
     */
    public static String sendGet(String url,Map<String,Object> params,String encoding){
        log.info("进入get请求方法...");
        log.info("请求入参：URL= " + url);
        log.info("请求入参：params=" + JSON.toJSONString(params));
        // 请求结果
        String resultJson = null;
        // 创建client
        CloseableHttpClient client = null;
        // 创建HttpGet
        HttpGet httpGet = new HttpGet();
        try {

            if(httpProxySwitch){
                //设置代理IP、端口、协议（请分别替换）
                HttpHost proxy = new HttpHost(httpProxyServer, httpProxyPort, "http");

                //把代理设置到请求配置
                RequestConfig defaultRequestConfig = RequestConfig.custom() .setProxy(proxy).build();

                //实例化CloseableHttpClient对象
                client = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();

            }else{
                client = HttpClients.custom().build();
            }
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            // 封装参数
            if(params != null){
                for (String key : params.keySet()) {
                    builder.addParameter(key, params.get(key).toString());
                }
            }
            URI uri = builder.build();
            log.info("请求地址："+ uri);
            // 设置请求地址
            httpGet.setURI(uri);
            // 发送请求，返回响应对象
            CloseableHttpResponse response = client.execute(httpGet);
            // 获取响应状态
            int status = response.getStatusLine().getStatusCode();
            if(status == HttpStatus.SC_OK){
                // 获取响应数据
                resultJson = EntityUtils.toString(response.getEntity(), encoding);
            }else{
                log.error("响应失败，状态码：" + status);
            }
        } catch (Exception e) {
            log.error("发送get请求失败",e);
        } finally {
            httpGet.releaseConnection();
        }
        return resultJson;
    }
    /**
     * @Title: sendGet
     * @Description: 发送get请求
     * @param url 请求地址
     * @param params 请求参数
     * @author gbl
     * @return String
     * @date 2018/11/7 16:45
     * @throws
     */
    public static String sendGet(String url,Map<String,Object> params){
        return sendGet(url,params,ENCODING);
    }
    /**
     * @Description: 发送get请求
     * @param url 请求地址
     * @author gbl
     * @return String
     * @date 2018/11/7 16:46
     * @throws
     */
    public static String sendGet(String url){
        return sendGet(url,null,ENCODING);
    }


    /**
     * @Description 上传多文件
     * @param fileNames 文件名
     * @param url 地址
     * @author gbl
     * @date 2018/11/7 16:47
     **/

    public static String uploadMultiFile(String url, String[] fileNames) {
        String  result = "";
        CloseableHttpClient httpClient = null;
        HttpPost httpPost;

        CloseableHttpResponse response =  null;
        log.info("开始上传文件："+fileNames);
        try {

            if(httpProxySwitch){
                //设置代理IP、端口、协议（请分别替换）
                HttpHost proxy = new HttpHost(httpProxyServer, httpProxyPort, "http");

                //把代理设置到请求配置
                RequestConfig defaultRequestConfig = RequestConfig.custom().setProxy(proxy).build();

                //实例化CloseableHttpClient对象
                httpClient = HttpClientBuilder.create().setDefaultRequestConfig(defaultRequestConfig).build();

            }else{
                httpClient = HttpClients.custom().build();
            }
            httpPost = new HttpPost(url);

            MultipartEntityBuilder multipartEntityBuilder =  MultipartEntityBuilder.create();


            for(int i=0;i<fileNames.length;i++) {
                String fileName=fileNames[i];
                multipartEntityBuilder.addBinaryBody("file"+(i+1),new File(fileName));
            }

            HttpEntity reqEntity = multipartEntityBuilder.build();
            httpPost.setEntity(reqEntity);

            response = httpClient.execute(httpPost);
            if (null != response && response.getStatusLine().getStatusCode() == 200) {
                HttpEntity resEntity = response.getEntity();
                if (null != resEntity) {
                    result = EntityUtils.toString(resEntity);
                    System.out.println(result);
                    log.info(fileNames+"  上传文件结果："+result);

                }
                EntityUtils.consume(resEntity);
            }

        }  catch (ClientProtocolException e) {
            System.out.println(e.getMessage());
            log.info("上传文件失败："+e.getMessage());
            result = "fail";
        } catch (IOException e) {
            System.out.println(e.getMessage());
            log.info("上传文件失败："+e.getMessage());
            result = "fail";
        } catch (Exception e) {
            System.out.println(e.toString());
            log.info("上传文件失败："+e.toString());
            result = "fail";
        } finally {

            try {
                //关闭返回
                if(response!=null){
                    response.close();
                }

                // 关闭连接，释放资源
                if(httpClient!=null){
                    httpClient.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return result;
    }

    /**
     * @Description 上传单个文件
     * @param url 上传路径
     * @param file 上传文件
     * @author gbl
     * @date 2018/11/8 10:45
     **/

    public  static  String uploadSingleFile(String url ,File file){

        String result  = "";
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {
            if(httpProxySwitch){
                //设置代理IP、端口、协议
                HttpHost proxy = new HttpHost(httpProxyServer, httpProxyPort, "http");

                //把代理设置到请求配置
                RequestConfig defaultRequestConfig = RequestConfig.custom().setProxy(proxy).build();

                //实例化CloseableHttpClient对象
                httpClient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();

            }else{
                httpClient = HttpClientBuilder.create().build();
            }

            HttpPost httpPost = new HttpPost(URI.create(url));
            FileBody fileBody = new FileBody(file);
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            multipartEntityBuilder.addPart("file",fileBody);
            HttpEntity reqEntity = multipartEntityBuilder.build();
            httpPost.setEntity(reqEntity);
            httpResponse = httpClient.execute(httpPost);

            HttpEntity resEntity = httpResponse.getEntity();
            int statusCode= httpResponse.getStatusLine().getStatusCode();
            if(statusCode == 200){
//                InputStream stream = reqEntity.getContent();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
//                StringBuffer buffer = new StringBuffer();
//                String str = "";
//                while((reader.readLine())!=null||!reader.readLine().isEmpty()) {
//                    buffer.append(reader.readLine());
//                }
                result = EntityUtils.toString(resEntity);
                log.info(file.getName()+"文件上传结果："+result.toString());
//                System.out.println(buffer.toString());
//                result = buffer.toString();
//                if(stream!= null){
//                    stream.close();
//                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
            log.info(file.getName()+" 文件上传失败"+e.toString());
           result = "fail";
        }catch (Exception e) {
            System.out.println(e.getMessage());
            log.info(file.getName()+" 文件上传失败"+e.toString());
            result = "fail";
        } finally {
                try {
                    if(httpResponse!=null){
                        httpResponse.close();
                    }

                    if(httpClient!=null){
                        httpClient.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
        return result;
    }


    /**
     * @Description 删除文件
     * @param file  文件
     * @author gbl
     * @date 2018/11/8 11:32
     **/

    public static void deleteFile(File file){
        try {
            if(file.exists()){
                file.delete();
                log.info(" 删除文件："+file.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(file.getName()+" 删除文件："+e.toString());
        }
    }

    /**
     * @Description 删除多文件
     * @param fileNames 文件名
     * @author gbl
     * @date 2018/11/8 11:34
     **/

    public static void deleteFile(String[] fileNames){
        for(String fileName:fileNames){
            File file = null;
            try {
                file = new File(fileName);
                if(file.exists()){
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.info(file.getName()+ " 删除文件："+e.toString());
            }

        }
    }

}
