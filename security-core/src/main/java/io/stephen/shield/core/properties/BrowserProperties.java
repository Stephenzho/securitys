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


	private LoginType loginType = LoginType.JSON;




	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
}
