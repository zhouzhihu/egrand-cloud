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
@TableName("egd_yc_file")
@ApiModel(value="File对象", description="")
public class File extends AbstractEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "文件夹ID")
    private Long folderId;

    @ApiModelProperty(value = "文件夹位置")
    private String folderLocation;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件类型")
    private String fileType;

    @ApiModelProperty(value = "文件大小(KB)")
    private Long fileSize;

    @ApiModelProperty(value = "文件物理存储路径")
    private String filePath;

    @ApiModelProperty(value = "创建人ID")
    private Long createrId;

    @ApiModelProperty(value = "创建人")
    private String creater;


}
