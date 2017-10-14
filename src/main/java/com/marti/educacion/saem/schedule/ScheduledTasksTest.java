package com.marti.educacion.saem.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;

//@Component
public class ScheduledTasksTest {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasksTest.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //@Scheduled(cron=". . .")
    //@Scheduled(cron="0/10 * * * * *")
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
	
}
