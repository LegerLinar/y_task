package com.besedin.yadro.yadro_pcb_task.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitProducerConfig {

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    public Queue componentInstallationQueue() {
        return new Queue("process.component.installation.request");
    }

    @Bean
    public Queue qualityControlQueue() {
        return new Queue("process.quality.control.request");
    }

    @Bean
    public Queue repairQueue() {
        return new Queue("process.repair.request");
    }

    @Bean
    public Queue packagingQueue() {
        return new Queue("process.packaging.request");
    }


    @Bean
    public Binding componentInstallationBinding(Queue componentInstallationQueue,
                                                TopicExchange processExchange) {
        return BindingBuilder.bind(componentInstallationQueue).to(processExchange).with("process.component.installation.request");
    }

    @Bean
    public Binding qualityControlBinding(Queue qualityControlQueue,
                                         TopicExchange processExchange) {
        return BindingBuilder.bind(qualityControlQueue).to(processExchange).with("process.quality.control.request");
    }

    @Bean
    public Binding repairBinding(Queue repairQueue, TopicExchange processExchange) {
        return BindingBuilder.bind(repairQueue).to(processExchange).with("process.repair.request");
    }

    @Bean
    public Binding packagingBinding(Queue packagingQueue, TopicExchange processExchange) {
        return BindingBuilder.bind(packagingQueue).to(processExchange).with("process.packaging.request");
    }
}
