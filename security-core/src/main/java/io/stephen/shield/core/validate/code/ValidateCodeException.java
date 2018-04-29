package io.stephen.shield.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author zhoushuyi
 * @since 2018/4/25
 */
public class ValidateCodeException extends AuthenticationException {


    private static final long serialVersionUID = 6806300745969881006L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
