package com.besedin.yadro.yadro_pcb_task.repository;


import com.besedin.yadro.yadro_pcb_task.model.entity.Pcb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PcbRepository extends JpaRepository<Pcb, UUID> {

}
