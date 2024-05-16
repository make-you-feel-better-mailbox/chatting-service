package com.onetwo.chattingservice;

import com.onetwo.chattingservice.common.GlobalStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChattingServiceApplication {

    public static void main(String[] args) {
        String active = System.getProperty(GlobalStatus.SPRING_PROFILES_ACTIVE);
        if (active == null) {
            System.setProperty(GlobalStatus.SPRING_PROFILES_ACTIVE, GlobalStatus.SPRING_PROFILES_ACTIVE_DEFAULT);
        }

        System.setProperty(
                GlobalStatus.SPRING_PROFILES_ACTIVE,
                System.getProperty(GlobalStatus.SPRING_PROFILES_ACTIVE, GlobalStatus.SPRING_PROFILES_ACTIVE_DEFAULT)
        );
        SpringApplication.run(ChattingServiceApplication.class, args);
    }

}
