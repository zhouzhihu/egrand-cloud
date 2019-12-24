package com.egrand.cloud.yuncang.file.server.mq;

import com.egrand.cloud.yuncang.file.client.constants.QueueFileConstants;
import com.egrand.cloud.yuncang.file.client.model.ESFileInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MqFileService {

    @Autowired
    private AmqpTemplate amqpTemplate;
    private ExecutorService executorService;

    public MqFileService() {
        Integer availableProcessors = Runtime.getRuntime().availableProcessors();
        Integer numOfThreads = availableProcessors * 2;
        executorService = new ThreadPoolExecutor(numOfThreads, numOfThreads, 0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());
    }

    /**
     * 发送增加文件消息
     * @param fileInfo
     */
    public void sendAddFile(ESFileInfo fileInfo){
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    amqpTemplate.convertAndSend(QueueFileConstants.QUEUE_ES__FILE_ADD, fileInfo);
                } catch (Exception e) {
                    log.error("file add error:{}", e);
                }
            }
        });
    }

    /**
     * 发送更新文件消息
     * @param fileInfo
     */
    public void sendUpdateFile(ESFileInfo fileInfo){
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    amqpTemplate.convertAndSend(QueueFileConstants.QUEUE_ES__FILE_UPDATE, fileInfo);
                } catch (Exception e) {
                    log.error("file update error:{}", e);
                }
            }
        });
    }

    /**
     * 发送删除文件消息
     * @param fileInfo
     */
    public void sendDeleteFile(ESFileInfo fileInfo){
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    amqpTemplate.convertAndSend(QueueFileConstants.QUEUE_ES__FILE_DELETE, fileInfo);
                } catch (Exception e) {
                    log.error("file delete error:{}", e);
                }
            }
        });
    }
}
