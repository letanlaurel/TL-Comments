package com.tl666.comments.utils;


import com.tl666.comments.resultpojo.ResultDT;

/**
 * 封装返回结果集的工具类
 */
public class ResultDTUtils {
    public static final Integer COMMENT_ERROR = 403;//评论格式非法
    public static final Integer NULL_DATA = 401; //空数据
    public static final Integer SUBMIT_ERROR = 400; //提交错误

    public static ResultDT success(Object object) {
        ResultDT resultVO = new ResultDT();
        resultVO.setCode(200);
        resultVO.setMsg("success");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultDT success(Object o1,Object o2) {
        ResultDT resultVO = new ResultDT();
        resultVO.setCode(200);
        resultVO.setMsg("success");
        resultVO.setData(o1);
        resultVO.setAddData(o2);
        return resultVO;
    }

    public static ResultDT success() {
        return success(null);
    }

    public static ResultDT error(Integer code, String msg) {
        ResultDT resultVO = new ResultDT();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }


}
