package com.sea.upms.web;

import com.sea.common.bean.ResultVO;
import com.sea.upms.po.SysUser;
import com.sea.upms.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sys")
@Slf4j
public class LoginController {

    @Autowired
    private ISysUserService sysUserService;

    @PostMapping("login")
    public ResponseEntity<SysUser> loginByUserName(@RequestParam("username") String username,
                                          @RequestParam("password") String password){
        log.info("------loginByUserName-----param username is:{} , password is {}",username,password);
        return ResponseEntity.ok(sysUserService.getUserByNameAndPassword(username, password));
    }


}
