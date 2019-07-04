package com.sea.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sea.upms.po.SysPermission;

import java.util.List;

public interface ISysPermisionService extends IService<SysPermission> {

    /**
     * 根据roleid 获取权限
     * @param roleId
     * @return
     */
    List<SysPermission> getPermissionByRoleId(String roleId);

    /**
     * 获取权限编码
     * @param permissionList
     * @return
     */
    List<String> getPerms(List<SysPermission> permissionList);
}
