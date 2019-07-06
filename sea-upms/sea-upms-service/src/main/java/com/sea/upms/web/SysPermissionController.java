package com.sea.upms.web;

import com.sea.common.bean.ResultVO;
import com.sea.upms.po.SysPermission;
import com.sea.upms.service.ISysPermissionService;
import com.sea.upms.utils.BuildTree;
import com.sea.upms.utils.Tree;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
        List<Tree<SysPermission>> trees = new ArrayList<Tree<SysPermission>>();
        for (SysPermission sp:sysPermissions){
            Tree<SysPermission> tree = new Tree<SysPermission>();
            tree.setParentId(sp.getParentId());
            tree.setId(sp.getId());
            tree.setChildren(sp.isLeaf());
            log.info("------for 循环里面-----{}",tree);
            trees.add(tree);

        }
        log.info("------for 循环外-----{}",trees);
        return new ResultVO<>(BuildTree.build(trees));
    }
}
