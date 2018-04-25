package io.stephen.shield.core.validate.code;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图片验证码过滤器
 *
 * @author zhoushuyi
 * @since 2018/4/25
 */
public class ValidateCodeFilter extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        /*
            当为登陆请求时 判断图片验证码
         */
        if (StringUtils.equals("/authentication/form",request.getRequestURI()) &&
                StringUtils.equalsIgnoreCase(request.getMethod(),"post")) {


            try {

                validate(new ServletWebRequest(request));

            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }


        }

        filterChain.doFilter(request,response);
    }

    /**
     * 校验验证码
     * @param request
     */
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {

        /*
            session中的验证码
         */
        ImageCode imageCode = (ImageCode) sessionStrategy.getAttribute(request,ValidateCodeController.SESSION_KEY);

        /*
          请求传过来的验证码
         */
        String code = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

        if (StringUtils.isBlank(code)) {
            throw new ValidateCodeException("验证码不能为空");
        }

        if (null == imageCode) {
            throw new ValidateCodeException("验证码不存在");
        }

        if(imageCode.isExpried()){
            sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.equals(imageCode.getCode(), code)) {
            throw new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
    }


    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }
}
