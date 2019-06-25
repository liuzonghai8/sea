package com.sea.upms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sea.upms.mapper.SysUserMapper;
import com.sea.upms.pojo.SysUser;
import com.sea.upms.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getUserByName(String username, String password) {
        SysUser user = sysUserMapper.selectByName(username);
        
       return  user;
    }
}
