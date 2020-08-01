package com.tulies.api.service.impl;

import com.tulies.api.entity.Generator;
import com.tulies.api.enums.ResultEnum;
import com.tulies.api.exception.AppException;
import com.tulies.api.repository.GeneratorRepository;
import com.tulies.api.service.GeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 王嘉炀 346461062@qq.com
 * @date 2020/7/31 22:39
 */
@Slf4j
@Service
@Transactional
public class GeneratorServiceImpl implements GeneratorService {
    @Autowired
    private GeneratorRepository generatorRepository;

    // 获取自增长id，获取成功之后，会把id加1存进去。
    public Integer autoGeneratorValue(String key) {
        log.info("key={}",key);
        Generator generator = this.generatorRepository.findByKey(key);
        if(generator == null){
            throw new AppException(ResultEnum.DATA_NOT_EXIT);
        }
        // 更新value
        generator.setValue(generator.getValue()+1);
        this.generatorRepository.save(generator);
        return generator.getValue();
    }
//    @Override
//    public Integer getGeneratorValue(String key) {
//        Generator generator = this.generatorRepository.findByKey(key);
//        if(generator != null){
//            return generator.getValue();
//        }
//        return null;
//    }

//    @Override
//    @Transactional
//    public Generator setGeneratorValue(Generator generator) {
//        return this.generatorRepository.save(generator);
//    }
}
