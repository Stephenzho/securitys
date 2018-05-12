package io.stephen.shield.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.stephen.shield.core.properties.LoginResponseType;
import io.stephen.shield.core.properties.SecurityProperties;
import io.stephen.shield.core.support.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhoushuyi
 * @since 2018/4/23
 */
@Component("shieldAuthenticationFailureHandler")
public class ShieldAuthenticationFailureHandler  extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.info("登陆失败");

        if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getSignInPage())){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
        }else{
            super.onAuthenticationFailure(request,response,exception);      // 走父类处理方法
        }

    }
}
