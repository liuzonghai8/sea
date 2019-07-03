package com.sea.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sea.upms.po.SysPermission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    @Select("SELECT p.* FROM sys_permission p LEFT JOIN sys_role_permission r ON p.id = r.permission_id WHERE p.del_flag = 0 AND r.role_id =#{roleid} ORDER BY p.sort_no DESC")
    List<SysPermission> selectPermissionByRoleid(@Param("roleid") String roleId);
}
