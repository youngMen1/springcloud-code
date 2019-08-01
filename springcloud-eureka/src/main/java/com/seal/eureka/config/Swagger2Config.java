package com.seal.eureka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhenpeng
 */
@SuppressWarnings({"unused"})
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Value("${swagger2.enable:true}")
    private boolean enable;

    @Bean("eureka-server")
    public Docket userApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("註冊中心")
                .select()
                .build()
                .apiInfo(apiInfo())
                .enable(enable);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("inf-註冊中心")
                .description("好豆子！")
                .termsOfServiceUrl("https://github.com/kolema-tech/spring-cloud-inf")
                .version("1.0")
                .build();
    }
}
