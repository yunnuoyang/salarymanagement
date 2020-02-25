package com.joinx.salary.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class DecorateMap extends LinkedHashMap {

    public DecorateMap put(Object key,Object value){
        super.put(key,value);
        return this;
    }
}
