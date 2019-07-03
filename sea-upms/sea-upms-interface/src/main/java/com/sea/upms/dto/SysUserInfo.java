package com.sea.upms.dto;

import com.sea.upms.po.SysUser;
import lombok.Data;
import java.io.Serializable;

@Data
public class SysUserInfo implements Serializable {
    /**
     * 用户基本信息
     */
    private SysUser sysUser;
    /**
     * 权限标识集合
     */
    private String[] permissions;

    /**
     * 角色集合
     */
    private String[] roles;
}
