package com.hstc.yak;

import com.hstc.yak.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserProviderApplicationTests {

    @Autowired
    private AdminService adminService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void AdminLoginTest(){
	    String username="admin";
	    String password="123456";
        String returnUsername = adminService.Login(username, password);
        System.out.println(returnUsername);
    }

}
