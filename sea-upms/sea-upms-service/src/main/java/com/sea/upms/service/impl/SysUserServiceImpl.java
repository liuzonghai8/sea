package com.sea.upms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sea.common.enums.ExceptionEnum;
import com.sea.common.exception.SeaException;
import com.sea.upms.mapper.SysUserMapper;
import com.sea.upms.po.SysUser;
import com.sea.upms.service.ISysUserService;

import com.sea.upms.utils.PasswordUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements ISysUserService {


   @Autowired
   private SysUserMapper sysUserMapper;
    @Override
    public SysUser getUserByNameAndPassword(String username, String password) {
        /*
        * 0、根据用户名查询用户
        * 1、判断查询到用户是否为空，空 返回用户不存在
        * 2、不为空，先判断用户是否锁定，锁定返回锁定信息 //放控制层
        * 3、再判断密码是否错误，返回密码错误
        * 4、返回用户*/
        SysUser user = sysUserMapper.selectByName(username);
        if(user==null) throw new SeaException(ExceptionEnum.USER_NOT_EXIST);
        //判断密码
        if(!StringUtils.equals(PasswordUtil.encrypt(username,password,user.getSalt()),user.getPassword()))
            throw new SeaException(ExceptionEnum.USERNAME_OR_PASSWORD_ERROR);

        return user;
    }
}
