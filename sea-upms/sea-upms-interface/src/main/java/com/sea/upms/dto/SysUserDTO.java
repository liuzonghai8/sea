package com.sea.upms.dto;

import com.sea.upms.po.SysUser;

import java.util.List;

public class SysUserDTO extends SysUser {

    private List<String> roles;

    private String departId;

    /**
     * 新密码
     */
    private String newpassword;
}
