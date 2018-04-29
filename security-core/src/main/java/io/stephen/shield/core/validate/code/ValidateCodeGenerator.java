package io.stephen.shield.core.validate.code;

import javax.servlet.http.HttpServletRequest;

/**
 * 图形验证码生成器接口，用户可实现此接口以覆盖默认实现。
 * @author zhoushuyi
 * @since 2018/4/29
 */
public interface ValidateCodeGenerator {

    ImageCode generate(HttpServletRequest request);
}
