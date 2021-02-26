package cn.fyyice.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author fwh
 * @version 1.0
 * @date 2021/1/28 12:49
 * @description 服务降级处理
 */

//@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService fallback - paymentInfo_OK";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "PaymentFallbackService fallback - paymentInfo_TimeOut";
    }
}
