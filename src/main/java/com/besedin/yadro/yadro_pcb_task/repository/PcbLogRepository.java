package com.besedin.yadro.yadro_pcb_task.repository;

import com.besedin.yadro.yadro_pcb_task.model.entity.PcbLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PcbLogRepository extends JpaRepository<PcbLog, UUID> {
    List<PcbLog> findByPcbId(UUID pcbId);
}
