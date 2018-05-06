package io.stephen.shield.core.social.support;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;


/**
 * @author zhoushuyi
 * @since 2018/4/30
 */
public class ShieldSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;
    private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;


    public ShieldSpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        if (socialAuthenticationFilterPostProcessor != null) {
            socialAuthenticationFilterPostProcessor.process(filter);
        }
        return (T) filter;
    }


    public SocialAuthenticationFilterPostProcessor getSocialAuthenticationFilterPostProcessor() {
        return socialAuthenticationFilterPostProcessor;
    }

    public void setSocialAuthenticationFilterPostProcessor(SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor) {
        this.socialAuthenticationFilterPostProcessor = socialAuthenticationFilterPostProcessor;
    }
}
