package com.hstc.yak.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hstc.yak.entity.Renter;
import com.hstc.yak.mapper.RenterMapper;
import com.hstc.yak.service.RenterService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by hp on 2019/10/9.
 */


@Service(version = "1.0.0",interfaceClass = RenterService.class)
@org.springframework.stereotype.Service
public class RenterServiceImpl implements RenterService {

    @Autowired
    private RenterMapper renterMapper;

    /**
     *   注册之后返回对象
     *   注册：判断是否存在这个这个用户（电话）
     *          存在，返回空对象
     *          不存在，返回对象，直接进入页面
     ***/
    @Override
    public Renter renterRegist(Renter renter) {

        if (renter!=null){
            int i = renterMapper.insert(renter);
            if (i==1){
                return renter;
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
    public Renter renterLogin(String phone, String password) {

        if (phone!=null&&password!=null){
            QueryWrapper<Renter> qw = new QueryWrapper<>();
            qw.eq("phone",phone).eq("password",password);
            Renter renter = renterMapper.selectOne(qw);
            if (renter!=null){
                return renter;
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
    public Renter selectById(Long id) {
        QueryWrapper<Renter> qw = new QueryWrapper<>();
        qw.eq("id",id);

        if (id!=null){
            Renter renter = renterMapper.selectOne(qw);
            if (renter!=null){
                return renter;
            }
        }
        return null;
    }

    @Override
    public int updatePass(Renter renter,String newPass) {
        /**
         *  获取原密码，做比较
         *  获取id
         *  修改密码
         */
        if (renter!=null){
            //查找原先的记录
            Renter oldrenter = renterMapper.selectById(renter.getId());
            //原密码与传输进来的密码作比较
            if (renter.getPassword()==oldrenter.getPassword()){
                //比较成功，赋值新密码
                renter.setPassword(newPass);
                //更新密码
                int i = renterMapper.updateById(renter);
                if (i==1){
                    return i;
                }
            }
        }

        return 0;
    }

    @Override
    public int updatePhone(Long id,String newPhone) {

        if (id!=null&&newPhone!=null){
            //在数据查找，这个手机号码是否已经有人注册过了
            QueryWrapper<Renter> qw = new QueryWrapper<>();
            qw.eq("phone",newPhone);
            List<Renter> renters = renterMapper.selectList(qw);
            if (renters==null){
                Renter renter = renterMapper.selectById(id);
                renter.setPhone(newPhone);
                renterMapper.updateById(renter);
                return 1;
            }

        }
        return 0;
    }
}
