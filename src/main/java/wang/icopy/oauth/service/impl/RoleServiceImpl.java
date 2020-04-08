package wang.icopy.oauth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import wang.icopy.oauth.mapper.RoleMapper;
import wang.icopy.oauth.model.Role;
import wang.icopy.oauth.service.IRoleService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lizhengguang
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> findRolesByUserId(Integer userId) {
        return roleMapper.findRolesByUserId(userId);
    }


}
