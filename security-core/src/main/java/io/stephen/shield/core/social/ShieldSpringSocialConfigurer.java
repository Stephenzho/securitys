package io.stephen.shield.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;


/**
 * @author zhoushuyi
 * @since 2018/4/30
 */
public class ShieldSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;

    public ShieldSpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        return (T) filter;
    }
}
