package io.stephen.shield.browser;

import io.stephen.shield.core.properties.SecurityProperties;
import io.stephen.shield.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author zhoushuyi
 * @since 2018/4/22
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler shieldAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler shieldAuthenticationFailureHandler;

    /**
     * 用户密码加密类，处理用户密码加解密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(shieldAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)        // 将图片验证码过滤器添加到账户密码过滤器前
                .formLogin()
                .loginPage("/authentication/required")  // 指定登陆页面
                .loginProcessingUrl("/authentication/form")  //指定登陆请求接口，与html中登陆对应
                .successHandler(shieldAuthenticationSuccessHandler)     //指定登陆成功处理
                .failureHandler(shieldAuthenticationFailureHandler)     //指定登陆失败处理
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/required",
                        "/code/image",
                        securityProperties.getBrowser().getLoginPage()).permitAll()    // 当访问matchers页面时允许通过。
                .anyRequest()           // 所有的请求
                .authenticated()        // 都需要验证
                .and()
                .csrf().disable();      //关闭csrf

    }
}
