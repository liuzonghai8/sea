package com.sea.upms.bo;

import com.sea.upms.po.SysPermission;
import com.sea.upms.po.SysRole;
import com.sea.upms.po.SysUser;
import lombok.Data;

import java.util.List;

@Data
public class SysUserBO {
    SysUser sysUser;
    List<SysRole> roles;
    List<SysPermission> meuns;
}
