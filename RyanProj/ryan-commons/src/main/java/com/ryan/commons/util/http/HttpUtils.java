package com.ryan.commons.util.http;

import com.ryan.commons.constant.HttpConstant;
import com.ryan.commons.util.io.CloserUtil;
import com.ryan.commons.util.json.GsonUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by ryan on 15/5/5.
 * 用于http处理的工具类
 * 有2种提交
 * 1、参数形式提交（key/value）
 * 2、body形式提交（只限于post）
 * 以下Map<String, String> mapParms和String bodyParam，只能选其一
 */
public class HttpUtils {

    public static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    private boolean isStream = false;

    private String byteCotent = null;

    private Header[] responseHeaders = null;

    public String getByteContent(){
        return byteCotent;
    }

    public boolean isStream() {
        return isStream;
    }

    public void setStream(boolean isStream) {
        this.isStream = isStream;
    }

    public enum HttpMethodType{
        GET(1, HttpGet.METHOD_NAME), POST(2, HttpPost.METHOD_NAME), PUT(3, HttpPut.METHOD_NAME)
        , DELETE(4, HttpDelete.METHOD_NAME), HEAD(5, HttpHead.METHOD_NAME), TRACE(6, HttpTrace.METHOD_NAME)
        , PATCH(7, HttpPatch.METHOD_NAME), OPTIONS(8, HttpOptions.METHOD_NAME);

        private Integer code;
        private String name;

        private HttpMethodType(Integer code, String name){
            this.code = code;
            this.name = name;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getCodeByName(String name){
            for(HttpMethodType type : HttpMethodType.values()){
                if(type.getName().equals(name)){
                   return type.getCode();
                }
            }
            return -1;
        }

    }

    /**
     * return :{"code":xxxx, "content":""}
     * @param methodType
     * @param url
     * @param header
     * @param params
     * @param connectTimeout
     * @param connectRequestTimeout
     * @param socketTimeout
     * @return
     */
    public String doHttpMethod(@NotNull HttpMethodType methodType, @NotNull String url, Map<String, String> header
            , Map<String, String> params, String bodyParam, Integer connectTimeout, Integer connectRequestTimeout, Integer socketTimeout){
        return execHttpMethod(methodType, url, header, params, bodyParam, connectTimeout, connectRequestTimeout, socketTimeout);
    }

    /**
     * return : HttpRequestResult
     * @param url
     * @param header
     * @param params
     * @return
     */
    public HttpRequestResult doPost(String url, Map<String, String> header, Map<String, String> params, String bodyParam){
        GsonUtil gson = new GsonUtil();
        String reult = doPost(url, header, params, bodyParam, HttpConstant.HTTP_CONNECT_TIMEOUT, HttpConstant.HTTP_CONNECT_REQUEST_TIMEOUT, HttpConstant.HTTP_SOCKET_TIMEOUT);

        return gson.getBean(reult, HttpRequestResult.class);
    }

    /**
     * return :{"code":xxxx, "content":""}
     * @param url
     * @param header
     * @param params
     * @param connectTimeout
     * @param connectRequestTimeout
     * @param socketTimeout
     * @return
     */
    public String doPost(@NotNull String url, Map<String, String> header
            , Map<String, String> params, String bodyParam, Integer connectTimeout, Integer connectRequestTimeout, Integer socketTimeout){
        int cTimeout = HttpConstant.HTTP_CONNECT_TIMEOUT;
        int crTimeout = HttpConstant.HTTP_CONNECT_REQUEST_TIMEOUT;
        int sTimeout = HttpConstant.HTTP_SOCKET_TIMEOUT;
        if(connectTimeout != null)
            cTimeout = connectTimeout;
        if(connectRequestTimeout != null)
            crTimeout = connectRequestTimeout;
        if(socketTimeout != null)
            sTimeout = socketTimeout;
        return execHttpMethod(HttpMethodType.POST, url, header, params, bodyParam, cTimeout, crTimeout, sTimeout);
    }

    /**
     * return :{"code":xxxx, "content":""}
     * @param url
     * @param header
     * @param params
     * @return
     */
    public String doGet(String url, Map<String, String> header, Map<String, String> params, String bodyParam) {
        return doGet(url, header, params, bodyParam, HttpConstant.HTTP_CONNECT_TIMEOUT, HttpConstant.HTTP_CONNECT_REQUEST_TIMEOUT, HttpConstant.HTTP_SOCKET_TIMEOUT);
    }
    /**
     * return :{"code":xxxx, "content":""}
     * @param url
     * @return
     */
    public HttpRequestResult doGet(String url) {
        GsonUtil gson = new GsonUtil();
        String reult = doGet(url, null, null, null, HttpConstant.HTTP_CONNECT_TIMEOUT, HttpConstant.HTTP_CONNECT_REQUEST_TIMEOUT, HttpConstant.HTTP_SOCKET_TIMEOUT);

        return gson.getBean(reult, HttpRequestResult.class);
    }

    /**
     * return :{"code":xxxx, "content":""}
     * @param url
     * @param header
     * @param params
     * @param connectTimeout
     * @param connectRequestTimeout
     * @param socketTimeout
     * @return
     */
    public String doGet(String url, Map<String, String> header, Map<String, String> params
            , String bodyParam, Integer connectTimeout, Integer connectRequestTimeout, Integer socketTimeout){
        int cTimeout = HttpConstant.HTTP_CONNECT_TIMEOUT;
        int crTimeout = HttpConstant.HTTP_CONNECT_REQUEST_TIMEOUT;
        int sTimeout = HttpConstant.HTTP_SOCKET_TIMEOUT;
        if(connectTimeout != null)
            cTimeout = connectTimeout;
        if(connectRequestTimeout != null)
            crTimeout = connectRequestTimeout;
        if(socketTimeout != null)
            sTimeout = socketTimeout;
        return execHttpMethod(HttpMethodType.GET, url, header, params, bodyParam, cTimeout, crTimeout, sTimeout);
    }

    private String execHttpMethod(@NotNull HttpMethodType type, @NotNull String url, Map<String, String> header , Map<String, String> params
            , String bodyParams, @NotNull Integer connectTimeout, @NotNull Integer connectRequestTimeout, @NotNull Integer socketTimeout){
        HttpRequestResult requestResult = new HttpRequestResult();
        String status = "failure";
        UrlValidator validator = new UrlValidator();
        boolean bFlag = validator.isValid(url);
        if(!bFlag){
            logger.error("url is not valid, url:{}", url);
            requestResult.setCode(-1);
            return requestResult.toJson();
        }
        StringBuilder result = new StringBuilder();
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpRequestBase request = null;
        try{
            request = getHttpRequest(type, url, header, params, bodyParams);
            if(null == request)
                return "";
            request.setConfig(getRequestConfig(connectTimeout, connectRequestTimeout, socketTimeout));
            response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            if(HttpStatus.SC_OK == code)  {
                status = "success";
            }
            HttpEntity entity = response.getEntity();
            requestResult.setCode(code);
            if(entity.isStreaming()){
                setStream(entity.isStreaming());
                byteCotent = EntityUtils.toString(entity);
                requestResult.setContent("outputstream");

            } else {
                setStream(false);
                result.append(EntityUtils.toString(entity, HttpConstant.DEFAULT_HTTP_CHARSET));
                requestResult.setContent(result.toString());
            }
            getHeader(response);
            EntityUtils.consume(entity);
            logger.debug("exec {}, method:{}, httpCode:{}, responseContent:{}", status, type.getName(), code, request.toString());
        } catch (Exception e){
            logger.error("exec {}, method:{}, cause:{}", status, type.getName(), e.getMessage());
        } finally{
            CloserUtil.closeHTTPResponse(response);
            CloserUtil.closeHTTPRequest(request);
            CloserUtil.closeHTTPClient(client);
        }
        return requestResult.toJson();
    }

    private void getHeader(HttpResponse response){
        responseHeaders = response.getAllHeaders();
    }

    /**
     * 获取response的header
     * @return
     */
    public Header[] getResponseHeaders(){
        return responseHeaders;
    }

    /**
     * 获取对应的 http 请求
     * @param type
     * @param url
     * @param params
     * @return
     */
    private HttpRequestBase getHttpRequest(HttpMethodType type, String url, Map<String, String> header
            , Map<String, String> params, String bodyParam){
        HttpRequestBase request = null;
        StringBuilder sbUrl = new StringBuilder(url);
        if(HttpMethodType.GET == type){
            setGetParams(sbUrl, params);
            request = new HttpGet();
        } else if(HttpMethodType.POST == type){
            HttpPost requestPost = new HttpPost(sbUrl.toString());
            setHeader(requestPost, header);
            setParam(requestPost, params, bodyParam);
            return requestPost;
        } else if(HttpMethodType.DELETE == type){
            request = new HttpDelete();
        } else if(HttpMethodType.PUT == type){
            request = new HttpPut();
        } else if(HttpMethodType.HEAD == type){
            request = new HttpHead();
        }
        setHeader(request, header);
        request.setURI(URI.create(sbUrl.toString()));
        return request;
    }

    /**
     * 设置Http的header
     * @param request
     * @param header
     */
    private void setHeader(HttpRequestBase request, Map<String, String> header){
        if(header != null && !header.isEmpty()){
            for(Map.Entry<String, String> kv : header.entrySet()){
                request.setHeader(kv.getKey(), kv.getValue());
            }
        }
    }

    /**
     * 设置Http get参数
     * @param url
     * @param params
     */
    private void setGetParams(StringBuilder url, Map<String, String> params){
        if(params != null && !params.isEmpty()){
            try {
                url.append("?");
                for (Map.Entry<String, String> kv : params.entrySet()) {
                    url.append(kv.getKey()).append("=").append(URLEncoder.encode(kv.getValue(), HttpConstant.DEFAULT_HTTP_CHARSET));
                }
            }catch (Exception e){
                logger.error("http get add params error, cause:{}", e.getMessage());
            }
        }
    }

    /**
     * 设置http参数
     * @param request
     * @param params
     */
    private void setParam(HttpPost request, Map<String, String> params, String bodyParam){
        if(params != null && !params.isEmpty()){
            List<BasicNameValuePair> lst = Lists.newArrayList();
            for(Map.Entry<String, String> kv : params.entrySet()){
                lst.add(new BasicNameValuePair(kv.getKey(), kv.getValue()));
            }
            try {
                request.setEntity(new UrlEncodedFormEntity(lst, HttpConstant.DEFAULT_HTTP_CHARSET));
            }catch (Exception e){
                logger.error("http add params error, cause:{}", e.getMessage());
            }
        }
        if(StringUtils.isNotBlank(bodyParam)){
            StringEntity entity = new StringEntity(bodyParam, HttpConstant.DEFAULT_HTTP_CHARSET);
            entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, request.getFirstHeader(HTTP.CONTENT_TYPE).getValue()));
            request.setEntity(entity);
        }

    }

    private void setCookies(HttpRequestBase request, Map<String, String> cookies){
        if(cookies != null && !cookies.isEmpty()) {
            BasicCookieStore cookieStore = new BasicCookieStore();
            for(Map.Entry<String, String> kv : cookies.entrySet()){
//                Cookie
            }
        }
    }

    private RequestConfig getRequestConfig(int connectTimeout, int connectRequestTimeout
            , int socketTimeout){
        return RequestConfig.custom().setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectRequestTimeout)
                .setSocketTimeout(socketTimeout).build();
    }

}
