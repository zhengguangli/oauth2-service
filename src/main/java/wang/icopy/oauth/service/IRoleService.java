package wang.icopy.oauth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import wang.icopy.oauth.model.Role;

import java.util.List;

/**
 * @author lizhengguang
 */
public interface IRoleService extends IService<Role> {

    /**
     * 根据用户id查找该用户角色
     *
     * @param userId
     * @return
     */
    List<Role> findRolesByUserId(@Param("userId") Integer userId);
}
