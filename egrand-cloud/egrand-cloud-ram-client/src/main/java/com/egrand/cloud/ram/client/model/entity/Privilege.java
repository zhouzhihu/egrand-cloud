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
@TableName("egd_sec_privilege")
@ApiModel(value="Privilege对象", description="")
public class Privilege extends AbstractEntity {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String comments;

    private Date createDate;

    private String icon;

    private String innerFlag;

    private String orderNo;

    private String privilegeCode;

    private String privilegeName;

    private Integer status;

    private String type;

    private Date updateDate;

    private String urlPath;

    private Long modelId;


}
