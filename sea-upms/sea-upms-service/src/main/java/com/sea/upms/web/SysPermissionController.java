package com.sea.upms.web;

import com.sea.common.bean.ResultVO;
import com.sea.upms.dto.MenuTree;
import com.sea.upms.po.SysPermission;
import com.sea.upms.service.ISysPermissionService;
import com.sea.upms.utils.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("sys/permission")
public class SysPermissionController {

    @Autowired
    private ISysPermissionService sysPermissionService;

    @GetMapping("tree")
    public ResultVO tree(){
        List<SysPermission>  sysPermissions = sysPermissionService.list();
        //final List<MenuTree> menuTrees = TreeUtil.builTree(sysPermissions, "-1");/
        return new ResultVO<>(TreeUtil.builTree(sysPermissions, "-1"));
    }
}
