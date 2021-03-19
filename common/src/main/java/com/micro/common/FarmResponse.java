package com.micro.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FarmResponse<T> implements Serializable {
    @ApiModelProperty(value = "http状态码", example = "0")
    private int code;
    @ApiModelProperty(value = "以key/value键值对形式返回的消息")
    private Map<String, String> messages = new HashMap<String, String>();
    @ApiModelProperty(value = "数据对象")
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Map<String, String> getMessages() {
        return messages;
    }

    public void setMessages(Map<String, String> messages) {
        this.messages = messages;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private FarmResponse(int code, String key, String msg){
        this.code =code;
        messages.put(key, msg);
    }

    private FarmResponse(int code, Map<String, String> messages){
        this.code = code;
        this.messages = messages;
    }

    public static <T> FarmResponse success(String msg){
        FarmResponse response = new FarmResponse(HttpStatus.OK.value(), Constants.API_RESPONSE_SUCCESS_MESSAGE_DEFAULT_KEY, msg);
        return response;
    }

    private static <T> FarmResponse success(Map<String, String> messages){
        FarmResponse response = new FarmResponse(HttpStatus.OK.value(), messages);
        return response;
    }

    public static <T> FarmResponse success(){
        FarmResponse response = success(Constants.API_RESPONSE_SUCCESS_MESSAGE_DEFAULT_VALUE);
        return response;
    }


    public static <T> FarmResponse success(T data){
        FarmResponse response = success(Constants.API_RESPONSE_SUCCESS_MESSAGE_DEFAULT_VALUE);
        response.setData(data);
        return response;
    }

    public static <T> FarmResponse fail(HttpStatus status, String message){
        FarmResponse response = new FarmResponse(status.value(), Constants.API_RESPONSE_FAIL_MESSAGE_DEFAULT_KEY, message);
        return response;
    }

    public static <T> FarmResponse fail(HttpStatus status, Map<String, String> messages){
        FarmResponse response = new FarmResponse(status.value(), messages);
        return response;
    }
}
