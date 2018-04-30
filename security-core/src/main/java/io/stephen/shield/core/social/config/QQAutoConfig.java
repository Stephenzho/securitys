package io.stephen.shield.core.social.config;

import io.stephen.shield.core.properties.QQProperties;
import io.stephen.shield.core.properties.SecurityProperties;
import io.stephen.shield.core.social.connet.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author zhoushuyi
 * @since 2018/4/30
 */
@Configuration
@ConditionalOnProperty(prefix = "shield.social.qq", name = "app-id")    // appid配置时此配置才生效
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qq = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qq.getProviderId(), qq.getAppId(), qq.getAppSecret());
    }
}
