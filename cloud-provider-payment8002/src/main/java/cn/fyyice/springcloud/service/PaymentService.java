package cn.fyyice.springcloud.service;

import cn.fyyice.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author fwh
 * @version 1.0
 * @date 2021/1/21 17:00
 * @description
 */
public interface PaymentService {
    int create(Payment payment);
    Payment getPaymentById(@Param("id") Long id);
}
