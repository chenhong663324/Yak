package com.hstc.yak.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hstc.yak.entity.Landlord;
import com.hstc.yak.mapper.LandlordMapper;
import com.hstc.yak.service.LandlordService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by hp on 2019/10/9.
 */
@Service(version = "1.0.0",interfaceClass = LandlordService.class)
@org.springframework.stereotype.Service
public class LandlordServiceImpl implements LandlordService {

    @Autowired
    private LandlordMapper landlordMapper;
    /**
     *   注册之后返回对象
     *   注册：判断是否存在这个这个用户（电话）
     *          存在，返回空对象
     *          不存在，返回对象，直接进入页面
     ***/
    @Override
    public Landlord landlordRegist(Landlord landlord) {

        if (landlord!=null){
            int i = landlordMapper.insert(landlord);
            if (i==1){
                return landlord;
            }
        }
        return null;
    }

    /**
     *   登录之后返回对象
     *   登录：判断是否存在这个这个用户（电话）
     *          不存在，返回空对象
     *          存在，返回对象，直接进入页面
     ***/

    @Override
    public Landlord landlordLogin(String phone, String password) {
        if (phone!=null&&password!=null){
            QueryWrapper<Landlord> qw = new QueryWrapper<>();
            qw.eq("phone",phone).eq("password",password);
            Landlord landlord = landlordMapper.selectOne(qw);
            if (landlord!=null){
                return landlord;
            }
        }
        return null;
    }

    /**
     根据id查找个人信息

     修改密码的功能(传入原密码，id)

     修改绑定手机号码

     **/

    @Override
    public Landlord selectById(Long id) {
        QueryWrapper<Landlord> qw = new QueryWrapper<>();
        qw.eq("id",id);

        if (id!=null){
            Landlord landlord = landlordMapper.selectOne(qw);
            if (landlord!=null){
                return landlord;
            }
        }
        return null;
    }

    @Override
    public int updatePass(Landlord landlord,String newPass) {
        /**
         *  获取原密码，做比较
         *  获取id
         *  修改密码
         */
        //查找原先的记录
        Landlord oldlandlord = landlordMapper.selectById(landlord.getId());
        //原密码与传输进来的密码作比较
        if (landlord.getPassword()==oldlandlord.getPassword()) {
            //比较成功，赋值新密码
            landlord.setPassword(newPass);
            //更新密码
            int i = landlordMapper.updateById(landlord);
            if (i == 1) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public int updatePhone(Long id,String newPhone) {
        if (id!=null&&newPhone!=null){
            //在数据查找，这个手机号码是否已经有人注册过了
            QueryWrapper<Landlord> qw = new QueryWrapper<>();
            qw.eq("phone",newPhone);
            List<Landlord> landlords = landlordMapper.selectList(qw);
            if (landlords==null){
                Landlord landlord = landlordMapper.selectById(id);
                landlord.setPhone(newPhone);
                landlordMapper.updateById(landlord);
                return 1;
            }
        }
        return 0;
    }
}
