package com.tl666.comments.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存类
 */
public class TLCache {
    private TLCache(){};
    private static Map<String,Object> map = new HashMap();

    public static Map<String,Object> getMap(){
        return map;
    }
}