package com.besedin.yadro.yadro_pcb_task.service.impl;


import com.besedin.yadro.yadro_pcb_task.exception.PcbNotFoundException;
import com.besedin.yadro.yadro_pcb_task.model.dto.PcbDto;
import com.besedin.yadro.yadro_pcb_task.model.dto.PcbMoveDto;
import com.besedin.yadro.yadro_pcb_task.model.dto.PcbStatusDto;
import com.besedin.yadro.yadro_pcb_task.model.dto.converters.PcbConverter;
import com.besedin.yadro.yadro_pcb_task.model.entity.Pcb;
import com.besedin.yadro.yadro_pcb_task.model.entity.PcbLog;
import com.besedin.yadro.yadro_pcb_task.model.enums.PcbProcessStatus;
import com.besedin.yadro.yadro_pcb_task.model.enums.PcbProcessStep;
import com.besedin.yadro.yadro_pcb_task.rabbitMQ.ProducerService;
import com.besedin.yadro.yadro_pcb_task.repository.PcbLogRepository;
import com.besedin.yadro.yadro_pcb_task.repository.PcbRepository;
import com.besedin.yadro.yadro_pcb_task.service.PcbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PcbServiceImpl implements PcbService {
    private final PcbRepository pcbRepository;
    private final PcbLogRepository pcbLog;
    private final PcbConverter pcbConverter;
    private final PcbProcessStep registerStep = PcbProcessStep.REGISTRATION;
    private final ProducerService producerService;


    @Override
    @Transactional
    public PcbDto registerPcb(String registerRequest) {
        Pcb pcb = new Pcb();
        pcb.setName(registerRequest);
        pcb.setCurrentStep(registerStep);
        return pcbConverter.pcbToPcbDto(pcbRepository.save(pcb));
    }

    @Override
    @Transactional
    public PcbMoveDto nextStepPcb(UUID pcbId) {
        Pcb pcb = pcbRepository.findById(pcbId)
            .orElseThrow(() -> new PcbNotFoundException("PCB not found"));

        producerService.sendToProcess(pcbConverter.pcbToPcbDto(pcb), processRouter(pcb));

        pcb.setStepStatus(PcbProcessStatus.IN_WORK);

        return pcbConverter.pcbToPcbMoveDto(pcbRepository.save(pcb));

    }

    @Override
    public PcbStatusDto getPcbStatus(UUID pcbId) {
         Pcb pcb = pcbRepository.findById(pcbId)
            .orElseThrow(() -> new PcbNotFoundException("PCB not found"));
         return PcbStatusDto.builder()
             .id(pcbId)
             .name(pcb.getName())
             .step(pcb.getCurrentStep())
             .stepStatus(pcb.getStepStatus())
             .build();
    }

    @Override
    public List<PcbLog> getPcbLogs(UUID pcbId) {
        return pcbLog.findByPcbId(pcbId);
    }

    private String processRouter(Pcb pcb) {
        switch (pcb.getCurrentStep()) {
            case REGISTRATION:
                return "process.component.installation.request";
            case COMPONENT_INSTALLATION:
                return "process.quality.control.request";
            case QUALITY_CONTROL:
                return (pcb.getStepStatus() == PcbProcessStatus.QUALITY_CONTROL_SUCCESSFUL) ? "process.quality.control.successful" : "process.quality.control.failed";
            case REPAIR:
                return "process.quality.control.request";
            case PACKAGING:
                return "process.packaging.request";
            default: throw new IllegalArgumentException(String.format("Unknown step %s", pcb.getCurrentStep()));
        }
    }

}
