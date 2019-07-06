package com.sea.upms.utils;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class Tree<T> {

    private  String id;
    private  String parentId;
    /**
     * 节点属性
     */
    protected List<Map<String, Object>> attributes;
    /**
     * 是否有父节点
     */
    private boolean isParent = false;
    /**
     * 是否有子节点
     */
    private boolean isChildren = false;

    private List<Tree<T>> children = new ArrayList<Tree<T>>();

}
