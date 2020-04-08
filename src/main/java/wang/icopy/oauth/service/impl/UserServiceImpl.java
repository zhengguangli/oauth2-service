package wang.icopy.oauth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import wang.icopy.oauth.mapper.UserMapper;
import wang.icopy.oauth.model.User;
import wang.icopy.oauth.service.IUserService;


/**
 * @author lizhengguang
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User findByUserName(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 查询条件
        queryWrapper.lambda().eq(User::getUsername, name);
        return baseMapper.selectOne(queryWrapper);
    }

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User create(String username, String password) {

        User user = new User();
        user.setUsername(username);
        password = "{bcrypt}" + passwordEncoder.encode(password);
        user.setPassword(password);
        int insert = baseMapper.insert(user);
        return user;
    }

}
