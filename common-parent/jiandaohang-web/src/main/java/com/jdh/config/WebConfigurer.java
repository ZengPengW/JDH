package com.jdh.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Value("${myparameter.uploadPath}")
    private String materialPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       // System.out.println(materialPath+" 66666666666666666666666666666666666666666");
        //配置server虚拟路径，handler为访问的目录
        registry.addResourceHandler("/material/**").addResourceLocations("file:///"+materialPath);
        //registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }



//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginHandlerInterceptor).excludePathPatterns(Arrays.asList("/views/**", "/res/**"));
//    }


}
