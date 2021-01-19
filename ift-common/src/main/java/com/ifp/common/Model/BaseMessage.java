package com.ifp.common.Model;


import com.ifp.common.Utils.UUIDGenerator;

import java.io.Serializable;

/**
 * mq消息基类
 * 统一规范日志展示使用
 * 所有mq消息继承此类
 *
 * @author Administrator
 */
public class BaseMessage implements Serializable {

    private String msgId;
    private String sourceId;
    private String functionCode;
    private String functionName;
    private String json;

    public BaseMessage() {
        msgId = UUIDGenerator.create32Key();
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getMsgId() {
        return msgId;
    }
}
