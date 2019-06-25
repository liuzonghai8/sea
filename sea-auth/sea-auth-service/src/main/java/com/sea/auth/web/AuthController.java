package com.sea.auth.web;

import com.sea.auth.entity.ResultAuth;
import com.sea.auth.properties.JwtProperties;
import com.sea.auth.service.AuthService;
import com.sea.common.bean.ResultVO;
import com.sea.common.utils.CookieUtils;
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
        
       // String token = authService.login(username,password);
        ResultAuth resultAuth = authService.login(username,password);
        if(resultAuth==null){
            return
        }
        CookieUtils.newBuilder(response).httpOnly().maxAge(jwtProperties.getCookieMaxAge()).request(request).build(jwtProperties.getCookieName(), resultAuth.getToken());
        return new ResultVO<>(resultAuth);

    }
    /**
     * 注销登录
     *
     * @param token "SWEET_TOKEN"
     * @param response
     * @return
     */
    @GetMapping("/logout")
    public ResultVO<Void> logout(@CookieValue("SEA_TOKEN") String token, HttpServletResponse response) {

       log.info("_-------logout -token:{}----------",token);
        if (StringUtils.isNotBlank(token)) {
            CookieUtils.newBuilder(response).maxAge(0).build(jwtProperties.getCookieName(), token);
        }
        return new ResultVO<>();
    }

//    @GetMapping("/userInfo")
//    public Result<UserVo> getUserInfo(@CookieValue("SEA_TOKEN") String token){
//        log.info("获取前端的token: {}",token);
//        //从Token中获取用户信息
//        UserInfo userInfo = JwtUtils.getUserInfo(jwtProperties.getPublicKey(), token);
//        log.info("用户的Id：,{} ",userInfo.getId());
//
//        UserVo userVo = authService.getUserInfo(userInfo.getId());
//
//        log.info("userVo is : {}",userVo.toString());
//
//        return new Result<>(authService.getUserInfo(userInfo.getId()));
//    }
}
