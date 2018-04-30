package io.stephen.shield.core.properties;

/**
 * @author zhoushuyi
 * @since 2018/4/30
 */
public class SocialProperties {
    private QQProperties qq = new QQProperties();

    private String filterProcessesUrl = "/auth";


    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }
}
