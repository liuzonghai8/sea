package com.sea.upms.web;

import com.sea.common.bean.ResultVO;
import com.sea.upms.bo.SysUserBO;
import com.sea.upms.dto.SysUserDTO;
import com.sea.upms.po.SysUser;
import com.sea.upms.service.ISysUserService;
import com.sea.upms.vo.SysUserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
        return  new ResultVO<>(sysUserService.saveUser(sysUserDTO));
    }

    /**
     * 登陆用户 只提供给auth-service调用
     * */
    @PostMapping("qurey")
    public SysUser loginByUserName(@RequestParam("username") String username,
                                   @RequestParam("password") String password){
        log.info("------loginByUserName-----param username is:{} , password is {}",username,password);
        return sysUserService.getUserByNameAndPassword(username,password);
    }

    /**
     *  通过用户ID 获取用户相关的所有信息，包括角色、权限、菜单
     */
    @GetMapping("Info")
    public SysUserBO getUserAllInfo(String userId){
        return  sysUserService.getUserAllInfo(userId);
    }

}
