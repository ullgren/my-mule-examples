package com.ullgren.pontus.demo.beans;

import org.springframework.stereotype.Service;

@Service("myFirstGreeterService")
public class GreeterService {
	public String sayHello(String name) {
		return "Hello " + name;
	}
}
