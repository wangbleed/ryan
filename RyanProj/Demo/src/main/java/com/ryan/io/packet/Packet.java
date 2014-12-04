package com.ryan.io.packet;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 14-12-2
 * Time: 下午2:05
 * To change this template use File | Settings | File Templates.
 */
public class Packet implements Serializable{
    String msgId = UUID.randomUUID().toString().replace("-","");
    private String title;
    private String content;
    private Map<String, Object> extData = new HashMap<String, Object>();
    private Map<String, String> rulesMap = new HashMap<String, String>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, Object> getExtData() {
        return extData;
    }

    public void setExtData(Map<String, Object> extData) {
        this.extData = extData;
    }

    public Map<String, String> getRulesMap() {
        return rulesMap;
    }

    public void setRulesMap(Map<String, String> rulesMap) {
        this.rulesMap = rulesMap;
    }
}
