package com.egrand.cloud.yuncang.file.client.model;

import com.egrand.cloud.yuncang.file.client.model.entity.File;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

/**
 * 全文检索文件信息
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ESFileInfo extends File implements Serializable {

}
