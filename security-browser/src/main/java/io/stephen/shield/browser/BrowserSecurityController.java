package io.stephen.shield.browser;

import io.stephen.shield.browser.support.SimpleResponse;
import io.stephen.shield.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhoushuyi
 * @since 2018/4/22
 */
@RestController
public class BrowserSecurityController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 缓存请求类，会把之前请求缓存到里面
     */
    private RequestCache requestCache = new HttpSessionRequestCache();

    /**
     * 重定向工具类
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    @Autowired
    private SecurityProperties securityProperties;


    /**
     * 当需要身份认证时跳转到这里
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/authentication/required")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 拿到引发到此的请求
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        logger.info("进来了");
        logger.info(savedRequest.toString());

        if (null != savedRequest) {
            String redirectUrl = savedRequest.getRedirectUrl();

            logger.info("引发跳转的请求是：" + redirectUrl);

            // 判断引发跳转的请求是否为html结尾
            if (StringUtils.endsWithIgnoreCase(redirectUrl, ".html")) {
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            }

        }

        return new SimpleResponse("访问的服务需要身份认证，请引导用户到登陆页");
    }




}
