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
@TableName("egd_org_group_roles")
@ApiModel(value="GroupRoles对象", description="")
public class GroupRoles extends AbstractEntity {

    private static final long serialVersionUID=1L;

    @TableId(value = "group_id", type = IdType.ID_WORKER)
    private Long groupId;

    private Long roleId;


}
