package io.stephen.shield.core.social.view;

import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 绑定解绑结果视图
 * @author zhoushuyi
 * @since 2018/5/1
 */
public class ShieldConnectView extends AbstractView {


    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setContentType("text/html;charset=UTF-8");
        if (model.get("connections") == null) {
            response.getWriter().write("<h3>解绑成功</h3>");
        } else {
            response.getWriter().write("<h3>绑定成功</h3>");
        }
    }
}
