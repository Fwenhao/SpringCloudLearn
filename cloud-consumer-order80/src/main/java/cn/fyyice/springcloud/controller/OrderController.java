package cn.fyyice.springcloud.controller;

import cn.fyyice.springcloud.entities.CommentResult;
import cn.fyyice.springcloud.entities.Payment;
import cn.fyyice.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author fwh
 * @version 1.0
 * @date 2021/1/22 11:41
 * @description
 */

@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderController {

    /**
     * public static final String PAYMENT_URP = "http://localhost:8001"; 单机版
     *
     * 由于做了集群，所以请求访问的地址是不能写死了的，我们可以根据注册进eureka中的application-name进行访问查找
     * 对外暴露微服务地址（名称），地址下并不知道有多少个集群；从而无法正确选择该以哪个地址作为入口
     * 因此我们需要给RestTemplate开启一个默认的加载机制    @LoadBalanced 负载均衡（轮询）
     *
     * 从而做到消费者只关心微服务的名称，而不是去关系其地址
     *
     * get/postForObject
     *      返回对象为响应体中数据转化成的对象，基本上可以理解为Json
     * get/postForEntity
     *      返回对象为ResponseEntity对象，包含了响应中的一些重要信息，比如响应头、响应状态码、响应体等
     *
     */
    public static final String PAYMENT_URP = "http://CLOUD-PATMENT-SERVICE";

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommentResult<Payment> create(Payment payment) {
        System.out.println(payment);
        //远程调度，请求url地址，提交参数,返回参数
        return restTemplate.postForObject(PAYMENT_URP + "/payment/create",payment, CommentResult.class);
    }

    @GetMapping("/payment/get/{id}")
    public CommentResult<Payment> getPayment(@PathVariable("id") Long id){
        System.out.println(id);
        return restTemplate.getForObject(PAYMENT_URP + "/payment/get/" + id,CommentResult.class);
    }

    @GetMapping("/payment/getForEntity/{id}")
    public CommentResult<Payment> getPayment2(@PathVariable("id") Long id) {
        ResponseEntity<CommentResult> entity = restTemplate.getForEntity(PAYMENT_URP + "/payment/get/" + id,CommentResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
            return new CommentResult<>(401,"操作失败");
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPayment(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PATMENT-SERVICE");
        if (instances == null || instances.size() <=0) {
            return null;
        }

        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }

}
