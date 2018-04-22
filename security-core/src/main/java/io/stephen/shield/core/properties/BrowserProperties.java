/**
 * 
 */
package io.stephen.shield.core.properties;

/**
 * 浏览器环境配置项
 * 
 * @author zhailiang
 *
 */
public class BrowserProperties {

	private String loginPage = "/signIn.html";


	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
}
