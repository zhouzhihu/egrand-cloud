package com.egrand.cloud.core.config;

import com.egrand.cloud.core.configuration.ElasticsearchProperties;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.Resource;
import java.net.InetAddress;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "egd.elasticsearch", name = "enabled", havingValue = "true")
public class TransportClientConfiguration {

    @Resource
    private ElasticsearchProperties elasticsearchProperties;

    @Bean(name = "transportClient")
    public TransportClient transportClient() {
        System.out.println("Elasticsearch初始化开始。。。。。");
        System.out.println("ClusterName = " + elasticsearchProperties.getClusterName());
        System.out.println("PoolSize = " + elasticsearchProperties.getPoolSize());
        System.out.println("HostName = " + elasticsearchProperties.getHostName());
        System.out.println("Port = " + elasticsearchProperties.getPort());
        TransportClient transportClient = null;
        try {
            // 配置信息
            Settings esSetting = Settings.builder()
                    .put("cluster.name", elasticsearchProperties.getClusterName()) //集群名字
                    .put("client.transport.sniff", true)//增加嗅探机制，找到ES集群
                    .put("thread_pool.search.size", Integer.parseInt(elasticsearchProperties.getPoolSize()))//增加线程池个数，暂时设为5
                    .build();
            //配置信息Settings自定义
            transportClient = new PreBuiltTransportClient(esSetting);
            TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(elasticsearchProperties.getHostName()), Integer.valueOf(elasticsearchProperties.getPort()));
            transportClient.addTransportAddresses(transportAddress);
        } catch (Exception e) {
            log.error("elasticsearch TransportClient create error!!", e);
        }
        return transportClient;
    }
}
