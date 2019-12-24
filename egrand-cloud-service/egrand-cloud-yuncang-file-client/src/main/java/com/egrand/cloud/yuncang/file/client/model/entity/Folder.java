package com.egrand.cloud.yuncang.file.client.model.entity;

import java.math.BigDecimal;
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
@TableName("egd_yc_folder")
@ApiModel(value="Folder对象", description="")
public class Folder extends AbstractEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "文件夹名称")
    private String folderName;

    @ApiModelProperty(value = "上级文件夹")
    private Long parentId;

    @ApiModelProperty(value = "文件夹位置")
    private String folderLocation;

    @ApiModelProperty(value = "文件大小")
    private BigDecimal fileSize;

    @ApiModelProperty(value = "文件个数")
    private Long fileCount;

    @ApiModelProperty(value = "文件夹类型（gd:固定文件夹/zdy:自定义文件夹）")
    private String folderType;

    @ApiModelProperty(value = "文件夹用途（user:个人/share:资料共享/work:协作）")
    private String folderUser;

    @ApiModelProperty(value = "文件夹描述")
    private String comment;

    @ApiModelProperty(value = "创建人ID")
    private Long createrId;

    @ApiModelProperty(value = "创建人")
    private String creater;


}
