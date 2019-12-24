package com.egrand.cloud.ram.client.model.entity;

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
@TableName("egd_sec_role_privilege")
@ApiModel(value="RolePrivilege对象", description="")
public class RolePrivilege extends AbstractEntity {

    private static final long serialVersionUID=1L;

    private Long roleId;

    private Long privilegeId;


}
