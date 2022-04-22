package com.capt.ebankingbackend2022.configuration;

import com.capt.ebankingbackend2022.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduleConfig {
    @Autowired
    private TransactionService transactionService;

    @Scheduled(cron = "0 * * * * *")
    public void scheduleFixedDelayTask() {
        System.out.println("Task running: " + System.currentTimeMillis());
        transactionService.resolveEndTimeSavingAndLoan();
    }
}
