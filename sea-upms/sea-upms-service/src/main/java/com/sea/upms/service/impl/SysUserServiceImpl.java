package com.sea.upms.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.netflix.discovery.CommonConstants;
import com.sea.common.bean.CommonConstant;
import com.sea.common.utils.ConvertUtils;
import com.sea.upms.dto.MenuTree;
import com.sea.upms.dto.SysUserDTO;
import com.sea.upms.dto.SysUserInfo;
import com.sea.upms.mapper.SysUserMapper;
import com.sea.upms.po.SysPermission;
import com.sea.upms.po.SysUser;
import com.sea.upms.po.SysUserRole;
import com.sea.upms.service.ISysPermisionService;
import com.sea.upms.service.ISysUserRoleService;
import com.sea.upms.service.ISysUserService;
import com.sea.upms.utils.PasswordUtil;
import com.sea.upms.vo.MenuVO;
import com.sea.upms.vo.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements ISysUserService {


   @Autowired
   private SysUserMapper sysUserMapper;
   @Autowired
   private ISysUserRoleService sysUserRoleService;
   @Autowired
   private ISysPermisionService sysPermisionService;

    @Override
    public SysUser getUserByNameAndPassword(String username, String password) {
        /*
        * 0、根据用户名查询用户
        * 1、判断查询到用户是否为空，空 返回用户不存在
        * 2、不为空，先判断用户是否锁定，锁定返回锁定信息 //放控制层
        * 3、再判断密码是否错误，返回密码错误
        * 4、返回用户*/
        //手写sql语句
        //SysUser user = sysUserMapper.selectByName(username);
        //wrappers 的查询连接语句
        final LambdaQueryWrapper<SysUser> eq = Wrappers.<SysUser>query()
                .lambda().eq(SysUser::getUsername, username);

        log.info("-------eq:----{}",eq);

        SysUser user =  sysUserMapper.selectOne(Wrappers.<SysUser>query()
                .lambda().eq(SysUser::getUsername, username));
        if(user==null) return null;
            //throw new SeaException(ExceptionEnum.USER_NOT_EXIST);
        //判断密码
        if(!StringUtils.equals(PasswordUtil.encrypt(username,password,user.getSalt()),user.getPassword()))
            return null;
           // throw new SeaException(ExceptionEnum.USERNAME_OR_PASSWORD_ERROR);

        return user;
    }

    /**
     * 添加用户
     * @param sysUserDTO
     * @return
     */
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

    /**
     *
     * @param userId
     * @return
     */
    @Override
    public SysUserInfo getUserAllInfo(String userId) {
        /*
        * 1.获取用户基本信息
        * 2.获取用户角色
        * 3.获取用户权限及菜单
        * */
        SysUserInfo userInfo = new SysUserInfo();
        SysUser sysUser =baseMapper.selectById(userId);
        log.info("---用户信息：--sysUser-----:{}",sysUser);
        userInfo.setSysUser(sysUser);

        //roles
        List<String> roles = sysUserRoleService.list(Wrappers.<SysUserRole>query()
                .lambda().eq(SysUserRole::getUserId, sysUser.getId()))
                .stream()
                .map(SysUserRole::getRoleId)
                .collect(Collectors.toList());
        log.info("------roles:----{}",roles);
        userInfo.setRoles(ArrayUtil.toArray(roles, String.class));

        //menus
       /**
        * 1,左连接查询用户改角色菜单
        * */
        Set<String> permissions = new HashSet<>();
        Set<SysPermission> all = new HashSet<>();
       roles.forEach(roleid->{
           List<SysPermission> permissionList = sysPermisionService.getPermissionByRoleId(roleid);
           //权限列表
           List<String> collect = sysPermisionService.getPerms(permissionList);
           permissions.addAll(collect);
            //menus 列表
           all.addAll(permissionList);
       });

        List<MenuTree> menuTreeList = all.stream()
                .filter(menuVo -> CommonConstant.MENU_TYPE_0.equals(menuVo.getMenuType()))
                .map(MenuTree::new)
                .sorted(Comparator.comparingInt(MenuTree::getSort))
                .collect(Collectors.toList());
        TreeUtil.buildByLoop(menuTreeList, -1);

       userInfo.setPermissions(ArrayUtil.toArray(permissions,String.class));

        return userInfo;
    }

    @Override
    public SysUser saveUserTest(SysUserDTO sysUserDTO) {
        log.info("-----saveUser----------controller传过来的参数为：{}",sysUserDTO);
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserDTO,sysUser);
//        String id =UUID.randomUUID().toString().replace("-", "");
//        sysUser.setId(id);
        //设置用户salt
        String salt = sysUser.getSalt();//ConvertUtils.randomGen(8);
        //加密密码
        String password = PasswordUtil.encrypt(sysUser.getUsername(), sysUser.getPassword(), salt);
        sysUser.setPassword(password);
        log.info("------name:{}-----",sysUser.getUsername());
        log.info("------salt:{}-----",sysUser.getSalt());
        log.info("------password:{}-----",password);
        //Boolean result = this.save(sysUser);
        return sysUser;
    }


}
