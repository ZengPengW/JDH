package com.jdh;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages={"com.jdh.feign"})
public class Application_start {

    public static void main(String[] args) {

        SpringApplication.run(Application_start.class,args);

        System.out.println(
                "| |              /  \\/ )                \n" +
                "| | _   _   _   (_/\\__/    _____  ____  \n" +
                "| || \\ | | | |            (___  )|  _ \\ \n" +
                "| |_) )| |_| |             / __/ | | | |\n" +
                "|____/  \\__  |            (_____)| ||_/ \n" +
                "       (____/                    |_|    ");
    }



    //配置fastjon来解析对象
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 定义一个信息转换对象
        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
        // 定义一个配置信息 比如要返回的json
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter=fastJsonConverter;
        return new HttpMessageConverters(converter);
    }
}
