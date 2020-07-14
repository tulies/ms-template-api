package com.tulies.api.utils;

import com.tulies.api.config.CopyPropertiesConfig;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 王嘉炀
 * @date 2020/7/14 1:21 下午
 */
public class BeanUtil {
    public static void copyProperties(Object source, Object target){
        copyProperties(source, target, new CopyPropertiesConfig());
    }
    public static void copyProperties(Object source, Object target, String... ignoreProperties) throws BeansException {
        copyProperties(source, target, new CopyPropertiesConfig(ignoreProperties, true));
    }

    public static void copyProperties(Object source, Object target,CopyPropertiesConfig copyPropertiesConfig){
        String[] ignoreProperties = getIgnoreProperties(source,copyPropertiesConfig);
        org.springframework.beans.BeanUtils.copyProperties(source, target, ignoreProperties);
    }

    // 忽略参数
    public static String[] getIgnoreProperties(Object source, CopyPropertiesConfig config){
        Boolean notNull = config.getNotNull();
        Set<String> ignoreSet = null;
        if(notNull){
            ignoreSet = getNullPropertyNames(source);
        }
        if(config.getIgnoreProperties() != null){
            ignoreSet.addAll(new HashSet<String>(Arrays.asList(config.getIgnoreProperties())));
        }
        String[] result = new String[ignoreSet.size()];
        return ignoreSet.toArray(result);
    }

    public static Set<String> getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<String>();
        for(PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        return emptyNames;
    }
}
