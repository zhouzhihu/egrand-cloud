package com.egrand.cloud.ram.server.listener;

import com.egrand.cloud.core.constants.QueueConstants;
import com.egrand.cloud.core.utils.BeanConvertUtils;
import com.egrand.cloud.ram.client.model.GatewayAccessLogs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Map;

/**
 * mq消息接收者
 *
 * @author liuyadu
 */
@Configuration
@Slf4j
public class AccessLogsHandler {

    /**
     * 接收访问日志
     *
     * @param access
     */
    @RabbitListener(queues = QueueConstants.QUEUE_ACCESS_LOGS)
    public void accessLogsQueue(@Payload Map access) {
        try {
            if (access != null) {
                GatewayAccessLogs logs = BeanConvertUtils.mapToObject(access, GatewayAccessLogs.class);
                if (logs != null) {
                    if (logs.getIp() != null) {
                        logs.setRegion(logs.getIp());
                    }
                    logs.setUseTime(logs.getResponseTime().getTime() - logs.getRequestTime().getTime());
                    //TODO 待插入网关日志
                    log.info(log.toString());
                }
            }
        } catch (Exception e) {
            log.error("error:", e);
        }
    }
}
