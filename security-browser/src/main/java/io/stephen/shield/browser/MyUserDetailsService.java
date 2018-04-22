package io.stephen.shield.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 处理用户信息获取逻辑
 *
 *  {@link UserDetails} 处理用户校验逻辑
 *
 * @author zhoushuyi
 * @since 2018/4/22
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 登陆时spring自动调用该方法根据username获取用户信息，
     * 需要在此做业务逻辑处理判断用户是否被冻结，可用等。
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 对密码加密
        String password = passwordEncoder.encode("1111");

        logger.info(username+" 用户登陆了,加密后的密码："+password);



        return new User(username,password,
                true,true,true,true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
