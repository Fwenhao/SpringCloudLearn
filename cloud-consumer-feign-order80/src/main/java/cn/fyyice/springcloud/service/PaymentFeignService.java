package cn.fyyice.springcloud.service;

import cn.fyyice.springcloud.entities.CommentResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author fwh
 * @version 1.0
 * @date 2021/1/27 11:47
 * @description
 */
@Component
@FeignClient(value = "CLOUD-PATMENT-SERVICE")
@RequestMapping(value = "/payment")
public interface PaymentFeignService {
    @GetMapping(value = "/get/{id}")
    public CommentResult getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/feign/timeout")
    public String paymentFeignTimeout();
}
