package com.demo.nacos.example.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaojing
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosProviderApplication.class, args);
	}

	@RestController
	class EchoController {
		@RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
		public String echo(@PathVariable String string) {
			return "Hello CAE: " + string;
		}
		@CrossOrigin
                @RequestMapping(value = "/v1/consume_cpu", method = RequestMethod.POST)
                public Integer clientTest() {
                        // 消耗CPU 的计算
       		        for (int i = 0; i <= 10; i++) {
			       MyThread thread = new MyThread();
			       thread.start();
		        }
		        return 1;
               }
	}
}
