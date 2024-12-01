package com.besedin.yadro.yadro_pcb_task.exception;

public class NotReadyProcessStatus extends RuntimeException {
    public NotReadyProcessStatus(String msg) {
        super(msg);
    }
}
