package com.sea.upms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sea.common.enums.ExceptionEnum;
import com.sea.common.exception.SeaException;
import com.sea.common.utils.ConvertUtils;
import com.sea.upms.dto.SysUserDTO;
import com.sea.upms.mapper.SysUserMapper;
import com.sea.upms.po.SysUser;
import com.sea.upms.service.ISysUserService;

import com.sea.upms.utils.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements ISysUserService {


   @Autowired
   private SysUserMapper sysUserMapper;
    @Override
    public SysUser getUserByNameAndPassword(String username, String password) {
        /*
        * 0、根据用户名查询用户
        * 1、判断查询到用户是否为空，空 返回用户不存在
        * 2、不为空，先判断用户是否锁定，锁定返回锁定信息 //放控制层
        * 3、再判断密码是否错误，返回密码错误
        * 4、返回用户*/
        SysUser user = sysUserMapper.selectByName(username);
        if(user==null) return null;
            //throw new SeaException(ExceptionEnum.USER_NOT_EXIST);
        //判断密码
        if(!StringUtils.equals(PasswordUtil.encrypt(username,password,user.getSalt()),user.getPassword()))
            return null;
           // throw new SeaException(ExceptionEnum.USERNAME_OR_PASSWORD_ERROR);

        return user;
    }

    /*
    * 添加用户
    * */
    @Override
    public Boolean saveUser(SysUserDTO sysUserDTO) {
        /*
        * 1、先保存用户，需要添加
        * 1.1、添加盐
        * 1.2、加密密码
        * 1.3、创建时间、创建人
        * 1.4、设置状态及删除标致
        * 2、再保存角色
        * */
        log.info("-----saveUser----------controller传过来的参数为：{}",sysUserDTO);
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserDTO,sysUser);
        String id =UUID.randomUUID().toString().replace("-", "");
        sysUser.setId(id);
        //设置用户salt
        String salt = ConvertUtils.randomGen(8);
        //加密密码
        sysUser.setPassword(PasswordUtil.encrypt(sysUser.getUsername(), sysUser.getPassword(),salt));
        sysUser.setSalt(salt).setCreateTime(new Date()).setStatus(1).setDelFlag("0");
        Boolean result = this.save(sysUser);
        log.info("------insert user------{},----result----{}",sysUser,result);
        List<String> roles = sysUserDTO.getRoles();
        if(ConvertUtils.isNotEmpty(roles)){
            //TODO 添加角色
        }


        return result;
    }
}
