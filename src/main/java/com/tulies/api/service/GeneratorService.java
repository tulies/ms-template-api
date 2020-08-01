package com.tulies.api.service;

import com.tulies.api.entity.Generator;
import org.springframework.stereotype.Service;

/**
 * @author 王嘉炀 346461062@qq.com
 * @date 2020/7/31 22:37
 */
public interface GeneratorService {
    Integer autoGeneratorValue(String key);
//    Generator setGeneratorValue(Generator generator);
}
