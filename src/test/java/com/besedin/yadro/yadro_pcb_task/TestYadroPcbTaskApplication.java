package com.besedin.yadro.yadro_pcb_task;

import org.springframework.boot.SpringApplication;

public class TestYadroPcbTaskApplication {

    public static void main(String[] args) {
        SpringApplication.from(YadroPcbTaskApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
