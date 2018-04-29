package io.stephen.shield.browser;

import io.stephen.shield.core.authentication.FormAuthenticationConfig;
import io.stephen.shield.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import io.stephen.shield.core.properties.SecurityConstants;
import io.stephen.shield.core.properties.SecurityProperties;
import io.stephen.shield.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author zhoushuyi
 * @since 2018/4/22
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;

    /**
     * 用户密码加密类，处理用户密码加解密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 记住我功能配置
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
    //    jdbcTokenRepository.setCreateTableOnStartup(true);        // 第一次启动时创建表。再次启动时注掉
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        formAuthenticationConfig.configure(http);

        http.apply(validateCodeSecurityConfig)
                    .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                    .and()
                .rememberMe()                                             //配置记住我功能
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                    .and()
                .authorizeRequests()
                    .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                            SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE,
                            SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM,
                            SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                            securityProperties.getBrowser().getLoginPage()).permitAll()    // 当访问matchers页面时允许通过。
                    .anyRequest()           // 所有的请求
                    .authenticated()        // 都需要验证
                    .and()
                .csrf().disable();      //关闭csrf

    }
}
