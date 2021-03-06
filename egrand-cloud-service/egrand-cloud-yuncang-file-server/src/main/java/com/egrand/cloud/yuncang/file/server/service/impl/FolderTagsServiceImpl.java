package com.egrand.cloud.yuncang.file.server.service.impl;

import com.egrand.cloud.yuncang.file.client.model.entity.FolderTags;
import com.egrand.cloud.yuncang.file.server.mapper.FolderTagsMapper;
import com.egrand.cloud.yuncang.file.server.service.FolderTagsService;
import com.egrand.cloud.core.mybatis.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author ZZH
 * @date 2019-12-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FolderTagsServiceImpl extends BaseServiceImpl<FolderTagsMapper, FolderTags> implements FolderTagsService {

}
