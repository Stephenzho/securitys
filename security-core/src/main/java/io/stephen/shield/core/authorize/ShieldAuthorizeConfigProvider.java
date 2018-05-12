package io.stephen.shield.core.authorize;

import io.stephen.shield.core.properties.SecurityConstants;
import io.stephen.shield.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 核心模块的授权配置提供器，安全模块涉及的url的授权配置在这里。
 *
 * @author zhoushuyi
 * @since 2018/5/12
 */
@Component
@Order(Integer.MIN_VALUE)
public class ShieldAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Autowired
    private SecurityProperties securityProperties;


    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        String[] urls ;

        String signUpUrl = securityProperties.getBrowser().getSignUpUrl();
        if (StringUtils.isNotEmpty(signUpUrl)){
            urls = new String[]{SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE,
                SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_OPENID,
                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                securityProperties.getBrowser().getSignInPage(),
                securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
                signUpUrl
            };
        }else{
            urls = new String[]{
                SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE,
                SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_OPENID,
                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                securityProperties.getBrowser().getSignInPage(),
                securityProperties.getBrowser().getSession().getSessionInvalidUrl()
            };
        }



        config.antMatchers(urls)
                .permitAll();

        if (StringUtils.isNotBlank(securityProperties.getBrowser().getSignOutUrl())) {
            config.antMatchers(securityProperties.getBrowser().getSignOutUrl()).permitAll();
        }
        return false;
    }
}
