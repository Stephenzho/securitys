package io.stephen.shield.core.support;

/**
 * 相应包装类
 * @author zhoushuyi
 * @since 2018/4/22
 */
public class SimpleResponse {

    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
