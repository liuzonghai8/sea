package com.sea.upms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sea.upms.mapper.SysPermissionMapper;
import com.sea.upms.po.SysPermission;
import com.sea.upms.service.ISysPermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper,SysPermission> implements ISysPermissionService {

  @Autowired
  private SysPermissionMapper sysPermissionMapper;
    @Override
    public List<SysPermission> getPermissionByRoleId(String roleId) {

        return  sysPermissionMapper.selectPermissionByRoleid(roleId);
    }

  @Override
  public List<String> getPerms(List<SysPermission> permissionList) {

    List<String> collect = permissionList
                            .stream()
                            .filter(permission -> StringUtils.isNotEmpty(permission.getPerms()))
                            .map(SysPermission::getPerms)
                            .collect(Collectors.toList());

    return collect;
  }
}
