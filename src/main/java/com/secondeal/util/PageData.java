package com.secondeal.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PageData extends HashMap implements Map {
    Map map = null;
    HttpServletRequest request;

    @SuppressWarnings({"unchecked"})
    public PageData(Map<String, Object> request) {
        this.request = (HttpServletRequest) request;
        Map properties = ((HttpServletRequest) request).getParameterMap();
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
//此处需要注意的是前台传过来的是String数组对象就用String[]来判断，如果传递过来是json对象可以用JSONObject来尝试(这种方法没试过，应该是可以的)，
//也可以传递在前台拼接个字符串后，后台直接用String来判断，然后用split方法来截断(这种当然可以，没试过，也不太方便)
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        map = returnMap;
    }
}
