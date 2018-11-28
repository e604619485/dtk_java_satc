package com.dtk.satc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title: SystemConfig
 * @Description: 系统配置
 * @Author <a href="mailto:chenxb1993@126.com">陈晓博</a>
 * @Date 2018-06-25 9:02
 * @Version V1.0
 */
@Configuration
public class SystemConfig {

    /**
     * cms-topic
     */
    public static String CMS_TOPIC;

    /**
     * cms-topic
     */
    @Value("${params.cms-topic}")
    private String cmsTopic;

    /**
     * web-topic
     */
    public static String WEB_TOPIC;

    /**
     * web-topic
     */
    @Value("${params.web-topic}")
    private String webTopic;


    @Bean
    public String init(){
        CMS_TOPIC = cmsTopic;
        WEB_TOPIC = webTopic;
        return null;
    }

}
