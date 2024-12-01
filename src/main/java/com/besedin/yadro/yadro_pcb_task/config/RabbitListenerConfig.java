package com.besedin.yadro.yadro_pcb_task.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitListenerConfig {



    @Bean
    public TopicExchange processExchange() {
        return new TopicExchange("processExchange");
    }

    @Bean
    public Queue componentInstallationResponseQueue() {
        return new Queue("process.component.installation.response");
    }

    @Bean
    public Queue qualityControlResponseQueue() {
        return new Queue("process.quality.control.response");
    }

    @Bean
    public Queue repairResponseQueue() {
        return new Queue("process.repair.response");
    }

    @Bean
    public Queue packagingResponseQueue() {
        return new Queue("process.packaging.response");
    }

    @Bean
    public Binding componentInstallationResponseBinding() {
        return BindingBuilder.bind(componentInstallationResponseQueue())
            .to(processExchange())
            .with("process.component.installation.response");
    }

    @Bean
    public Binding qualityControlResponseBinding() {
        return BindingBuilder.bind(qualityControlResponseQueue())
            .to(processExchange())
            .with("process.quality.control.response");
    }

    @Bean
    public Binding repairResponseBinding() {
        return BindingBuilder.bind(repairResponseQueue())
            .to(processExchange())
            .with("process.repair.response");
    }

    @Bean
    public Binding packagingResponseBinding() {
        return BindingBuilder.bind(packagingResponseQueue())
            .to(processExchange())
            .with("process.packaging.response");
    }

}
