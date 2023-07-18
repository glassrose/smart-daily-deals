package com.projects.cv.smartdailydeals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class SchedulingServiceImpl implements SchedulingService {

    @Autowired
    DealsService dealsService;

    ScheduledExecutorService scheduler;

    public SchedulingServiceImpl() {
        this.scheduler = Executors.newScheduledThreadPool(10);
    }

    @Override
    public void activateDealEngine() {
        Calendar now = Calendar.getInstance();

        //Populate dealItemsOfTheDay every 23:55 PM

        int initialDelay = (24 - now.get(Calendar.HOUR_OF_DAY))*60 - (now.get(Calendar.MINUTE)) - 5;
        scheduler.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                dealsService.populateDealItemsOfTheDay();
            }
        },
                initialDelay, 24*60 , TimeUnit.MINUTES);


        initialDelay = 10 - (Calendar.getInstance().get(Calendar.MINUTE)%10);
        if (dealsService.getActiveDeals().size() == 0) {
            initialDelay = (24 - now.get(Calendar.HOUR_OF_DAY))*60 - (now.get(Calendar.MINUTE)) - 1;
        }
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                dealsService.updateActiveDeals();
            }
        },
                initialDelay, 10, TimeUnit.MINUTES);

        System.out.println("Deal Engine Activated!");
    }

    @Override
    public void deactivateDealEngine() {

        scheduler.shutdown();
        System.out.println("Deal Engine Deactivated!");
    }
}
