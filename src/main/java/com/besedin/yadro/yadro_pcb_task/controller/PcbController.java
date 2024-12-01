package com.besedin.yadro.yadro_pcb_task.controller;

import com.besedin.yadro.yadro_pcb_task.model.dto.PcbDto;
import com.besedin.yadro.yadro_pcb_task.model.dto.PcbMoveDto;
import com.besedin.yadro.yadro_pcb_task.model.dto.PcbStatusDto;
import com.besedin.yadro.yadro_pcb_task.model.entity.PcbLog;
import com.besedin.yadro.yadro_pcb_task.model.enums.PcbProcessStep;
import com.besedin.yadro.yadro_pcb_task.service.PcbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pcbs")
@Slf4j
public class PcbController {

    private final PcbService pcbService;

    public PcbController(PcbService pcbService) {
        this.pcbService = pcbService;
    }

    @PostMapping
    public ResponseEntity<PcbDto> registerPcb(
        @RequestBody String request) {
        log.debug(String.format("new request: " + request));
        return ResponseEntity.ok(pcbService.registerPcb(request));
    }

    @PostMapping("/{pcbId}/next")
    public ResponseEntity<PcbMoveDto> movePcb(@PathVariable UUID pcbId) {
        log.debug(String.format("move pcb id: " + pcbId));
        return ResponseEntity.ok(pcbService.nextStepPcb(pcbId));
    }

    @GetMapping("/{pcbId}/get-pcb-status")
    public ResponseEntity<PcbStatusDto> getCurrentPcbStatus(@PathVariable UUID pcbId){
        log.debug(String.format("get pcb stepStatus: " + pcbId));

        return ResponseEntity.ok(pcbService.getPcbStatus(pcbId));
    }

    @GetMapping("/{pcbId}/history")
    public ResponseEntity<List<PcbLog>> getHistory(@PathVariable UUID pcbId) {
        log.debug(String.format("get history: " + pcbId));

        return ResponseEntity.ok(pcbService.getPcbLogs(pcbId));
    }

}
