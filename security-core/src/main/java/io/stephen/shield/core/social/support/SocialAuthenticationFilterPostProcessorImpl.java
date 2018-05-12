package io.stephen.shield.core.social.support;

import io.stephen.shield.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author zhoushuyi
 * @since 2018/5/12
 */
@Component
public class SocialAuthenticationFilterPostProcessorImpl implements SocialAuthenticationFilterPostProcessor {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;


    @Override
    public void process(SocialAuthenticationFilter filter) {

        String signUpUrl = securityProperties.getBrowser().getSignUpUrl();

        if (StringUtils.isEmpty(signUpUrl)) {
            filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        }

    }
}
