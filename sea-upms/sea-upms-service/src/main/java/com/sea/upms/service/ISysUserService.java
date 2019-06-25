package com.sea.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sea.upms.po.SysUser;

public interface ISysUserService extends IService<SysUser> {

    SysUser getUserByNameAndPassword(String username, String password);
}
