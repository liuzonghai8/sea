package com.sea.auth.client;

import api.UserApi;
import com.sea.upms.dto.SysUserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *sys/user/
 */

    @FeignClient(value="upms-service")
    public interface UserClient extends UserApi {
  }






