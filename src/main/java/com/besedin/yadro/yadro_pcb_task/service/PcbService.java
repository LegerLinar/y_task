package com.besedin.yadro.yadro_pcb_task.service;

import com.besedin.yadro.yadro_pcb_task.model.dto.PcbDto;
import com.besedin.yadro.yadro_pcb_task.model.dto.PcbMoveDto;
import com.besedin.yadro.yadro_pcb_task.model.dto.PcbStatusDto;
import com.besedin.yadro.yadro_pcb_task.model.entity.PcbLog;
import com.besedin.yadro.yadro_pcb_task.model.enums.PcbProcessStep;

import java.util.List;
import java.util.UUID;

public interface PcbService {

    PcbDto registerPcb(String registerRequest);

    PcbMoveDto nextStepPcb(UUID pcbId);

    PcbStatusDto getPcbStatus(UUID pcbId);

    List<PcbLog> getPcbLogs(UUID pcbId);


}
