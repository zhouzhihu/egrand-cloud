package com.egrand.cloud.core.starter;

import com.egrand.cloud.core.configuration.ElasticsearchProperties;
import com.egrand.cloud.core.configuration.OpenCommonProperties;
import com.egrand.cloud.core.configuration.OpenIdGenProperties;
import com.egrand.cloud.core.configuration.OpenScanProperties;
import com.egrand.cloud.core.exception.OpenGlobalExceptionHandler;
import com.egrand.cloud.core.exception.OpenRestResponseErrorHandler;
import com.egrand.cloud.core.filter.XFilter;
import com.egrand.cloud.core.gen.SnowflakeIdGenerator;
import com.egrand.cloud.core.health.DbHealthIndicator;
import com.egrand.cloud.core.security.http.OpenRestTemplate;
import com.egrand.cloud.core.security.oauth2.client.OpenOAuth2ClientProperties;
import com.egrand.cloud.core.utils.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.bus.BusProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

/**
 * 默认配置类
 *
 * @author liuyadu
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({OpenCommonProperties.class, OpenIdGenProperties.class, OpenScanProperties.class, OpenOAuth2ClientProperties.class})
public class AutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(ElasticsearchProperties.class)
    public ElasticsearchProperties elasticsearchProperties(){
        return  new ElasticsearchProperties();
    }

    @Bean
    @ConditionalOnMissingBean(OpenScanProperties.class)
     public  OpenScanProperties scanProperties(){
         return  new OpenScanProperties();
     }

    /**
     * xss过滤
     * body缓存
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean XssFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new XFilter());
        log.info("XFilter [{}]", filterRegistrationBean);
        return filterRegistrationBean;
    }

//    /**
//     * 分页插件
//     */
//    @ConditionalOnMissingBean(PaginationInterceptor.class)
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//        log.info("PaginationInterceptor [{}]", paginationInterceptor);
//        return paginationInterceptor;
//    }

    /**
     * 默认加密配置
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(BCryptPasswordEncoder.class)
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        log.info("BCryptPasswordEncoder [{}]", encoder);
        return encoder;
    }


    /**
     * Spring上下文工具配置
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SpringContextHolder.class)
    public SpringContextHolder springContextHolder() {
        System.out.println("===========SpringContextHolder============");
        SpringContextHolder holder = new SpringContextHolder();
        log.info("SpringContextHolder [{}]", holder);
        System.out.println("holder = " + holder);
        System.out.println("===========SpringContextHolder============");
        return holder;
    }

    /**
     * 统一异常处理配置
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(OpenGlobalExceptionHandler.class)
    public OpenGlobalExceptionHandler exceptionHandler() {
        OpenGlobalExceptionHandler exceptionHandler = new OpenGlobalExceptionHandler();
        log.info("OpenGlobalExceptionHandler [{}]", exceptionHandler);
        return exceptionHandler;
    }

    /**
     * ID生成器配置
     *
     * @param properties
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(OpenIdGenProperties.class)
    public SnowflakeIdGenerator snowflakeIdWorker(OpenIdGenProperties properties) {
        SnowflakeIdGenerator snowflakeIdGenerator = new SnowflakeIdGenerator(properties.getWorkId(), properties.getCenterId());
        log.info("SnowflakeIdGenerator [{}]", snowflakeIdGenerator);
        return snowflakeIdGenerator;
    }


//    /**
//     * 自定义注解扫描
//     *
//     * @return
//     */
//    @Bean
//    @ConditionalOnMissingBean(RequestMappingScan.class)
//    public RequestMappingScan resourceAnnotationScan(AmqpTemplate amqpTemplate, OpenScanProperties scanProperties) {
//        RequestMappingScan scan = new RequestMappingScan(amqpTemplate,scanProperties);
//        log.info("RequestMappingScan [{}]", scan);
//        return scan;
//    }

    /**
     * 自定义Oauth2请求类
     *
     * @param openCommonProperties
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(OpenRestTemplate.class)
    public OpenRestTemplate openRestTemplate(OpenCommonProperties openCommonProperties, BusProperties busProperties, ApplicationEventPublisher publisher) {
        OpenRestTemplate restTemplate = new OpenRestTemplate(openCommonProperties, busProperties, publisher);
        //设置自定义ErrorHandler
        restTemplate.setErrorHandler(new OpenRestResponseErrorHandler());
        log.info("OpenRestTemplate [{}]", restTemplate);
        return restTemplate;
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        //设置自定义ErrorHandler
        restTemplate.setErrorHandler(new OpenRestResponseErrorHandler());
        log.info("RestTemplate [{}]", restTemplate);
        return restTemplate;
    }

    @Bean
    @ConditionalOnMissingBean(DbHealthIndicator.class)
    public DbHealthIndicator dbHealthIndicator() {
        DbHealthIndicator dbHealthIndicator = new DbHealthIndicator();
        log.info("DbHealthIndicator [{}]", dbHealthIndicator);
        return dbHealthIndicator;
    }

//
//    /**
//     * 自动填充模型数据
//     *
//     * @return
//     */
//    @Bean
//    @ConditionalOnMissingBean(ModelMetaObjectHandler.class)
//    public ModelMetaObjectHandler modelMetaObjectHandler() {
//        ModelMetaObjectHandler metaObjectHandler = new ModelMetaObjectHandler();
//        log.info("ModelMetaObjectHandler [{}]", metaObjectHandler);
//        return metaObjectHandler;
//    }
}
