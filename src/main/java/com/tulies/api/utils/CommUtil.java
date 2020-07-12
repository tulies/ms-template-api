package com.tulies.api.utils;

import org.springframework.data.domain.Sort;

/**
 * @author 王嘉炀
 * @date 2018/7/10 下午3:40
 */
public class CommUtil {
    public static Sort formatSorter(String sorter){


        try{
            String[] sortstr = sorter.split(" ");

            if ("desc".equals(sortstr[1])) {
                return Sort.by(Sort.Direction.DESC,sortstr[0]);
            }
            return Sort.by(Sort.Direction.ASC,sortstr[0]);

        }catch(Exception e){
            return null;
        }

    }
}
