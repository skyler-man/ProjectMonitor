package com.taocares.monitor.common;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 15:59 2019/7/9
 */
public class HttpUtil {

    /**
     * get方式
     * @param url
     * @author www.yoodb.com
     * @return
     */
    public static String getHttp(String url) {
        String responseMsg = "";
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
        try {
            httpClient.executeMethod(getMethod);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = getMethod.getResponseBodyAsStream();
            int len = 0;
            byte[] buf = new byte[1024];
            while((len=in.read(buf))!=-1){
                out.write(buf, 0, len);
            }
            responseMsg = out.toString("UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放连接
            getMethod.releaseConnection();
        }
        return responseMsg;
    }



}
