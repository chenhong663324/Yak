package com.hstc.yak.service;

import com.hstc.yak.entity.Landlord;

/**
 * Created by hp on 2019/10/9.
 */
public interface LandlordService {

    /**
     *   注册之后返回对象
     *   注册：判断是否存在这个这个用户（电话）
     *          存在，返回空对象
     *          不存在，返回对象，直接进入页面
     ***/
    public Landlord landlordRegist(Landlord landlord);

    //登录后返回对象
    public Landlord landlordLogin(String phone, String password);

    //根据id查找个人信息
    public Landlord selectById(Long id);

    //修改密码的功能
    public int updatePass(Landlord landlord,String newPass);

    //修改绑定手机号码
    public int updatePhone(Long id,String newPhone);
}
