package com.sea.upms.web;

import com.sea.common.bean.Result;
import com.sea.upms.pojo.SysUser;
import com.sea.upms.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("sys/user")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @GetMapping("list")
    public Result<List<SysUser>> queryList(){
        log.info("hell");
        List<SysUser> list = sysUserService.list();
       log.info("queryList {}", sysUserService.list());
        return new Result<>(list);
    }
    @GetMapping("test")
    public String getTest(){
        return "test";
    }
}
