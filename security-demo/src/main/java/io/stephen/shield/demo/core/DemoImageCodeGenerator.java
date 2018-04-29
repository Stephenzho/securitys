package io.stephen.shield.demo.core;

import io.stephen.shield.core.validate.code.ImageCode;
import io.stephen.shield.core.validate.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhoushuyi
 * @since 2018/4/29
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generate(HttpServletRequest request) {
        System.out.println("我的验证码生成器");

        return null;
    }
}
