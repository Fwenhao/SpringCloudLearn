package cn.fyyice.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author fwh
 * @version 1.0
 * @date 2021/1/27 10:33
 * @description 自定义负载均衡算法
 */
public interface LoadBalancer {
    /**
     * @description : 〈初始化容器_填充实例〉
     * @param serviceInstances 1
     * @return : org.springframework.cloud.client.ServiceInstance
     */
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
