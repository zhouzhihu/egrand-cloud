package com.egrand.cloud.ram.client.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.egrand.cloud.core.mybatis.base.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 *
 * @author ZZH
 * @date 2019-12-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TableName("egd_org_ouinfo")
@ApiModel(value="Ouinfo对象", description="")
public class Ouinfo extends AbstractEntity {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String address;

    private String description;

    private String email;

    private String field1;

    private String field2;

    private String field3;

    private String field4;

    private String ouCode;

    private String ouFullCode;

    private String ouFullName;

    private String ouName;

    private String ouType;

    private String telephone;

    private String zipCode;

    private Long parentId;


}
