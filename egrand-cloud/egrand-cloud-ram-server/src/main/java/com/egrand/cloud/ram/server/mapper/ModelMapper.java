package com.egrand.cloud.ram.server.mapper;

import com.egrand.cloud.core.mybatis.base.mapper.SuperMapper;
import com.egrand.cloud.ram.client.model.entity.Model;
import org.apache.ibatis.annotations.Mapper;

/**
 *  Mapper 接口
 * @author ZZH
 * @date 2019-12-12
 */
@Mapper
public interface ModelMapper extends SuperMapper<Model> {

}
