package io.stephen.security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 10447
 * @since 2017/10/31
 */
@SpringBootApplication
@EnableSwagger2     // swager文档生成器
public class DemoApplication {

    public static void main(String[] arg) {
        SpringApplication.run(DemoApplication.class, arg);
    }

}
