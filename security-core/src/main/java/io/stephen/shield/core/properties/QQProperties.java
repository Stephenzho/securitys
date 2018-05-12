package io.stephen.shield.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author zhoushuyi
 * @since 2018/4/30
 */
public class QQProperties extends SocialProperties {

    /**
     * 第三方id，用来决定发起第三方登录的url，默认是 qq。
     */
    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
