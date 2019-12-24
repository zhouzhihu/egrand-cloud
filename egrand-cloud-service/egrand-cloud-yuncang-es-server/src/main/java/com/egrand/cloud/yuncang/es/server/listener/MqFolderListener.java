package com.egrand.cloud.yuncang.es.server.listener;

import com.alibaba.fastjson.JSON;
import com.egrand.cloud.core.utils.ElasticsearchUtil;
import com.egrand.cloud.yuncang.file.client.constants.QueueFileConstants;
import com.egrand.cloud.yuncang.file.client.model.ESFolderInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

@Configuration
@Slf4j
public class MqFolderListener {
    /**
     * 测试索引
     */
    private String indexName="yuncang";

    /**
     * 类型
     */
    private String esType="yuncang_folder";

    /**
     * 接收文件夹增加消息
     *
     * @param esFolderInfo
     */
    @RabbitListener(queues = QueueFileConstants.QUEUE_ES__FOLDER_ADD)
    public String folderAddQueue(@Payload ESFolderInfo esFolderInfo) {
        if(!ElasticsearchUtil.isIndexExist(indexName)) {
            ElasticsearchUtil.createIndex(indexName);
        }
        if (null != esFolderInfo) {
            String id = ElasticsearchUtil.addData(JSON.parseObject(JSON.toJSONString(esFolderInfo)), indexName, esType, esFolderInfo.getId().toString());
            return id;
        }
        return "-1";
    }

    /**
     * 接收文件更新消息
     * @param esFolderInfo
     */
    @RabbitListener(queues = QueueFileConstants.QUEUE_ES__FOLDER_UPDATE)
    public void folderUpdateQueue(@Payload ESFolderInfo esFolderInfo){
        if(!ElasticsearchUtil.isIndexExist(indexName)) {
            ElasticsearchUtil.createIndex(indexName);
        }
        if(null != esFolderInfo){
            ElasticsearchUtil.updateDataById(JSON.parseObject(JSON.toJSONString(esFolderInfo)), indexName, esType, esFolderInfo.getId().toString());
        }
    }

    /**
     * 接收文件删除消息
     * @param id
     */
    @RabbitListener(queues = QueueFileConstants.QUEUE_ES__FOLDER_DELETE)
    public void folderDeleteQueue(@Payload String id){
        if(!ElasticsearchUtil.isIndexExist(indexName)) {
            ElasticsearchUtil.createIndex(indexName);
        }
        ElasticsearchUtil.deleteDataById(indexName, esType, id);
    }
}
