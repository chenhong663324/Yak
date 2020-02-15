package com.hstc.yak.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.hstc.yak.entity.Landlord;
import com.hstc.yak.entity.Renter;
import com.hstc.yak.service.LandlordService;
import com.hstc.yak.service.RenterService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hp on 2019/10/9.
 */
@CrossOrigin
@RestController
public class RenterController {

    @Reference(version = "1.0.0")
    private RenterService renterService;
    @Reference(version = "1.0.0")
    private LandlordService landlordService;

    /**
     *   注册之后返回对象
     *   注册：判断是否存在这个这个用户（电话）
     *          存在，返回空对象
     *          不存在，返回对象，直接进入页面
     ***/
    @PostMapping("user/renter/regist")
    @ResponseBody
    public JSONObject renterRegist(@RequestBody Renter renter){

        JSONObject result = new JSONObject();

        //获取renter之后传入service,拿到注册的对象
        try {
            Renter renterRegist = renterService.renterRegist(renter);
            if (renterRegist!=null){
                result.put("rid", renterRegist.getId());
                result.put("rname", renterRegist.getName());
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
    @PostMapping("user/renter/login")
    @ResponseBody
    public JSONObject renterLogin(@RequestBody Renter renter){

        System.out.println("进入renterLoginController");
        System.out.println(renter);
        JSONObject result = new JSONObject();
        try {
            Renter renterLogin = renterService.renterLogin(renter.getPhone(), renter.getPassword());
            System.out.println(renterLogin);
            if (renterLogin!=null){
                result.put("id", renterLogin.getId());
                result.put("name", renterLogin.getName());
                return result;
            }else
                result.put("id",null);
        }catch (NullPointerException e){
            System.out.println("空指针异常");
        }
        result.put("id",null);
       return result;
    }


    /**
     根据id查找个人信息

     修改密码的功能(传入原密码，id)

     修改绑定手机号码

     **/

    //总的查找用户，租客房东一起查
    @PostMapping("user/selectById")
    @ResponseBody
    public JSONObject selectById(@RequestBody Renter renter1){
        System.out.println("进入selectById");
        JSONObject result = new JSONObject();

        try{
            Renter renter = renterService.selectById(renter1.getId());
            System.out.println(renter);
            if (renter!=null){
                result.put("phone",renter.getPhone());
                result.put("name",renter.getName());
                result.put("gender",renter.getGender());
                return result;
            }else{
                Landlord landlord = landlordService.selectById(renter1.getId());
                System.out.println(landlord);
                if (landlord!=null){
                    result.put("phone",landlord.getPhone());
                    result.put("name",landlord.getName());
                    result.put("gender",landlord.getGender());
                    return result;
                }
            }
        }catch (NullPointerException e){
            System.out.println("空指针异常");
        }
        return null;
    }

    @PostMapping("user/updatePass")
    @ResponseBody
    public JSONObject updatePass(@RequestBody Renter renter,@RequestParam(value = "newPass") String newPass){
        System.out.println("进入updatePass");
        JSONObject result = new JSONObject();

        Landlord landlord = new Landlord();
        landlord.setId(renter.getId());
        landlord.setGender(renter.getGender());
        landlord.setName(renter.getName());
        landlord.setPassword(renter.getPassword());
        landlord.setPhone(renter.getPhone());

        try{
            int i = renterService.updatePass(renter, newPass);
            if (i!=0){
                result.put("i",1);
                return result;
            }else{
                int j = landlordService.updatePass(landlord, newPass);
                if (i!=0){
                    result.put("i",1);
                    return result;
                }
            }
        }catch (NullPointerException e){
            System.out.println("空指针异常");
        }


        return null;
    }


}
