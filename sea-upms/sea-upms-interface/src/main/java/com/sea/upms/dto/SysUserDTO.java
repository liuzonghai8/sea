package com.sea.upms.dto;

import com.sea.upms.po.SysUser;
import lombok.Data;

import java.util.List;

@Data
public class SysUserDTO extends SysUser {

    private List<String> roles;

    private String departId;

    /**
     * 新密码
     */
    private String newpassword;
}
