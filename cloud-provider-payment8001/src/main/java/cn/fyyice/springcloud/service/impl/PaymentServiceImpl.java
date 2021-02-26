package cn.fyyice.springcloud.service.impl;

import cn.fyyice.springcloud.dao.PaymentDao;
import cn.fyyice.springcloud.entities.Payment;
import cn.fyyice.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author fwh
 * @version 1.0
 * @date 2021/1/21 17:01
 * @description
 */

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
