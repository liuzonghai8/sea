package api;

import com.sea.upms.po.SysUser;
import org.springframework.web.bind.annotation.*;

/**
 * @author bystander
 * @date 2018/10/1
 */
@RequestMapping("sys/user")
public interface UserApi {

    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return sys/user/
     */
    @PostMapping("query")
    SysUser queryUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    );

//    @GetMapping("/{id}")
//    UserVo findUserAllInfo(@PathVariable("id") Long userid);

//    @GetMapping("/{id}")
//    UserVo findUserAll(Long userid);
//
//    @GetMapping("test")
//     String test();
}
