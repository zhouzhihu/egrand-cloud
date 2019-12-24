package com.egrand.cloud.ram.client.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.egrand.cloud.core.mybatis.base.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 
 *
 * @author ZZH
 * @date 2019-12-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TableName("egd_sec_model")
@ApiModel(value="Model对象", description="")
public class Model extends AbstractEntity {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String comments;

    private Date createDate;

    private String icon;

    private String innerFlag;

    private String modelCode;

    private String modelName;

    private String orderNo;

    private Integer status;

    private Date updateDate;

    private String urlPath;

    private Long parentId;


}
