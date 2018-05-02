package io.stephen.shield.core.social;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设置查看用户绑定的第三方账号视图(/connect)。
 * @author zhoushuyi
 * @since 2018/5/1
 */
@Component("connect/status")
public class ShieldConnectionStatusView extends AbstractView {

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,List<Connection<?>>> connect = (Map<String, List<Connection<?>>>) model.get("connectionMap");
        Map<String, Boolean> map = new HashMap<>();
        for (String key : connect.keySet()) {
            map.put(key ,CollectionUtils.isNotEmpty(connect.get(key)));
        }

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(map));
    }
}
