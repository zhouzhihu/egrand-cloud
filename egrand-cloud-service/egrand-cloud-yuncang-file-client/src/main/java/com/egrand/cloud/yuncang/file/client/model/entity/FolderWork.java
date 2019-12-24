package com.egrand.cloud.yuncang.file.client.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
@TableName("egd_yc_folder_work")
@ApiModel(value="FolderWork对象", description="")
public class FolderWork extends AbstractEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "文件夹ID")
    private Long folderId;

    @ApiModelProperty(value = "限办时间")
    private Date limitTime;

    @ApiModelProperty(value = "提醒内容")
    private String remindMessage;

    @ApiModelProperty(value = "提醒时间间隔表达式")
    private String remindExpression;

    @ApiModelProperty(value = "通知方式（系统消息/邮件/短信）")
    private String remindMode;


}
