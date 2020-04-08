package wang.icopy.oauth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wang.icopy.oauth.model.User;

/**
 * @author lizhengguang
 */
public interface IUserService extends IService<User> {


    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    User findByUserName(String username);

    User create(String username, String password);

}
