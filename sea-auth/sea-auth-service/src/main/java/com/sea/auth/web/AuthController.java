package com.sea.auth.web;

import com.sea.auth.entity.ResultAuth;
import com.sea.auth.entity.UserInfo;
import com.sea.auth.properties.JwtProperties;
import com.sea.auth.service.AuthService;
import com.sea.auth.utils.JwtUtils;
import com.sea.common.bean.ResultVO;
import com.sea.common.utils.CookieUtils;
import com.sea.common.utils.SeaCode;
import com.sea.upms.bo.SysUserBO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    public ResultVO<ResultAuth> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        
        ResultAuth resultAuth = authService.login(username,password);

        if(resultAuth==null) return new ResultVO<>(SeaCode.FAIL,"用户名或密码错");
        //TODO 判断用户状态
        CookieUtils.newBuilder(response).httpOnly().maxAge(jwtProperties.getCookieMaxAge()).request(request).build(jwtProperties.getCookieName(), resultAuth.getToken());
        return new ResultVO<>(resultAuth);

    }
    /**
     * 注销登录
     *
     * @param token "SEA_TOKEN"
     * @param response
     * @return
     */
    @GetMapping("/logout")
    public ResultVO<Boolean> logout(@CookieValue("SEA_TOKEN") String token, HttpServletResponse response) {

       log.info("_-------logout -token:{}----------",token);
        if (StringUtils.isNotBlank(token)) {
            CookieUtils.newBuilder(response).maxAge(0).build(jwtProperties.getCookieName(), token);
        }
        return new ResultVO<>(true);
    }

   @GetMapping("/userInfo")
    public ResultVO<SysUserBO> getUserInfo(@CookieValue("SEA_TOKEN") String token){
        log.info("获取前端的token: {}",token);
        //从Token中获取用户信息
        UserInfo userInfo = JwtUtils.getUserInfo(jwtProperties.getPublicKey(), token);
        log.info("用户的Id：,{} ",userInfo.getId());

       //authService.getUserInfo(userInfo.getId());

        return new ResultVO<>(authService.getUserInfo(userInfo.getId()));
   }
}
