package com.sommelier.wine4you;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication
public class Wine4youApplication {

    public static void main(String[] args) {
        SpringApplication.run(Wine4youApplication.class, args);
        log.info("Start application 'Wine4You' at: " + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    }

}
