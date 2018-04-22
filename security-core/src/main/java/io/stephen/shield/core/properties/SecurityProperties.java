/**
 * 
 */
package io.stephen.shield.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhailiang
 *
 */
@ConfigurationProperties(prefix = "shield")
public class SecurityProperties {
	
	/**
	 * 浏览器环境配置
	 */
	public BrowserProperties browser = new BrowserProperties();


	public BrowserProperties getBrowser() {
		return browser;
	}


    public void setBrowser(BrowserProperties browser) {
	    this.browser = browser;
    }

}
