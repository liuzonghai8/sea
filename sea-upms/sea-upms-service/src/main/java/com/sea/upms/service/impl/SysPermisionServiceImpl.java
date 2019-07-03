package com.sea.upms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sea.upms.mapper.SysPermissionMapper;
import com.sea.upms.po.SysPermission;
import com.sea.upms.service.ISysPermisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermisionServiceImpl extends ServiceImpl<SysPermissionMapper,SysPermission> implements ISysPermisionService {

  @Autowired
  private SysPermissionMapper sysPermissionMapper;
    @Override
    public List<SysPermission> getPermissionByRoleId(String roleId) {

        return  sysPermissionMapper.selectPermissionByRoleid(roleId);
    }
}
