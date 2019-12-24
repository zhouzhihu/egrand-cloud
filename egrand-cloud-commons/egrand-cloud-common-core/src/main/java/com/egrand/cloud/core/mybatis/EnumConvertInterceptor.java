package com.egrand.cloud.core.mybatis;

public interface EnumConvertInterceptor {
    boolean convert(EntityMap map, String key, Object v);
}
