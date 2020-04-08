package wang.icopy.oauth.handler;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


/**
 * @author lizhengguang
 */
@Slf4j
@Component
public class AuthenticationSuccessEventHandler implements ApplicationListener<AuthenticationSuccessEvent> {

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {

        Authentication authentication = (Authentication) event.getSource();
        if (CollectionUtils.isNotEmpty(authentication.getAuthorities())) {
            log.info("用户：{} 登录成功", authentication.getPrincipal());
        }

    }
}
