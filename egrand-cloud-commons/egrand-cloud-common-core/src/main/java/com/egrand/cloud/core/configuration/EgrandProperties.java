/*
 * Copyright (c) 2019. paascloud.net All Rights Reserved.
 * 项目名称：Egrand快速搭建企业级分布式微服务平台
 * 类名称：EgandConfig.java
 * 创建人：周志虎
 * 联系方式：13560107927@139.com
 */
package com.egrand.cloud.core.configuration;

import com.egrand.cloud.core.constants.GlobalConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * The class Paascloud properties.
 *
 * @author paascloud.net @gmail.com
 */
@Data
@ConfigurationProperties(prefix = GlobalConstant.ROOT_PREFIX)
public class EgrandProperties {
	private SwaggerProperties swagger = new SwaggerProperties();
}
