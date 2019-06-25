package com.sea.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sea.upms.pojo.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SysUserMapper extends BaseMapper<SysUser> {
    @Select("select * from sys_user where username= #{username}")
    SysUser selectByName(@Param("username") String username);
}
