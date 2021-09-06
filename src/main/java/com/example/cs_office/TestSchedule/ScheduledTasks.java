package com.example.cs_office.TestSchedule;

import com.example.cs_office.Model.Entity.OrderDetail;
import com.example.cs_office.Model.Entity.Schedule;
import com.example.cs_office.Service.OrderDetailService;
import com.example.cs_office.Service.ScheduleService;
import com.example.cs_office.Service.SendEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
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

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ScheduleService scheduleService;

//    @Scheduled(fixedRate = 10000)
//    public void scheduleTaskWithFixedRate() {
//        java.util.Date dateNow = new Date();
//        try {
//            List<OrderDetail> listOrderDetail = orderDetailService.getListOrderDetail();
//            if (listOrderDetail.size() > 0) {
//                for (OrderDetail orderDetail : listOrderDetail) {
//                    List<Schedule> listSchedule = scheduleService.getListScheduleUpdate((java.sql.Date) dateNow, orderDetail.getId());
//                    if (listSchedule.size() > 0) {
//                        logger.info("Room update", dateTimeFormatter.format(LocalDateTime.now()));
//                    } else {
//                        logger.info("No room update", dateTimeFormatter.format(LocalDateTime.now()));
//                    }
//                }
//            } else {
//                logger.info("No room update", dateTimeFormatter.format(LocalDateTime.now()));
//            }
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException ex) {
//            logger.error("Ran into an error {}", ex);
//            throw new IllegalStateException(ex);
//        }
//    }

    @Scheduled(fixedDelay = 10000)
    public void scheduleTaskWithFixedDelay() {
        java.util.Date dateNow = new Date();
        List<OrderDetail> listOrderDetail = orderDetailService.getListOrderDetail();
        for (OrderDetail orderDetail : listOrderDetail) {
            List<Schedule> listSchedule = scheduleService.getListScheduleUpdate(dateNow, orderDetail.getId());
            List<Schedule> listScheduleFull = scheduleService.getListScheduleByIdOrderDetail(orderDetail.getId());
            if (listSchedule.size() > 0 && listSchedule.size() == listScheduleFull.size()) {
                logger.info("Room update", dateTimeFormatter.format(LocalDateTime.now()));
                orderDetailService.updateDone(orderDetail.getId());
            } else {
                logger.info("No room update", dateTimeFormatter.format(LocalDateTime.now()));
            }
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            logger.error("Ran into an error {}", ex);
            throw new IllegalStateException(ex);
        }
    }
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
