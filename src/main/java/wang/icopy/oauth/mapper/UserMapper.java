package wang.icopy.oauth.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import wang.icopy.oauth.model.User;

/**
 * @author lizhengguang
 */
public interface UserMapper extends BaseMapper<User> {

    @Override
    User selectOne(@Param("ew") Wrapper<User> wrapper);

    @Override
    int insert(User user);
}
