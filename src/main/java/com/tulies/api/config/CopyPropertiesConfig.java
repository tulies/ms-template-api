package com.tulies.api.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王嘉炀
 * @date 2020/7/14 2:13 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CopyPropertiesConfig {
//    哪些字段强制不替换
    private String[] ignoreProperties = null;
//    是否允许null替换
    private Boolean notNull = true;

}
