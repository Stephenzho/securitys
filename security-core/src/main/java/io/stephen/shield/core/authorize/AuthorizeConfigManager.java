/**
 * 
 */
package io.stephen.shield.core.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * 授权信息管理器
 * 
 * 用于收集系统中所有 AuthorizeConfigProvider 并加载其配置
 * 
 * @author zhoushuyi
 * @since 2018/5/12
 *
 */
public interface AuthorizeConfigManager {
	
	/**
	 * @param config
	 */
	void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);

}
