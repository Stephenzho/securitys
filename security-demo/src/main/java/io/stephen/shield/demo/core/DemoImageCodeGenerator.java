package io.stephen.shield.demo.core;

import io.stephen.shield.core.validate.code.ValidateCode;
import io.stephen.shield.core.validate.code.image.ImageCode;
import io.stephen.shield.core.validate.code.ValidateCodeGenerator;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhoushuyi
 * @since 2018/4/29
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        System.out.println("我的验证码生成器");

        return null;
    }
}
