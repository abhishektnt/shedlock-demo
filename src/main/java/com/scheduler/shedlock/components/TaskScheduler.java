package com.scheduler.shedlock.components;

import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class TaskScheduler {

    @Value("${spring.application.name}")
    private String applicationName;

    @Scheduled(cron = "*/5 * * * * *")
    @SchedulerLock(name = "TaskScheduler_scheduledTask",
            lockAtLeastFor = "PT5S", lockAtMostFor = "PT14M")
    public void scheduledTask() {
        LockAssert.assertLocked();//This is the main line which acquires lock and if acquired only proceeds
        //further after that else below line is not executed.
        System.out.println("Hello from application " + applicationName);
    }
}