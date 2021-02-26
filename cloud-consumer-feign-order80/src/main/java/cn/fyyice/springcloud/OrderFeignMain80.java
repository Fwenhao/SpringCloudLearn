package cn.fyyice.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author fwh
 * @version 1.0
 * @date 2021/1/27 11:43
 * @description
 *  @EnableFeignClients 表明这是一个Feign的客户端,激活并开启
 */
@SpringBootApplication
@EnableFeignClients
public class OrderFeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class, args);
    }
}
