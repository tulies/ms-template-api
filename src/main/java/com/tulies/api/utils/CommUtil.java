package com.tulies.api.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Sort;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 王嘉炀
 * @date 2018/7/10 下午3:40
 */
public class CommUtil {
    public static Sort formatSorter(String sorter){


        try{
            String[] sortstr = sorter.split(" ");

            if ("desc".equals(sortstr[1]) || "descend".equals(sortstr[1])) {
                return Sort.by(Sort.Direction.DESC,sortstr[0]);
            }
            return Sort.by(Sort.Direction.ASC,sortstr[0]);

        }catch(Exception e){
            return null;
        }

    }
}
