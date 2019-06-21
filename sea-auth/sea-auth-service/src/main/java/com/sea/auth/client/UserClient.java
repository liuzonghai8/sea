package com.sea.auth.client;

import api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 *sys/user/
 */

    @FeignClient(value="upms-service")
    public interface UserClient extends UserApi {
  }






