package com.sommelier.wine4you;

import com.sommelier.wine4you.utils.AppConstants;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@Log4j2
@SpringBootApplication
public class Wine4youApplication {

    public static void main(String[] args) {
        SpringApplication.run(Wine4youApplication.class, args);
        log.info("Start application 'Wine4You' at: " + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern(AppConstants.DEFAULT_PATTERN_DATE)));
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone(AppConstants.TIME_ZONE));
    }

}
