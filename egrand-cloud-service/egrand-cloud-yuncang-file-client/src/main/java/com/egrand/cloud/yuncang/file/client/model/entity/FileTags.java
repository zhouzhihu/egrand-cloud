package com.egrand.cloud.yuncang.file.client.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.egrand.cloud.core.mybatis.base.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 *
 * @author ZZH
 * @date 2019-12-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TableName("egd_yc_file_tags")
@ApiModel(value="FileTags对象", description="")
public class FileTags extends AbstractEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "文件ID")
    private Long fileId;

    @ApiModelProperty(value = "标签名称")
    private String tagName;

    @ApiModelProperty(value = "创建人ID")
    private Long createrId;

    @ApiModelProperty(value = "创建人")
    private String creater;


}
