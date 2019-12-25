package com.example.serviceclient01.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discovery")
public class DiscoveryController {

    @Autowired
    private DiscoveryClient client;

    @RequestMapping("/query")
    public String query() {
        JSONObject result = new JSONObject();
        //获取所有服务名称
        List<String> services = client.getServices();
        System.out.println("服务名称集合：" + services.toString());
        result.put("services01",services);
        //获取某个实例的信息
        List<ServiceInstance> instances = client.getInstances("serviceclient01");
        StringBuilder builder = new StringBuilder();
        for (ServiceInstance instance : instances) {
            builder.append(instance.getInstanceId()+"-"+instance.getUri().toString());
        }
        result.put("instances01",builder);
        return result.toJSONString();
    }

    @PostMapping("/hystrix")
//    @HystrixCommand(fallbackMethod = "fallback") //熔断转移到服务消费者
    public String hystrix(@RequestParam("param") String param) {
        if (param.length() == 0) {
            throw new RuntimeException("参数为空");
        }
        return "serviceClient01-" + param;
    }

    public String fallback(@RequestParam("param") String param) {
        return "参数为空";
    }
}
