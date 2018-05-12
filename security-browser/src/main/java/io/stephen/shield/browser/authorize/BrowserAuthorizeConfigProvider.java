package io.stephen.shield.browser.authorize;

import io.stephen.shield.core.authorize.AuthorizeConfigProvider;
import io.stephen.shield.core.properties.SecurityConstants;
import io.stephen.shield.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * 浏览器环境默认的授权配置，对常见的静态资源，如js,css，图片等不验证身份
 *
 * @author zhoushuyi
 * @since 2018/5/12
 */
@Component
@Order(Integer.MIN_VALUE)
public class BrowserAuthorizeConfigProvider implements AuthorizeConfigProvider {



    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(HttpMethod.GET,
                SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM,  //表单登陆请求url


                "/**/*.js",
                "/**/*.css",
                "/**/*.jpg",
                "/**/*.png",
                "/**/*.gif").permitAll();
        return false;
    }
}
