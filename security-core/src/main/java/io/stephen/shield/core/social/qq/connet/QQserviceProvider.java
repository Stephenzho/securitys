package io.stephen.shield.core.social.qq.connet;

import io.stephen.shield.core.social.qq.api.QQ;
import io.stephen.shield.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @author zhoushuyi
 * @since 2018/4/30
 */
public class QQserviceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    private String appId;


    public QQserviceProvider(String appId, String appSecret) {
        super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
        setAppId(appId);
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
