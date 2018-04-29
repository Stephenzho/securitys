package io.stephen.shield.core.validate.code;

import io.stephen.shield.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author zhoushuyi
 * @since 2018/4/29
 */
@Configuration
public class ValidateCodeBeanConfig {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 添加默认图形验证码生成器
     * ConditionalOnMissingBean注解在容器中没找到name的bean时才会加载此bean
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);

        if (logger.isDebugEnabled()) {
            logger.debug("加载默认图形验证码生成器");
        }


        return imageCodeGenerator;
    }
}
