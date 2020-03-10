package com.tl666.comments.resultpojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回结果的封装类
 * @param <T>
 */
@Data
public class ResultDT<T> implements Serializable{

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private T data;

    /**
     * 附加数据
     */
    private T addData;

    public ResultDT() {
    }
}
