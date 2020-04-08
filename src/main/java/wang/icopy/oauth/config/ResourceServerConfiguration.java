package wang.icopy.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import wang.icopy.oauth.handler.AuthExceptionEntryPoint;
import wang.icopy.oauth.handler.CustomAccessDeniedHandler;
import wang.icopy.oauth.handler.CustomTokenEnhancer;


/**
 * @author lizhengguang
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String DEMO_RESOURCE_ID = "order";

    private final CustomAccessDeniedHandler accessDeniedHandler;

    private final AuthExceptionEntryPoint authExceptionEntryPoint;

    final
    CustomTokenEnhancer customTokenEnhancer;

    public ResourceServerConfiguration(CustomAccessDeniedHandler accessDeniedHandler, AuthExceptionEntryPoint authExceptionEntryPoint, CustomTokenEnhancer customTokenEnhancer) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.authExceptionEntryPoint = authExceptionEntryPoint;
        this.customTokenEnhancer = customTokenEnhancer;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .resourceId(DEMO_RESOURCE_ID).stateless(true)
                .authenticationEntryPoint(authExceptionEntryPoint) // 外部定义的token错误进入的方法
                .accessDeniedHandler(accessDeniedHandler); // 没有权限的进入方法

        resources.tokenServices(tokenServices());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                // 配置order访问控制，必须认证后才可以访问
                .antMatchers("/order/**").authenticated();
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }

    @Bean
    public DefaultTokenServices tokenServices() {

        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

}
