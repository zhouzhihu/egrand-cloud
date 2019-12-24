package com.egrand.cloud.yuncang.file.client.model;

import com.egrand.cloud.yuncang.file.client.model.entity.Folder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

/**
 * 全文检索文件夹信息
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ESFolderInfo extends Folder implements Serializable {

}
