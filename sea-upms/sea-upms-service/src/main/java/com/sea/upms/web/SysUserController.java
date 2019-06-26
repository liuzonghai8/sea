package com.sea.upms.web;

import com.sea.common.bean.ResultVO;
import com.sea.upms.dto.SysUserDTO;
import com.sea.upms.po.SysUser;
import com.sea.upms.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("sys/user")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @GetMapping("list")
    public ResultVO<List<SysUser>> queryList(){
        log.info("hell");
        List<SysUser> list = sysUserService.list();
       log.info("queryList {}", sysUserService.list());
        return new ResultVO<>(list);
    }

    @PostMapping()
    public ResultVO<Boolean> addUser(@RequestBody SysUserDTO sysUserDTO){
        sysUserService.saveUser(sysUserDTO);
        return null;
    }


}
