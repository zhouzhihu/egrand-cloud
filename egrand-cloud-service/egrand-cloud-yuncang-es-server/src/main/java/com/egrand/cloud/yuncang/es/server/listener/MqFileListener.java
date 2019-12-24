package com.egrand.cloud.yuncang.es.server.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egrand.cloud.core.utils.ElasticsearchUtil;
import com.egrand.cloud.yuncang.file.client.constants.QueueFileConstants;
import com.egrand.cloud.yuncang.file.client.model.ESFileInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Map;

@Configuration
@Slf4j
public class MqFileListener {
    /**
     * 测试索引
     */
    private String indexName="yuncang";

    /**
     * 类型
     */
    private String esType="yuncang_file";

    /**
     * 接收文件增加消息
     *
     * @param esFileInfo
     */
    @RabbitListener(queues = QueueFileConstants.QUEUE_ES__FILE_ADD)
    public String fileAddQueue(@Payload ESFileInfo esFileInfo) {
        if(!ElasticsearchUtil.isIndexExist(indexName)) {
            ElasticsearchUtil.createIndex(indexName);
        }
        if (null != esFileInfo) {
            String id = ElasticsearchUtil.addData(JSON.parseObject(JSON.toJSONString(esFileInfo)), indexName, esType, esFileInfo.getId().toString());
            return id;
        }
        return "-1";
    }

    /**
     * 接收文件更新消息
     * @param esFileInfo
     */
    @RabbitListener(queues = QueueFileConstants.QUEUE_ES__FILE_UPDATE)
    public void fileUpdateQueue(@Payload ESFileInfo esFileInfo){
        if(!ElasticsearchUtil.isIndexExist(indexName)) {
            ElasticsearchUtil.createIndex(indexName);
        }
        if(null != esFileInfo){
            ElasticsearchUtil.updateDataById(JSON.parseObject(JSON.toJSONString(esFileInfo)), indexName, esType, esFileInfo.getId().toString());
        }
    }

    /**
     * 接收文件删除消息
     * @param id
     */
    @RabbitListener(queues = QueueFileConstants.QUEUE_ES__FILE_DELETE)
    public void fileDeleteQueue(@Payload String id){
        if(!ElasticsearchUtil.isIndexExist(indexName)) {
            ElasticsearchUtil.createIndex(indexName);
        }
        ElasticsearchUtil.deleteDataById(indexName, esType, id);
    }
}
