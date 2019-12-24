package com.egrand.cloud.ram.server.service.impl;

import com.egrand.cloud.core.mybatis.base.service.impl.BaseServiceImpl;
import com.egrand.cloud.ram.client.model.entity.Ouinfo;
import com.egrand.cloud.ram.server.mapper.OuinfoMapper;
import com.egrand.cloud.ram.server.service.OuinfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *  服务实现类
 *
 * @author ZZH
 * @date 2019-12-12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OuinfoServiceImpl extends BaseServiceImpl<OuinfoMapper, Ouinfo> implements OuinfoService {

}
