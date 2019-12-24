package com.egrand.cloud.yuncang.file.server.mq;

import com.egrand.cloud.yuncang.file.client.constants.QueueFileConstants;
import com.egrand.cloud.yuncang.file.client.model.ESFolderInfo;
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
public class MqFolderService {
    @Autowired
    private AmqpTemplate amqpTemplate;
    private ExecutorService executorService;

    public MqFolderService() {
        Integer availableProcessors = Runtime.getRuntime().availableProcessors();
        Integer numOfThreads = availableProcessors * 2;
        executorService = new ThreadPoolExecutor(numOfThreads, numOfThreads, 0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());
    }

    /**
     * 发送增加文件消息
     * @param folderInfo
     */
    public void sendAddFolder(ESFolderInfo folderInfo){
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    amqpTemplate.convertAndSend(QueueFileConstants.QUEUE_ES__FOLDER_ADD, folderInfo);
                } catch (Exception e) {
                    log.error("folder add error:{}", e);
                }
            }
        });
    }

    /**
     * 发送更新文件消息
     * @param folderInfo
     */
    public void sendUpdateFolder(ESFolderInfo folderInfo){
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    amqpTemplate.convertAndSend(QueueFileConstants.QUEUE_ES__FOLDER_UPDATE, folderInfo);
                } catch (Exception e) {
                    log.error("folder update error:{}", e);
                }
            }
        });
    }

    /**
     * 发送删除文件消息
     * @param folderInfo
     */
    public void sendDeleteFolder(ESFolderInfo folderInfo){
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    amqpTemplate.convertAndSend(QueueFileConstants.QUEUE_ES__FOLDER_DELETE, folderInfo);
                } catch (Exception e) {
                    log.error("folder delete error:{}", e);
                }
            }
        });
    }
}
