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
@TableName("egd_yc_folder_share_user")
@ApiModel(value="FolderShareUser对象", description="")
public class FolderShareUser extends AbstractEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "文件夹ID")
    private Long folderId;

    @ApiModelProperty(value = "文件夹位置")
    private String folderLocation;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "群组ID")
    private Long groupId;

    @ApiModelProperty(value = "群组名")
    private String groupName;

    @ApiModelProperty(value = "完全控制(0/1)")
    private Integer authorityAll;

    @ApiModelProperty(value = "编辑(0/1)")
    private Integer authorityEdit;

    @ApiModelProperty(value = "查看/上传(0/1)")
    private Integer authorityViewUpload;

    @ApiModelProperty(value = "查看(0/1)")
    private Integer authorityView;

    @ApiModelProperty(value = "上传(0/1)")
    private Integer authorityUpload;


}
