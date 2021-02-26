package cn.fyyice.springcloud.dao;

import cn.fyyice.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author fwh
 * @version 1.0
 * @date 2021/1/20 18:44
 * @description
 */
@Mapper
public interface PaymentDao {
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
