package com.bulingfeng.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:bulingfeng
 * @Date: 2020-10-22
 * 这个配置并没有实现负载均衡
 */
@Configuration
public class RibbonBalancerConfig {
    @Bean
    public IRule balancerRule(){
        return new RetryRule();
    }
}
