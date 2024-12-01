package com.besedin.yadro.yadro_pcb_task.rabbitMQ;

import com.besedin.yadro.yadro_pcb_task.model.dto.PcbDto;
import com.besedin.yadro.yadro_pcb_task.model.entity.Pcb;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProducerService {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    //"process.component.installation.request"
    //"process.quality.control.request"
    //"process.repair.request"
    //"process.packaging.request"

    @Async("sendExecutor")
    public void sendToProcess(PcbDto pcb, String routingKey) {
        String message;
        try {
            message = objectMapper.writeValueAsString(pcb);
        } catch (JsonProcessingException ex) {
            log.error(String.format("Error sending pcb to %s, pcb_id: %s; exception: ", routingKey, pcb.id(), ex.getMessage()));
            return;
        }
        rabbitTemplate.convertAndSend(routingKey, message);
        log.debug(String.format("Sent pcb_id: %s to %s", pcb.id(), routingKey));
    }



}
