package com.hstc.yak.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.hstc.yak.entity.Admin;
import com.hstc.yak.service.AdminService;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hp on 2019/9/29.
 */
@CrossOrigin
@RestController
public class AdminController {

//    @Reference(version = "1.0.0")
//    private AdminService adminService;
//
//    @GetMapping("user/admin")
//    public String admin() {
//        return "admin";
//    }
//
//    @PostMapping("user/admin/login")
//    @ResponseBody
//    public String adminLogin(@RequestBody Admin admin) {
//        System.out.println("zhixing_____________________________________________________");
//        System.out.println(admin);
//        String name = adminService.Login(admin.getUsername(),admin.getPassword());
//        System.out.println(name);
//        JSONObject result = new JSONObject();
//        result.put("name",name);
//        return result.toJSONString();
//    }

}
