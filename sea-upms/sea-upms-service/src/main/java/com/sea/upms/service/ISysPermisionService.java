package com.sea.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sea.upms.po.SysPermission;

import java.util.List;

public interface ISysPermisionService extends IService<SysPermission> {

    List<SysPermission> getPermissionByRoleId(String roleId);
}
