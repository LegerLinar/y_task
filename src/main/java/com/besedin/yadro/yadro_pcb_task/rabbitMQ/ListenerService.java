package com.besedin.yadro.yadro_pcb_task.rabbitMQ;

import com.besedin.yadro.yadro_pcb_task.exception.PcbNotFoundException;
import com.besedin.yadro.yadro_pcb_task.model.dto.PcbMoveDto;
import com.besedin.yadro.yadro_pcb_task.repository.PcbRepository;
import com.besedin.yadro.yadro_pcb_task.service.PcbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ListenerService {

    private final PcbRepository pcbRepository;

    @RabbitListener(queues = "process.component.installation.response")
    @Transactional
    public void handleComponentInstallationRequest(PcbMoveDto pcb) {
        pcbRepository.save(
            pcbRepository.findById(pcb.id())
                .orElseThrow(() -> new PcbNotFoundException("Pcb not found"))
        );

        log.debug(String.format("PCB id:%s components installed - %s",  pcb.id(), pcb.stepStatus()));
    }

    @RabbitListener(queues = "process.quality.control.response")
    @Transactional
    public void handleQualityControlRequest(PcbMoveDto pcb) {
        pcbRepository.save(
            pcbRepository.findById(pcb.id())
                .orElseThrow(() -> new PcbNotFoundException("Pcb not found"))
        );

        log.debug(String.format("PCB id:%s quality control - %s",  pcb.id(), pcb.stepStatus()));
    }

    @RabbitListener(queues = "process.repair.response")
    @Transactional
    public void handleRepairRequest(PcbMoveDto pcb) {
        pcbRepository.save(
            pcbRepository.findById(pcb.id())
                .orElseThrow(() -> new PcbNotFoundException("Pcb not found"))
        );

        log.debug(String.format("PCB id:%s repair - %s",  pcb.id(), pcb.stepStatus()));
    }

    @RabbitListener(queues = "process.packaging.response")
    @Transactional
    public void handlePackagingRequest(PcbMoveDto pcb) {
        pcbRepository.save(
            pcbRepository.findById(pcb.id())
                .orElseThrow(() -> new PcbNotFoundException("Pcb not found"))
        );

        log.debug(String.format("PCB id:%s packaging - %s",  pcb.id(), pcb.stepStatus()));
    }


}
