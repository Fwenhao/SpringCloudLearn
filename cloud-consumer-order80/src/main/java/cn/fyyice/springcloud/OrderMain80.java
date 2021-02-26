package cn.fyyice.springcloud;

import cn.fyyice.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author fwh
 * @version 1.0
 * @date 2021/1/22 11:38
 * @description
 */

@SpringBootApplication
@EnableEurekaClient
//@RibbonClient(name = "CLOUD-PATMENT-SERVICE",configuration = MySelfRule.class)
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
