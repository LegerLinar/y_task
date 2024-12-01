package com.besedin.yadro.yadro_pcb_task.model.enums;

import com.besedin.yadro.yadro_pcb_task.exception.NotReadyProcessStatus;

public enum PcbProcessStep {
    REGISTRATION {
        @Override
        public PcbProcessStep getNextStep(PcbProcessStatus processStatus) {
            switch (processStatus) {
                case IN_WORK: throw new NotReadyProcessStatus(String.format("Stage %s is IN WORK", processStatus));
                case READY: return COMPONENT_INSTALLATION;
                default: throw new IllegalArgumentException("Unknown process stepStatus: " + processStatus);
            }
        }
    },
    COMPONENT_INSTALLATION {
        @Override
        public PcbProcessStep getNextStep(PcbProcessStatus processStatus) {
            switch (processStatus) {
                case IN_WORK: throw new NotReadyProcessStatus(String.format("Stage %s is IN WORK", processStatus));
                case READY: return QUALITY_CONTROL;
                default: throw new IllegalArgumentException("Unknown process stepStatus: " + processStatus);
            }

        }
    },
    QUALITY_CONTROL {
        @Override
        public PcbProcessStep getNextStep(PcbProcessStatus processStatus) {
            switch (processStatus) {
                case IN_WORK: throw new NotReadyProcessStatus(String.format("Stage %s is IN WORK", processStatus));
                case QUALITY_CONTROL_FAILED: return REPAIR;
                case QUALITY_CONTROL_SUCCESSFUL: return PACKAGING;
                default: throw new IllegalArgumentException("Unknown process stepStatus: " + processStatus);
            }
        }
    },
    REPAIR {
        @Override
        public PcbProcessStep getNextStep(PcbProcessStatus processStatus) {
            switch (processStatus) {
                case IN_WORK: throw new NotReadyProcessStatus(String.format("Stage %s is IN WORK", processStatus));
                case READY: return QUALITY_CONTROL;
                default: throw new IllegalArgumentException("Unknown process stepStatus: " + processStatus);
            }
        }
    },
    PACKAGING {
        @Override
        public PcbProcessStep getNextStep(PcbProcessStatus processStatus) {
            return null;
        }
    }
    ;

    public abstract PcbProcessStep getNextStep(PcbProcessStatus processStatus);
}
