package com.sea.upms.utils;

import com.sea.upms.dto.MenuTree;
import com.sea.upms.dto.TreeNode;
import com.sea.upms.po.SysPermission;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TreeUtil {

    /**
     * 两层循环实现建树
     *
     * @param treeNodes 传入的树节点列表
     * @return
     */
    public static <T extends TreeNode> List<T> bulid(List<T> treeNodes, Object root) {
        log.info("-------两层循环实现建树---Tree - build-------------");

        List<T> trees = new ArrayList<T>();
        for (T treeNode : treeNodes) {
            log.info("------treeNode-parenid {}------",treeNode);
            if (root.equals(treeNode.getParentId())) {
                log.info("----root-----treeNode-parenid == root,{}",treeNode);
                trees.add(treeNode);
            }
            for (T it : treeNodes) {
                if (it.getParentId().equals(treeNode.getId())) {
                    log.info("-----it == treeNode.getId()",it);
                    if (treeNode.getChildren() == null) {
                        //log.info("treeNode.getChildren()"+treeNode.getChildren().toString());
                        treeNode.setChildren(new ArrayList<TreeNode>());
                    }
                    treeNode.add(it);
                    log.info("treeNode  {}",treeNode);
                }
            }
        }
        log.info("buid  -trees = "+ trees);
        return trees;
    }

    /**
     * 构建菜单树
     * @param menus
     * @param root
     * @return
     */
    public static List<MenuTree> builTree(List<SysPermission> menus, String root){
        log.info("--------构建菜单树 TreeUtil -builTree,{},{}-------",menus,root);
        List<MenuTree> trees = new ArrayList<MenuTree>();
        MenuTree node;
        for (SysPermission menu : menus) {
            //log.info("parentId:  "+ menu.getParentId());
            node = new MenuTree();
            node.setId(menu.getId());
            node.setParentId(menu.getParentId());
            node.setName(menu.getName());
            node.setMenuType(menu.getMenuType());
            node.setDelFlag(menu.getDelFlag());
            node.setSortNo(menu.getSortNo());
            node.setComponent(menu.getComponent());
            node.setUrl(menu.getUrl());
            node.setIcon(menu.getIcon());
            node.setRedirect(menu.getRedirect());
            node.setPermsType(menu.getPermsType());
            node.setDescription(menu.getDescription());
            log.info("当前node："+node.toString());
            trees.add(node);
        }
        log.info("----------trees is :{}----------",trees);
        return TreeUtil.bulid(trees,root);
    }
}
