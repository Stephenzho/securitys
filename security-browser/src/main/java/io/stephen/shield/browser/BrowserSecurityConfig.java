package io.stephen.shield.browser;

import io.stephen.shield.core.authentication.FormAuthenticationConfig;
import io.stephen.shield.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import io.stephen.shield.core.properties.SecurityConstants;
import io.stephen.shield.core.properties.SecurityProperties;
import io.stephen.shield.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;


/**
 * @author zhoushuyi
 * @since 2018/4/22
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;
    @Autowired
    private LogoutSuccessHandler shieldLogoutSuccessHandler;

    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;

    @Autowired
    public PersistentTokenRepository persistentTokenRepository;



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        formAuthenticationConfig.configure(http);

        http.apply(validateCodeSecurityConfig).and()

                .apply(smsCodeAuthenticationSecurityConfig).and()

                .apply(springSocialConfigurer).and()

                .rememberMe()                                             //配置记住我功能
                    .tokenRepository(persistentTokenRepository)
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                    .and()
                .sessionManagement()
                    .invalidSessionStrategy(invalidSessionStrategy)
                    .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
                    .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
                    .expiredSessionStrategy(sessionInformationExpiredStrategy)
                    .and().and()
                .logout()
                    .logoutUrl("/signOut")
                    .logoutSuccessHandler(shieldLogoutSuccessHandler)
                    .deleteCookies("JSESSIONID").and()

                .authorizeRequests()
                    .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                            SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE,
                            SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM,
                            SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
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
