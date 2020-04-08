package wang.icopy.oauth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import wang.icopy.oauth.model.Role;

import java.util.List;

/**
 * @author lizhengguang
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * @param userId
     * @return
     */
    List<Role> findRolesByUserId(@Param("userId") Integer userId);
}

