package io.stephen.shield.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
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
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 密码加密器
     */
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
        logger.info(username+" 获取用户信息");

        /*
          返回的是查询出来的用户信息，此信息的password会与请求中的密码比对。此时库中密码为1111
         */
        return buildUser(username);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        logger.info(userId+" 社交登陆用户");

        return buildUser(userId);
    }

    private SocialUserDetails buildUser(String userId) {

        // 对密码加密
        String password = passwordEncoder.encode("1111");

        return new SocialUser(userId,password,
                true,true,true,true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
    }
}
