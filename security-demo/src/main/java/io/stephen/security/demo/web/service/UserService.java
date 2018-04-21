package io.stephen.security.demo.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author 10447
 * @since 2017/11/6
 */
@Service
public class UserService implements UserDetailsService, SocialUserDetailsService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public PasswordEncoder passwordEncoder;

    /**
     * 根据username查询user
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // UserDetails 四种状态其一为false则无法登陆
        return buildUser(username);
    }


    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        return (SocialUserDetails) buildUser(userId);
    }

    public UserDetails buildUser(String id) {
        return  new User(id, passwordEncoder.encode("111111"),
                true,true,true,true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
