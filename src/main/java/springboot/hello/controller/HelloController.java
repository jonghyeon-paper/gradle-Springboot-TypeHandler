package springboot.hello.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.hello.service.HelloService;

@RestController
public class HelloController {
	
	@Autowired
	private HelloService helloService;
	
	@RequestMapping("/")
	public String index() {
		return "hello~";
	}
	
	@RequestMapping("/list")
	public List<?> getUserList() {
		return helloService.getUserList();
	}

}
