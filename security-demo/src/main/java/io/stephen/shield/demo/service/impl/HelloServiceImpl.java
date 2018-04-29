/**
 * 
 */
package io.stephen.shield.demo.service.impl;

import io.stephen.shield.demo.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author zhoushuyi
 *
 */
@Service
public class HelloServiceImpl implements HelloService {

	@Override
	public String greeting(String name) {
		System.out.println("greeting");
		return "hello "+name;
	}

}
