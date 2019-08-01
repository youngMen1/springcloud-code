package com.seal.eureka.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/31 16:40
 * @description swagger
 **/
@SuppressWarnings({"unused"})
@Configuration
@EnableSwagger2
public class Swagger2Config {

//    @Value("${swagger2.enable:true}")
//    private boolean enable;
//
//    @Bean("eureka-server")
//    public Docket userApis() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("注册中心")
//                .select()
//                .build()
//                .apiInfo(apiInfo())
//                .enable(enable);
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("注册中心")
//                .description("高可用注册中心")
//                .termsOfServiceUrl("https://github.com/springcloud-code/springcloud-eureka")
//                .version("1.0")
//                .build();
//    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.seal.eureka.*"))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("高可用注册中心")
                .description("https://github.com/springcloud-code/springcloud-eureka")
                .version("1.0")
                .build();
    }
}
