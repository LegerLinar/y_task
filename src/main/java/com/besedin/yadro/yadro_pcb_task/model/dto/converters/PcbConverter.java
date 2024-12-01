package com.besedin.yadro.yadro_pcb_task.model.dto.converters;

import com.besedin.yadro.yadro_pcb_task.model.dto.PcbDto;
import com.besedin.yadro.yadro_pcb_task.model.dto.PcbMoveDto;
import com.besedin.yadro.yadro_pcb_task.model.dto.PcbStatusDto;
import com.besedin.yadro.yadro_pcb_task.model.entity.Pcb;
import org.springframework.stereotype.Component;

@Component
public class PcbConverter {
    public PcbDto pcbToPcbDto(Pcb pcb) {
        return PcbDto.builder()
            .id(pcb.getId())
            .name(pcb.getName())
            .registrationTime(pcb.getCreatedAt())
            .build();
    }

    public PcbMoveDto pcbToPcbMoveDto(Pcb pcb) {
        return PcbMoveDto.builder()
            .id(pcb.getId())
            .name(pcb.getName())
            .step(pcb.getCurrentStep())
            .stepStatus(pcb.getStepStatus())
            .registrarTime(pcb.getCreatedAt())
            .lastUpdateTime(pcb.getLastUpdateTime())
            .build();
    }


    public PcbStatusDto pcbToPcbStatusDto(Pcb pcb) {
        return PcbStatusDto.builder()
            .id(pcb.getId())
            .name(pcb.getName())
            .step(pcb.getCurrentStep())
            .stepStatus(pcb.getStepStatus())
            .lastUpdateTime(pcb.getLastUpdateTime())
            .build();

    }
}
