package com.example.cs_office.TestSchedule;

import com.example.cs_office.Service.SendEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */
@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired
    private SendEmailService sendEmailService;

    @Scheduled(fixedRate = 10000)
    public void scheduleTaskWithFixedRate() {
        int[] numbers = {4, 3, 5};
        for (int i : numbers) {
            if (5 == i) {
                logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
            }else{
                logger.info("Fixed Rate Task 1 :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
            }
        }
    }

//    @Scheduled(fixedDelay = 2000)
//    public void scheduleTaskWithFixedDelay() {
//        logger.info("Fixed Delay Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException ex) {
//            logger.error("Ran into an error {}", ex);
//            throw new IllegalStateException(ex);
//        }
//    }
//
//    @Scheduled(fixedRate = 2000, initialDelay = 5000)
//    public void scheduleTaskWithInitialDelay() {
//        logger.info("Fixed Rate Task with Initial Delay :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
//    }
//
//    @Scheduled(cron = "2 * * * * ?")
//    public void scheduleTaskWithCronExpression() {
//        logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
//    }

}
