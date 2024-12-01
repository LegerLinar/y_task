package com.besedin.yadro.yadro_pcb_task.model.entity;

import com.besedin.yadro.yadro_pcb_task.model.enums.PcbProcessStatus;
import com.besedin.yadro.yadro_pcb_task.model.enums.PcbProcessStep;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "pcb_log")
public class PcbLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "pcb_id")
    @ManyToOne
    @JoinColumn(name = "pcb_id", nullable = false)
    private Pcb pcb;

    @Column(name = "step")
    @Enumerated(EnumType.STRING)
    private PcbProcessStep processStep;

    @Column(name = "step_status")
    @Enumerated(EnumType.STRING)
    private PcbProcessStatus processStatus;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;


}
