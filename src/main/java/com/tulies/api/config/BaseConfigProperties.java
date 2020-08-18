package com.tulies.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 王嘉炀
 * @date 2018/7/12 下午10:24
 */
@ConfigurationProperties(prefix = "app.base")
@Data
@Component
public class BaseConfigProperties {

    // 文件访问url的前面域名部分，这个地方需要主要，要配置nginx
//    private String fileUrlHost;
//    // 服务器路径的存放目录
//    private String uploadPathPrefix;
//    // 上传路径的前面部分
//    private String filePathPrefix;
//    //临时路径
//    private String uploadTempPrefix;
//    //上传excel路径
//    private String fileImport;
//    //导出excel路径
//    private String fileExport;
}

