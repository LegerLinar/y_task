package com.besedin.yadro.yadro_pcb_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableAsync
public class YadroPcbTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(YadroPcbTaskApplication.class, args);
    }

}
