package com.besedin.yadro.yadro_pcb_task.model.entity;


import com.besedin.yadro.yadro_pcb_task.model.enums.PcbProcessStatus;
import com.besedin.yadro.yadro_pcb_task.model.enums.PcbProcessStep;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="pcb")
public class Pcb {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "current_step")
    @Enumerated(EnumType.STRING)
    private PcbProcessStep currentStep;

    @Column(name = "step_status")
    @Enumerated(EnumType.STRING)
    private PcbProcessStatus stepStatus;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_update")
    private LocalDateTime lastUpdateTime;

    @OneToMany(mappedBy = "pcb")
    private List<PcbLog> pcbLogs;


    public void setCurrentStep(PcbProcessStep currentStep) {

    }

}
