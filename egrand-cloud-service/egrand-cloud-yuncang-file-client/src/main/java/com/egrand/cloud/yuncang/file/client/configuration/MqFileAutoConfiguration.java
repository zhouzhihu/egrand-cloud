package com.egrand.cloud.yuncang.file.client.configuration;

import com.egrand.cloud.yuncang.file.client.constants.QueueFileConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author liuyadu
 */
@Slf4j
@Configuration
public class MqFileAutoConfiguration {

    /**
     * 文件夹增加
     *
     * @return
     */
    @Bean
    public Queue folderAddQueue() {
        Queue queue = new Queue(QueueFileConstants.QUEUE_ES__FOLDER_ADD);
        log.info("Query {} [{}]", QueueFileConstants.QUEUE_ES__FOLDER_ADD, queue);
        return queue;
    }

    /**
     * 文件夹删除
     * @return
     */
    @Bean
    public Queue folderDeleteQueue() {
        Queue queue = new Queue(QueueFileConstants.QUEUE_ES__FOLDER_DELETE);
        log.info("Query {} [{}]", QueueFileConstants.QUEUE_ES__FOLDER_DELETE, queue);
        return queue;
    }

    /**
     * 文件夹更新
     * @return
     */
    @Bean
    public Queue folderUpdateQueue() {
        Queue queue = new Queue(QueueFileConstants.QUEUE_ES__FOLDER_UPDATE);
        log.info("Query {} [{}]", QueueFileConstants.QUEUE_ES__FOLDER_UPDATE, queue);
        return queue;
    }

    /**
     * 文件增加
     *
     * @return
     */
    @Bean
    public Queue fileAddQueue() {
        Queue queue = new Queue(QueueFileConstants.QUEUE_ES__FILE_ADD);
        log.info("Query {} [{}]", QueueFileConstants.QUEUE_ES__FILE_ADD, queue);
        return queue;
    }

    /**
     * 文件删除
     * @return
     */
    @Bean
    public Queue fileDeleteQueue() {
        Queue queue = new Queue(QueueFileConstants.QUEUE_ES__FILE_DELETE);
        log.info("Query {} [{}]", QueueFileConstants.QUEUE_ES__FILE_DELETE, queue);
        return queue;
    }

    /**
     * 文件更新
     * @return
     */
    @Bean
    public Queue fileUpdateQueue() {
        Queue queue = new Queue(QueueFileConstants.QUEUE_ES__FILE_UPDATE);
        log.info("Query {} [{}]", QueueFileConstants.QUEUE_ES__FILE_UPDATE, queue);
        return queue;
    }
}
