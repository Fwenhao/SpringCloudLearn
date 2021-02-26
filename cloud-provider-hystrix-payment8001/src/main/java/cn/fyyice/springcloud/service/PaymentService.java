package cn.fyyice.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author fwh
 * @version 1.0
 * @date 2021/1/27 14:45
 * @description
 */

@Service
public class PaymentService {

    /**
     * @description : 〈正常访问〉
     * @param id 1
     * @return : java.lang.String
     */
    public String paymentInfo_OK(Integer id){
        return "线程池:"+Thread.currentThread().getName()+ "  paymentInfo_OK,id:"+ id +"🚀🛫🛫🛫🛫";
    }

    /**
     * @description : 〈超时访问〉
     * @param id 1
     * @return : java.lang.String
     */

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value="3000")
    })
    public String paymentInfo_TimeOut(Integer id){
        int timeNumber = 2;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:"+Thread.currentThread().getName()+ "  paymentInfo_TimeOut,id:"+ id +"  耗时"+ timeNumber +"秒";
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池:"+Thread.currentThread().getName()+ " 系统繁忙，请稍后再试,id:"+ id +" 可惜o(╥﹏╥)o 🙁 ";
    }

    /********************************  服务熔断   ***************************************/
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),  // 请求次数超过了峰值，熔断器将会从关闭到开启S
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),    // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),    //失败率达到%多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id")Integer id){
        if (id < 0){
            throw new RuntimeException("【id】不能为负数");
        }
        //类似于UUID UUID.randomUUID().toString()
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功 id:"+ serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id不能为负数，你是傻逼吗 😳 id:" + id;
    }


}
