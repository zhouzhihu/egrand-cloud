/*
 * Copyright (c) 2019. paascloud.net All Rights Reserved.
 * 项目名称：Egrand快速搭建企业级分布式微服务平台
 * 类名称：EgandConfig.java
 * 创建人：周志虎
 * 联系方式：13560107927@139.com
 */
package com.egrand.cloud.core.configuration;

import lombok.Data;

/**
 * The class Swagger properties.
 *
 * @author 13560107927@139.com
 */
@Data
public class SwaggerProperties {

	private String title;

	private String description;

	private String version = "1.0";

	private String license = "Apache License 2.0";

	private String licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0";

	private String contactName = "周志虎";

	private String contactUrl = "http://www.ico.com.cn";

	private String contactEmail = "13560107927@139.com";
}
