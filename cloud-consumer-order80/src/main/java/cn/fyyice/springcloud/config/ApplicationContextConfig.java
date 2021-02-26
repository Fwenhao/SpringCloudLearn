package cn.fyyice.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author fwh
 * @version 1.0
 * @date 2021/1/22 12:01
 * @description
 */

@Configuration
public class ApplicationContextConfig {


    /**
     * 默认开启负载均衡
     */
    @Bean
//    @LoadBalanced
    public RestTemplate getRestTemplate(){
        //restful方法接口调用
        return new RestTemplate();
    }

}
