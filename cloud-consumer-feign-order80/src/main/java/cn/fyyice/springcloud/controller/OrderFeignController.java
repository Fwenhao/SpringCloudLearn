package cn.fyyice.springcloud.controller;

import cn.fyyice.springcloud.entities.CommentResult;
import cn.fyyice.springcloud.entities.Payment;
import cn.fyyice.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author fwh
 * @version 1.0
 * @date 2021/1/27 11:54
 * @description
 */

@RestController
@Slf4j
@RequestMapping(value = "/consumer")
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/payment/get/{id}")
    public CommentResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        //openfeign-ribbon 客户端一般默认等待1s
        return paymentFeignService.paymentFeignTimeout();
    }
}
