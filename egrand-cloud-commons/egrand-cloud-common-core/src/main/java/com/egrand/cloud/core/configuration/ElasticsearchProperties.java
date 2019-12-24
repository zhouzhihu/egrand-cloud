package com.egrand.cloud.core.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "egd.elasticsearch")
public class ElasticsearchProperties {
    /**
     * 主机
     */
    private String hostName;

    /**
     * 端口
     */
    private String port;

    /**
     * 集群名称
     */
    private String clusterName;

    /**
     * 连接池
     */
    private String poolSize;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ElasticsearchProperties{");
        sb.append("clientId='").append(hostName).append('\'');
        sb.append(", clientSecret='").append(port).append('\'');
        sb.append(", apiServerAddr='").append(clusterName).append('\'');
        sb.append(", authServerAddr='").append(poolSize).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
