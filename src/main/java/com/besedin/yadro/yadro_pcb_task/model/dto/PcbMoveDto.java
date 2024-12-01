package com.besedin.yadro.yadro_pcb_task.model.dto;

import com.besedin.yadro.yadro_pcb_task.model.enums.PcbProcessStatus;
import com.besedin.yadro.yadro_pcb_task.model.enums.PcbProcessStep;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record PcbMoveDto(UUID id,
                         String name,
                         PcbProcessStep step,
                         PcbProcessStatus stepStatus,
                         LocalDateTime registrarTime,
                         LocalDateTime lastUpdateTime) {
}
