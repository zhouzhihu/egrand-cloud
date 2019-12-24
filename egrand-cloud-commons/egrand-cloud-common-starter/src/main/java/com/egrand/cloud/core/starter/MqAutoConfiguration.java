package com.egrand.cloud.core.starter;

import com.egrand.cloud.core.constants.QueueConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * MQ消息
 */
@Slf4j
@Configuration
public class MqAutoConfiguration {

//    /**
//     * direct模式，直接根据队列名称投递消息
//     *
//     * @return
//     */
//    @Bean
//    public Queue apiResourceQueue() {
//        Queue queue = new Queue(QueueConstants.QUEUE_SCAN_API_RESOURCE);
//        log.info("Query {} [{}]", QueueConstants.QUEUE_SCAN_API_RESOURCE, queue);
//        return queue;
//    }

    @Bean
    public Queue accessLogsQueue() {
        Queue queue = new Queue(QueueConstants.QUEUE_ACCESS_LOGS);
        log.info("Query {} [{}]", QueueConstants.QUEUE_ACCESS_LOGS, queue);
        return queue;
    }
}
