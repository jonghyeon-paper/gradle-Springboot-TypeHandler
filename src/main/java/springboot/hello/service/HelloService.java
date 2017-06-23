package springboot.hello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.hello.model.Users;
import springboot.hello.persistence.UserMapper;

@Service
public class HelloService {
	
	@Autowired
	private UserMapper userMapper;
	
	public List<Users> getUserList() {
		return userMapper.selectList(new Users());
	}
	
}
