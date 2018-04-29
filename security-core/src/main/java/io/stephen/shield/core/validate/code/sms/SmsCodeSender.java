/**
 * 
 */
package io.stephen.shield.core.validate.code.sms;

/**
 * @author zhoushuyi
 * @since 2018/4/25
 */
public interface SmsCodeSender {
	
	/**
	 * @param mobile
	 * @param code
	 */
	void send(String mobile, String code);

}
