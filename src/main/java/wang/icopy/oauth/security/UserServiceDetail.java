package wang.icopy.oauth.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wang.icopy.oauth.model.Role;
import wang.icopy.oauth.model.User;
import wang.icopy.oauth.service.IRoleService;
import wang.icopy.oauth.service.IUserService;

import java.util.List;

/**
 * @author lizhengguang
 */
@Slf4j
@Service
public class UserServiceDetail implements UserDetailsService {

    private final IUserService userService;

    private final IRoleService roleService;

    public UserServiceDetail(IUserService userService, IRoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findByUserName(username);
        log.info("登录用户：" + username);
        if (user == null) {
            log.info("登录用户：" + username + " 不存在");
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        //获取用户拥有的角色
        List<Role> roles = roleService.findRolesByUserId(user.getId());
        return new SecurityUser(user.getId(), user.getUsername(), user.getPassword(), roles);
    }
}
