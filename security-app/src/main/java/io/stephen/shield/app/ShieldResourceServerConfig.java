package io.stephen.shield.app;

import io.stephen.shield.app.authentication.openid.OpenIdAuthenticationSecurityConfig;
import io.stephen.shield.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import io.stephen.shield.core.properties.SecurityConstants;
import io.stephen.shield.core.properties.SecurityProperties;
import io.stephen.shield.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author zhoushuyi
 * @since 2018/5/2
 */
@Configuration
@EnableResourceServer
public class ShieldResourceServerConfig  extends ResourceServerConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    protected AuthenticationSuccessHandler shieldAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler shieldAuthenticationFailureHandler;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig ;

    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;

    @Autowired
    public PersistentTokenRepository persistentTokenRepository;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
                .successHandler(shieldAuthenticationSuccessHandler)
                .failureHandler(shieldAuthenticationFailureHandler);

        http.apply(validateCodeSecurityConfig).and()
                .apply(openIdAuthenticationSecurityConfig).and()

                .apply(smsCodeAuthenticationSecurityConfig).and()

                .apply(springSocialConfigurer).and()

                .authorizeRequests()
                .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE,
                        SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM,
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                        securityProperties.getBrowser().getSignInPage(),
                        securityProperties.getBrowser().getSignUpUrl(),
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
                        securityProperties.getBrowser().getSignOutUrl(),
                        "/user/regist").permitAll()    // 当访问matchers页面时允许通过。
                .anyRequest()           // 所有的请求
                .authenticated()        // 都需要验证
                .and()
                .csrf().disable();      //关闭csrf

    }

}
