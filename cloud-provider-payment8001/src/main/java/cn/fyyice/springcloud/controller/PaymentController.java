package cn.fyyice.springcloud.controller;

import cn.fyyice.springcloud.entities.CommentResult;
import cn.fyyice.springcloud.entities.Payment;
import cn.fyyice.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author fwh
 * @version 1.0
 * @date 2021/1/21 17:06
 * @description
 */

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/create")
    public CommentResult create(@RequestBody Payment payment){
        System.out.println(payment);
        int result = paymentService.create(payment);
        log.info("插入结果："+result);
        if (result > 0){
            return new CommentResult(200,"数据插入成功"+payment+"|serverPort:"+serverPort,result);
        }else {
            return new CommentResult(401,"数据插入失败",null);
        }
    }

    @GetMapping(value = "/get/{id}")
    public CommentResult getPaymentById(@PathVariable("id") Long id){
        //通过 @PathVariable 可以将URL中占位符参数{xxx}绑定到处理器类的方法形参中@PathVariable(“xxx“)
        Payment payment = paymentService.getPaymentById(id);
        log.info("获取结果："+payment);
        if (payment != null){
            return new CommentResult(200,"查询成功"+"|serverPort:"+serverPort,payment);
        }else {
            return new CommentResult(401,"没有对应记录:"+id,null);
        }
    }

    @GetMapping(value = "/discovery")
    public Object discovery(){
        //服务发现

        //获取注册的微服务信息
        List<String> services = discoveryClient.getServices();
        for(String element : services){
            log.info("element:"+element);
        }

        //获取对应serviceId下的所有实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PATMENT-SERVICE");
        for (ServiceInstance instance : instances){
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping(value = "/lb")
    public String getPayment(){
        return serverPort;
    }

    @GetMapping(value = "/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
