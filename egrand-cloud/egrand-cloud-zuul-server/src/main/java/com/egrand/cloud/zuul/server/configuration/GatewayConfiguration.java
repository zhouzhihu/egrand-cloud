package com.egrand.cloud.zuul.server.configuration;

import com.egrand.cloud.zuul.server.filter.TokenFilter;
import com.egrand.cloud.zuul.server.filter.ZuulErrorFilter;
import com.egrand.cloud.zuul.server.filter.ZuulResponseFilter;
import com.google.common.collect.Lists;
import com.netflix.zuul.ZuulFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 网关配置类
 *
 * @author: liuyadu
 * @date: 2018/10/23 10:31
 * @description:
 */
@Slf4j
@Configuration
public class GatewayConfiguration {
    private static final String ALLOWED_HEADERS = "*";
    private static final String ALLOWED_METHODS = "*";
    private static final String ALLOWED_ORIGIN = "*";
    private static final String ALLOWED_EXPOSE = "Authorization";
    private static final Long MAX_AGE = 18000L;

    /**
     * 响应过滤器
     *
     * @return
     */
    @Bean
    public ZuulFilter zuulResponseFilter() {
        ZuulFilter zuulFilter = new ZuulResponseFilter();
        log.info("ZuulErrorFilter [{}]", zuulFilter);
        return zuulFilter;
    }

    /**
     * 错误过滤器
     *
     * @return
     */
    @Bean
    public ZuulFilter zuulErrorFilter() {
        ZuulFilter zuulFilter = new ZuulErrorFilter();
        log.info("ZuulErrorFilter [{}]", zuulFilter);
        return zuulFilter;
    }

    /**
     * 修改请求头
     *
     * @return
     */
    @Bean
    public ZuulFilter tokenFilter() {
        ZuulFilter zuulFilter = new TokenFilter();
        log.info("ModifyHeaderFilter [{}]", zuulFilter);
        return zuulFilter;
    }

    /**
     * 跨域配置
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Lists.newArrayList(ALLOWED_HEADERS.split(",")));
        config.setAllowedOrigins(Lists.newArrayList(ALLOWED_ORIGIN.split(",")));
        config.setAllowedMethods(Lists.newArrayList(ALLOWED_METHODS.split(",")));
        config.setMaxAge(MAX_AGE);
        config.addExposedHeader(ALLOWED_EXPOSE);

        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        //最大优先级,设置0不好使
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        log.info("CorsFilter [{}]", bean);
        return bean;
    }
}
