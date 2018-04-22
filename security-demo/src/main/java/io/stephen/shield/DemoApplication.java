package io.stephen.shield;

import io.stephen.shield.demo.dto.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 10447
 * @since 2017/10/31
 */
@SpringBootApplication
@EnableSwagger2     // swager文档生成器
@RestController
@RequestMapping("/test")
public class DemoApplication {

    public static void main(String[] arg) {
        SpringApplication.run(DemoApplication.class, arg);
    }



    @RequestMapping("/user")
    public User queryUserInfo(String id){

        User user = new User();
        user.setId(id);
        user.setUsername("stephen");
        return user;
    }


}
