/*
 * Copyright (c) 2019. paascloud.net All Rights Reserved.
 * 项目名称：Egrand快速搭建企业级分布式微服务平台
 * 类名称：EgandConfig.java
 * 创建人：周志虎
 * 联系方式：13560107927@139.com
 */
package com.egrand.cloud.core.config;

import com.egrand.cloud.core.configuration.EgrandProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The class Egand config.
 *
 * @author paascloud.net @gmail.com
 */
@Configuration
@EnableConfigurationProperties(EgrandProperties.class)
public class EgandConfig {
}
