package com.hstc.yak.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.hstc.yak.entity.Landlord;
import com.hstc.yak.service.LandlordService;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hp on 2019/10/9.
 */
@CrossOrigin
@RestController
public class LandlordController {

    @Reference(version = "1.0.0")
    private LandlordService landlordService;

    /**
     *   注册之后返回对象
     *   注册：判断是否存在这个这个用户（电话）
     *          存在，返回空对象
     *          不存在，返回对象，直接进入页面
     ***/
    @PostMapping("user/landlord/regist")
    @ResponseBody
    public JSONObject landlordRegist(@RequestBody Landlord landlord){
        JSONObject result = new JSONObject();
        //获取renter之后传入service,拿到注册的对象
        try {
            Landlord landlordRegist = landlordService.landlordRegist(landlord);
            if (landlordRegist!=null){
                result.put("lid", landlordRegist.getId());
                result.put("lname", landlordRegist.getName());
            }
        }catch (NullPointerException e){
            System.out.println("空指针异常");
        }
        return result;
    }



    /**
     *   登录之后返回对象
     *   登录：判断是否存在这个这个用户（电话）
     *          不存在，返回空对象
     *          存在，返回对象，直接进入页面
     ***/
    @PostMapping("user/landlord/login")
    @ResponseBody
    public JSONObject landlordLogin(@RequestBody Landlord landlord){
        JSONObject result = new JSONObject();
        try {
            Landlord landlordLogin = landlordService.landlordLogin(landlord.getPhone(), landlord.getPassword());
            System.out.println(landlordLogin);
            if (landlordLogin!=null){
                result.put("id", landlordLogin.getId());
                result.put("name", landlordLogin.getName());
                return result;
            }else{
                result.put("id",null);
            }
        }catch (NullPointerException e){
            System.out.println("空指针异常");
        }
        return result;
    }

    @PostMapping("user/landlord/selectById")
    public JSONObject selectById(@RequestBody Landlord landlord){
        JSONObject result = new JSONObject();
        System.out.println("前端传送过来的房东id"+landlord);
        try {
            Landlord landlord1 = landlordService.selectById(landlord.getId());
            System.out.println("获取到的房东的信息"+landlord1);
            if (landlord1!=null){
                result.put("lname", landlord1.getName());
                return result;
            }else{
                result.put("lname",null);
            }
        }catch (NullPointerException e){
            System.out.println("空指针异常");
        }
        return result;
    }
}
