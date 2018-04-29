package io.stephen.shield.core.properties;

/**
 * 验证码配置
 *
 * @author zhoushuyi
 * @since 2018/4/29
 */
public class ValidateCodeProperties {

    /**
     * 图片验证码配置
     */
    private ImageCodeProperties image = new ImageCodeProperties();
    /**
     * 短信验证码配置
     */
    private SmsCodeProperties sms = new SmsCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }

    public SmsCodeProperties getSms() {
        return sms;
    }

    public void setSms(SmsCodeProperties sms) {
        this.sms = sms;
    }
}
