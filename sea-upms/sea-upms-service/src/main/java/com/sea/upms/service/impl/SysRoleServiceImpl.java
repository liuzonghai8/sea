package com.sea.upms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sea.upms.mapper.SysRoleMapper;
import com.sea.upms.po.SysRole;
import com.sea.upms.service.ISysRoleService;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper,SysRole> implements ISysRoleService {
}
