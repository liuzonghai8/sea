package com.sea.upms.web;

import com.sea.common.bean.Result;
import com.sea.upms.pojo.SysUser;
import com.sea.upms.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sys")
@Slf4j
public class LoginController {

    @Autowired
    private ISysUserService sysUserService;

    @PostMapping("login")
    public Result<SysUser> getUserByName(@Param("username") String username, @Param("password") String password){
        log.info("-----getUserByName--------,param username is:{}, password is {}",username,password);
        //return sysUserService.getUserByName(username,password);
        Result<SysUser> result =new Result<>();
        result.setCode(1);
        result.setMessage("登陆失败！");
        return result;
    }
}
