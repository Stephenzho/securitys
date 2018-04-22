package io.stephen.shield.browser;

import io.stephen.shield.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author zhoushuyi
 * @since 2018/4/22
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

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

        http.formLogin()
                .loginPage("/authentication/required")  // 指定登陆页面
                .loginProcessingUrl("/authentication/form")  //指定登陆请求接口，与html中登陆对应
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/required",
                        securityProperties.getBrowser().getLoginPage()).permitAll()    // 当访问matchers页面时允许通过。
                .anyRequest()           // 所有的请求
                .authenticated()        // 都需要验证
                .and()
                .csrf().disable();      //关闭csrf

    }
}
