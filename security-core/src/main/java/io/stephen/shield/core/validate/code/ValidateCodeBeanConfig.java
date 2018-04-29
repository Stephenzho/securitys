package io.stephen.shield.core.validate.code;

import io.stephen.shield.core.properties.SecurityProperties;
import io.stephen.shield.core.validate.code.image.ImageCodeGenerator;
import io.stephen.shield.core.validate.code.sms.DefaultSmsCodeSender;
import io.stephen.shield.core.validate.code.sms.SmsCodeSender;
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

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 图片验证码图片生成器
     *
     * 若容器中没有name=imageValidateCodeGenerator的bean则加载此bean
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    /**
     * 短信验证码发送器
     *
     * 若容器中没有实现SmsCodeSender的bean则加载此bean
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }
}
