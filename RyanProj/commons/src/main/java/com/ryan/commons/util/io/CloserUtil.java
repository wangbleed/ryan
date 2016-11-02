package com.ryan.commons.util.io;

import ch.qos.logback.core.util.CloseUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by ryan on 15/5/25.
 * 关闭流的工具
 */
public class CloserUtil {

    public static final Logger logger = LoggerFactory.getLogger(CloseUtil.class);

    public static void closeIO(Closeable io){
        if(io != null){
            try{
                io.close();
            } catch (IOException e){
                logger.error("close io error, cause:{}", e.getMessage());
            }
        }
    }

    public static void closeHTTPRequest(HttpRequestBase request){
        if(request != null){
            try{
                request.releaseConnection();
            }catch (Exception e){
                logger.error("close http io error, cause:{}", e.getMessage());
            }
        }
    }

    public static void closeHTTPResponse(CloseableHttpResponse response){
        if(response != null){
            try{
                response.close();
            }catch (Exception e){
                logger.error("close http response error, cause:{}", e.getMessage());
            }
        }
    }

    public static void closeHTTPClient(CloseableHttpClient client){
        if(client != null){
            try{
                client.close();
            }catch (Exception e){
                logger.error("close http client error, cause:{}", e.getMessage());
            }
        }
    }

}
