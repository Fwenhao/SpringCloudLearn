package cn.fyyice.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fwh
 * @version 1.0
 * @date 2021/1/26 19:17
 * @description Ribbon轮询算法
 */

@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
        //负载均衡算法默认为随机
        return new RandomRule();
    }

}
