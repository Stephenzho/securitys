package io.stephen.shield.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 图形验证码生成器接口，用户可实现此接口以覆盖默认实现。
 * @author zhoushuyi
 * @since 2018/4/29
 */
public interface ValidateCodeGenerator {

    /**
     * 生成校验码
     * @param request
     * @return
     */
    ValidateCode generate(ServletWebRequest request);
}
